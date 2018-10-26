/**
 *
 * @author maguhos
 */
package dk.codingpirates.cpdemo4;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

public class ScoreboardManager implements Listener {

    private static ScoreboardManager instance = null;
    private Collection<Player> players;
    private TreeMap<String, String> display = new TreeMap<>();
    private int delay = 40;
    private boolean taskisrunning = false;

    public static synchronized ScoreboardManager getInstance() {
        if (instance == null) {
            instance = new ScoreboardManager();
        }
        return instance;
    }

    private String defaultDisplay(Player p) {
        return "§e§L - Velkommen -\n"
                + "\n"
                + "§4§L" + p.getName() + "\n";
    }

    private String globalHeader(Player p) {
        return "";
    }

    private String globalFooter(Player p) {
        return "";
    }

    public void starttask(Plugin plugin) {
        getServer().getPluginManager().registerEvents(this, plugin);
        players = (Collection<Player>) Bukkit.getOnlinePlayers();
        for (Player p : players) {
            startOne(p);
        }
        taskisrunning = true;
        new BukkitRunnable() {
            private ScoreboardManager sb = ScoreboardManager.getInstance();

            @Override
            public void run() {
                if (!taskisrunning) {
                    this.cancel();
                }
                if (players != null) {
                    for (Player p : players) {
                        sb.updateDisplay(p);
                    }
                }
            }
        }.runTaskTimer(plugin, delay, delay);
    }

    public void stoptask() {
        taskisrunning = false;
        for (Player p : players) {
            stopOne(p);
        }
    }

    private String getDisplay(Player p) {
        return globalHeader(p) + getPlayerText(p) + globalFooter(p);
    }

    private String getPlayerText(Player p) {
        return display.getOrDefault(p.getUniqueId().toString(), defaultDisplay(p));
    }

    public void setPlayerText(Player p, String info) {
        if (info == null) {
            removePlayerText(p);
        } else {
            display.put(p.getUniqueId().toString(), info);
        }
    }

    public void removePlayerText(Player p) {
        display.remove(p.getUniqueId().toString());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        startOne(p);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player p = (Player) event.getPlayer();
        stopOne(p);
    }

    public void startOne(Player p) {
        if (!players.contains(p)) {
            players.add(p);
        }
        updateDisplay(p);
    }

    public void stopOne(Player p) {
        players = (Collection<Player>) Bukkit.getOnlinePlayers();
        if (players.contains(p)) {
            //players.remove(p);
        }
        display.remove(p.getUniqueId());
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public boolean updateDisplay(Player p) {
        return updateDisplay(p, getDisplay(p));
    }

    public boolean updateDisplay(Player p, String info) {

        String[] elements = info.split("\n");
        elements = cutUnranked(elements);
        elements = removedub(elements);
        try {
            if (p.getScoreboard() == null || p.getScoreboard() == Bukkit.getScoreboardManager().getMainScoreboard() || p.getScoreboard().getObjectives().size() != 1) {
                p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
            if (p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16)) == null) {
                p.getScoreboard().registerNewObjective(p.getUniqueId().toString().substring(0, 16), "dummy");
                p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16)).setDisplaySlot(DisplaySlot.SIDEBAR);
            }
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(elements[0]);

            for (int i = 1; i < 16; i++) {
                if (i < elements.length && elements[i] != null) {
                    if (p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(elements[i]).getScore() != 16 - i) {
                        p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(elements[i]).setScore(16 - i);
                    }
                }
            }
            List<String> elemhash = Arrays.asList(elements);
            for (String entry : p.getScoreboard().getEntries()) {
                if (!elemhash.contains(entry)) {
                    p.getScoreboard().resetScores(entry);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String[] cutUnranked(String[] content) {
        String[] elements = Arrays.copyOf(content, 16);
        if (elements[0] == null) {
            elements[0] = "Unamed board";
        }
        if (elements[0].length() > 32) {
            elements[0] = elements[0].substring(0, 32);
        }
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] != null) {
                if (elements[i].length() > 40) {
                    elements[i] = elements[i].substring(0, 40);
                }
            }
        }
        return elements;
    }

    public static String[] removedub(String[] dub) {
        Set<String> set = new LinkedHashSet<String>(Arrays.asList(dub));
        return set.toArray(new String[set.size()]);
    }

}

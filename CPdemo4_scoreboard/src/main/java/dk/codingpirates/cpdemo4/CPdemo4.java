package dk.codingpirates.cpdemo4;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/* 
Demo4 laver et scoreboard
tilpas defaultDisplay, globalHeader og globalFooter i ScoreboardManager
 */
public class CPdemo4 extends JavaPlugin implements Listener {

    public ScoreboardManager scoreboard = ScoreboardManager.getInstance();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[" + this.getName() + "] Enabling plugin");
        scoreboard.starttask(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + this.getName() + "] Disabling plugin");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //tilføj individuel info for en spiller
        Player p = event.getPlayer();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        scoreboard.setPlayerText(p, "Du er logget på serveren kl." + formatter.format(date));
    }

}
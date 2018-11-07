package dk.codingpirates.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatManager {

    private static HatManager instance = null;
    private final Map<UUID, ItemStack> players = new HashMap<>();

    public static synchronized HatManager getInstance() {
        if (instance == null) {
            instance = new HatManager();
        }
        return instance;
    }

    public void addHat(Player p, ItemStack item) {
        if (!players.containsKey(p.getUniqueId())) {
            players.put(p.getUniqueId(), p.getInventory().getHelmet());
        }
        p.getInventory().setHelmet(item);
    }

    public void removeHat(Player p) {
        if (!players.containsKey(p.getUniqueId())) {
            p.getInventory().setHelmet(players.get(p.getUniqueId()));
            players.remove(p.getUniqueId());
        }
    }

    public void removeAllHats() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            removeHat(p);
        }

    }

}

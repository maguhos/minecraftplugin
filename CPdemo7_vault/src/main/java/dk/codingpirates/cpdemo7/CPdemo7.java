package dk.codingpirates.cpdemo7;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

/* 
Demo6 tiføjer en ny kommando
 */
public class CPdemo7 extends JavaPlugin {

    public EcoVault eco = EcoVault.getInstance();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[" + this.getName() + "] Enabling plugin");
        eco.setup();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + this.getName() + "] Disabling plugin");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.sendMessage("Du har " + eco.getBalance(p) +"$");
    }

}

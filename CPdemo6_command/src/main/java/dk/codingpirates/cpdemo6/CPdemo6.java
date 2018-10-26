package dk.codingpirates.cpdemo6;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

/* 
Demo6 tiføjer en ny kommando
 */
public class CPdemo6 extends JavaPlugin implements Listener{

   
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[" + this.getName() + "] Enabling plugin");
        new MyCommands(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + this.getName() + "] Disabling plugin");
    }

}

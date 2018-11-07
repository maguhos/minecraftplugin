package dk.codingpirates.cpdemo3;

import dk.codingpirates.lib.SettingsManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/* 
Demo3 Hente og gemme settings
 */
public class CPdemo3 extends JavaPlugin {

    public SettingsManager settings = SettingsManager.getInstance();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[" + this.getName() + "] Enabling plugin");
        settings.setup(this);

        //henter setting og bruger værdien 1.0.0 hvis den ikke er sat.
        String version = settings.getConfig().getString("plugin.version", "1.0.0");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + this.getName() + " version: " + version);

        //henter settings uden default value
        String message = settings.getConfig().getString("plugin.message");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + message);

        //gemmer settings
        settings.getConfig().set("plugin.version", version);
        //gemmer til fil
        settings.saveConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + this.getName() + "] Disabling plugin");
    }

}

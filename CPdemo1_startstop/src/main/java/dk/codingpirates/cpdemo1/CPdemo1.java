
package dk.codingpirates.cpdemo1;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/*
Plugin demo1 som skriver på skærmen 
når plugin'et loades samt når plugin'et stopper igen 
når serveren startes, stoppes eller reloades 
*/

public class CPdemo1 extends JavaPlugin {
     @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[" + this.getName() + "] Enabling plugin");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + this.getName() + "] Disabling plugin");
    }
}

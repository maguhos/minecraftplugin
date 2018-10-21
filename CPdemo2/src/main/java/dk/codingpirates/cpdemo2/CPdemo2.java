
package dk.codingpirates.cpdemo2;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


public class CPdemo2 extends JavaPlugin {
     @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[" + this.getName() + "] Enabling plugin");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[" + this.getName() + "] Disabling plugin");
    }
}

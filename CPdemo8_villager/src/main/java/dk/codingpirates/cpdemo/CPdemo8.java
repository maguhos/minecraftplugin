package dk.codingpirates.cpdemo;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/* 
Demo8 spawner villageer 
 */
public class CPdemo8 extends JavaPlugin implements Listener {

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

    @EventHandler
    public void on(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        p.sendMessage("test");
        if (e.getRightClicked() instanceof Villager) {
            Villager v = (Villager) e.getRightClicked();
            if (v.getCustomName().equalsIgnoreCase("Æblemand")) {
                e.setCancelled(true);

                //Do something
                //For example open an Inventory
                Inventory inv = Bukkit.createInventory(null, 27, "Bare tag for dig af varerne");
                ItemStack item = new ItemStack(Material.APPLE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("Dette er et rødt æble!!");
                item.setItemMeta(meta);
                inv.addItem(item);
                p.openInventory(inv);
            }
        }
    }

    @EventHandler
    public void on2(EntityInteractEvent e) {
        Bukkit.getConsoleSender().sendMessage("test2");
    }
    
  

}

package dk.codingpirates.cpdemo7;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;

/**
 *
 * @author maguhos
 */
public class EcoVault {

    private static EcoVault instance = null;
    private static Economy economy = null;

    public static synchronized EcoVault getInstance() {
        if (instance == null) {
            instance = new EcoVault();
        }
        return instance;
    }

    public void setup() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> ecoProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (ecoProvider != null) {
            economy = ecoProvider.getProvider();
        }
    }

    public static String format(Double price) {
        return economy.format(price);
    }

    public static double getBalance(Player player) {
        if (economy == null) {
            return 0;
        }
        return economy.getBalance(player);
    }

    public static boolean setBalance(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        return removeBalance(player, getBalance(player)) && addBalance(player, amount);
    }

    public static boolean addBalance(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        EconomyResponse r = economy.depositPlayer(player, amount);
        if (r.transactionSuccess()) {
            player.sendMessage(ChatColor.GREEN + String.format("You were given %s and now have %s", economy.format(r.amount), economy.format(r.balance)));
            return true;
        } else {
            player.sendMessage(ChatColor.RED + String.format("An error occured: %s", r.errorMessage));
            return false;
        }
    }

    public static boolean removeBalance(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        EconomyResponse r = economy.withdrawPlayer(player, amount);
        if (r.transactionSuccess()) {
            player.sendMessage(ChatColor.GREEN + String.format("You used %s and now have %s", economy.format(r.amount), economy.format(r.balance)));
            return true;
        } else {
            player.sendMessage(ChatColor.RED + String.format("An error occured: %s", r.errorMessage));
            return false;
        }
    }

    public static boolean canAfford(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        return economy.has(player, amount);
    }
}

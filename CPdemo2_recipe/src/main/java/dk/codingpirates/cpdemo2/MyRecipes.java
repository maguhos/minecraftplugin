/**
 *
 * @author maguhos
 */
package dk.codingpirates.cpdemo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MyRecipes {

    public static void AddRecipe(Plugin plugin) {
        String displayname=ChatColor.BLUE + "Mit Emerald Sword";
        String lore= ChatColor.GRAY + "Det grønne lyn.";       
        ItemStack item = createItemStack(Material.DIAMOND_SWORD, displayname, lore);
        item.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        NamespacedKey key = new NamespacedKey(plugin, "my_emerald_sword");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" E ", " E ", " S ");
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
    }
    public static ItemStack createItemStack(Material mat, String displayname, String lore) {
        ItemStack itemStack = new ItemStack(mat);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        List<String> list = new ArrayList<String>();
        if (lore.contains("\n")) {
            for (String next : lore.split("\n")) {
                list.add(next);
            }
            itemMeta.setLore(list);
        } else {
            itemMeta.setLore(Arrays.asList(lore));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

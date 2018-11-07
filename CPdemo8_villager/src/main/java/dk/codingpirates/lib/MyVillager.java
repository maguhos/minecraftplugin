package dk.codingpirates.lib;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MyVillager {

    private final Villager v;

    public MyVillager(Location loc, String Id) {
        v = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        v.setCustomName(Id);
        v.setCustomNameVisible(false);
        v.setProfession(Villager.Profession.LIBRARIAN);
        v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 127));
        v.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999999, 127));
        v.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 99999999, 127));
        v.setCanPickupItems(false);
        v.setAI(false);
        v.setInvulnerable(true);

    }

    public void kill() {
        if (v == null) {
            return;
        }
        v.setHealth(0);
    }

    public void AddTrade(ItemStack result, ItemStack ingredent1) {
        AddTrade(result, ingredent1, null);
    }

    public void AddTrade(ItemStack result, ItemStack ingredent1, ItemStack ingredent2) {
        if (ingredent1 == null) {
            return;
        }
        MerchantRecipe rec = new MerchantRecipe(result, Integer.MAX_VALUE);
        rec.addIngredient(ingredent1);
        if (ingredent2 != null) {
            rec.addIngredient(ingredent2);
        }
        List<MerchantRecipe> merchantRecipes = new ArrayList<MerchantRecipe>();
        merchantRecipes.add(rec);
        v.setRecipes(merchantRecipes);
    }

}

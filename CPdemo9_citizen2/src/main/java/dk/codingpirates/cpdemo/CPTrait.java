package dk.codingpirates.cpdemo;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CPTrait extends Trait {

    public CPTrait() {
        super("cptrait");
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent e) {
        if (e.getNPC() == this.getNPC()) {
            Player p = e.getClicker();
            p.sendMessage("right click + " + e.getNPC().getName());
        }
    }

    @EventHandler
    public void onNPCDamageByEntityEvent(NPCDamageByEntityEvent e) {
        if (e.getNPC() == this.getNPC()) {
            Player p = (Player) e.getDamager();
            p.sendMessage("left click + " + e.getNPC().getName());
        }
    }

    // Called every tick
    @Override
    public void run() {
    }

    //Run code when your trait is attached to a NPC. 
    //This is called BEFORE onSpawn, so npc.getBukkitEntity() will return null
    //This would be a good place to load configurable defaults for new NPCs.
    @Override
    public void onAttach() {
        Bukkit.getServer().getLogger().info(npc.getName() + " has been assigned CPTrait!");
    }

    // Run code when the NPC is despawned. This is called before the entity actually despawns so npc.getBukkitEntity() is still valid.
    @Override
    public void onDespawn() {
    }

    //Run code when the NPC is spawned. Note that npc.getBukkitEntity() will be null until this method is called.
    //This is called AFTER onAttach and AFTER Load when the server is started.
    @Override
    public void onSpawn() {

    }

    //run code when the NPC is removed. Use this to tear down any repeating tasks.
    @Override
    public void onRemove() {
    }

    static void registerCitizensAPI(Plugin plugin) {
        //check if Citizens is present and enabled.
        if (Bukkit.getPluginManager().getPlugin("Citizens") == null || Bukkit.getPluginManager().getPlugin("Citizens").isEnabled() == false) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Citizens 2.0 not found or not enabled, download http://ci.citizensnpcs.co/job/Citizens2/");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        //Register your trait with Citizens.        
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(CPTrait.class));
    }
}

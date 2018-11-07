/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.codingpirates.cpdemo;

import dk.codingpirates.lib.CommandManager;
import net.citizensnpcs.Citizens;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author maguhos Extends CommandManager, som laver autmatisk test for antal og
 * type af paramtre, der testes for permissions, der testes om det er OP eller
 * CONSOLE eller PLAYER der kalder kommandoen. Der laves automatisk tab
 * completion og hjælpetekster. Kommandoerne registreres automatisk i minecraft.
 */
public class MyCommands extends CommandManager {

    private final JavaPlugin plugin;

    public MyCommands(JavaPlugin plugin) {
        super(plugin, "CPdemo commands", "cp6demo.command", "cp9");
        this.plugin = plugin;
    }

    @CommandManager.Cmd(cmd = "create",
            args = "<name>", argTypes = {Arg.ArgString.class},
            help = "create", longhelp = "create",
            only = CommandManager.CommandOnly.OP,
            permission = "create")
    public static CommandManager.CommandFinished cmd_create(CommandSender sender, Object[] args) {
        Player p = (Player) sender;
        String name = (String) args[0];
        
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
        npc.spawn(p.getLocation());
        
        Equipment equipTrait = npc.getTrait(Equipment.class);
        equipTrait.set(0, new ItemStack(Material.DIAMOND_SWORD, 1));
        equipTrait.set(1, new ItemStack(Material.LEATHER_HELMET, 1));
        equipTrait.set(2, new ItemStack(Material.LEATHER_CHESTPLATE, 1));
        equipTrait.set(3, new ItemStack(Material.LEATHER_LEGGINGS, 1));
        equipTrait.set(4, new ItemStack(Material.LEATHER_BOOTS, 1));
        npc.addTrait(equipTrait);
        
        npc.addTrait(CPTrait.class);
        
        p.sendMessage("NPC created!");

        return CommandManager.CommandFinished.DONE;
    }


}

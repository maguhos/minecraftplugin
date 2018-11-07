/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.codingpirates.cpdemo;

import dk.codingpirates.lib.MyVillager;
import dk.codingpirates.lib.CommandManager;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author maguhos 
 * Extends CommandManager, som laver autmatisk test for antal og
 * type af paramtre, der testes for permissions, der testes om det er OP eller
 * CONSOLE eller PLAYER der kalder kommandoen. Der laves automatisk tab
 * completion og hjælpetekster. Kommandoerne registreres automatisk i minecraft.
 */
public class MyCommands extends CommandManager {

    private final JavaPlugin plugin;

    public MyCommands(JavaPlugin plugin) {
        super(plugin, "CPdemo commands", "cp6demo.command", "cp8");
        this.plugin = plugin;
    }

    @CommandManager.Cmd(cmd = "villager",
            args = "", argTypes = {},
            help = "villager", longhelp = "villager ",
            only = CommandManager.CommandOnly.OP,
            permission = "villager")
    public static CommandManager.CommandFinished cmd_villager_spawn(CommandSender sender, Object[] args) {
        Player p = (Player) sender;
        MyVillager v = new MyVillager(p.getLocation(), "Æblemand");
        p.sendMessage("Villager created!");
        return CommandManager.CommandFinished.DONE;
    }

    @CommandManager.Cmd(cmd = "villager2",
            args = "", argTypes = {},
            help = "villager", longhelp = "villager ",
            only = CommandManager.CommandOnly.OP,
            permission = "villager")
    public static CommandManager.CommandFinished cmd_villager2_spawn(CommandSender sender, Object[] args) {
        Player p = (Player) sender;
        MyVillager v = new MyVillager(p.getLocation(), "Hr. Stone");
        p.sendMessage("Villager created!");
        v.AddTrade(new ItemStack(Material.STONE, 10), new ItemStack(Material.OAK_PLANKS, 1), new ItemStack(Material.COBBLESTONE, 1));
        return CommandManager.CommandFinished.DONE;
    }

}

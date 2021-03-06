/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.codingpirates.cpdemo6;

import dk.codingpirates.lib.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author maguhos
 * Extends CommandManager, som laver autmatisk test for antal og type af paramtre, der testes for permissions, 
 * der testes om det er OP eller CONSOLE eller PLAYER der kalder kommandoen. 
 * Der laves automatisk tab completion og hjælpetekster. Kommandoerne registreres automatisk i minecraft.
 */
public class MyCommands extends CommandManager {
    
    private final JavaPlugin plugin;
    
    public MyCommands(JavaPlugin plugin) {
        super(plugin, "CP6demo commands", "cp6demo.command", "cp6");
        this.plugin = plugin;
    }
    
    @CommandManager.Cmd(cmd = "test", args = "<navn> <price> [player]", 
            argTypes = {Arg.ArgString.class, Arg.ArgDouble.class, Arg.ArgPlayer.class},
            help = "test <navn> <price>", longhelp = "test <navn> <price>.",
            only = CommandManager.CommandOnly.OP, permission = "test")
    public static CommandManager.CommandFinished cmd_test(CommandSender sender, Object[] args) {
        Player player = (Player) sender;
        String name = args[0].toString();
        double price = (double) args[1];
        
        player.sendMessage("Du har tastet rigtigt " + name + " " + price);
        if (args.length>2 && args[2] != null) {
            Player p = (Player) args[2];
            player.sendMessage("Du har tastet "+p.getName());
        }
        return CommandManager.CommandFinished.DONE;   
    }
    
}

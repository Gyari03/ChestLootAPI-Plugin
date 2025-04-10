package com.github.gyari03.LootRandomizer.chestLootRandomizer.commands;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.Coordinate3D;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;

public class AddTableCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Coordinate3D chestLocation;

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "You did not specify any arguments!");
                return false;
            } else if (args.length == 3) {
                int coordX = Integer.parseInt(args[0]);
                int coordY = Integer.parseInt(args[1]);
                int coordZ = Integer.parseInt(args[2]);
                chestLocation = new Coordinate3D(coordX, coordY, coordZ);
            }else{
                player.sendMessage(ChatColor.RED + "Please specify a valid argument!");
                return false;
            }

            Block block = player.getWorld().getBlockAt(chestLocation.getX(), chestLocation.getY(), chestLocation.getZ());


            if(!(block.getState() instanceof Chest)) {
                player.sendMessage(ChatColor.RED + chestLocation.toString() +": Not a chest");
                return false;
            }

            Chest chest = (Chest) block.getState();

            //emptyChest(chestLocation, player);


            //chest.getBlockInventory().clear(); ITS NOT WORKING AGHHHHHHHHH
            chest.setLootTable(LootTables.BASTION_TREASURE.getLootTable());
            chest.update();
            player.sendMessage(ChatColor.DARK_GREEN + chestLocation.toString() +": Loot table added");


        }

        return true;
    }
/*
    public static void emptyChest(Coordinate3D chestLocation, Player player){ //NEITHER THIS BITCH
        Block block = player.getWorld().getBlockAt(chestLocation.getX(), chestLocation.getY(), chestLocation.getZ());

        if(!(block.getState() instanceof Chest chest)) {
            player.sendMessage(ChatColor.RED + chestLocation.toString() +": Not a chest");
            return;
        }
        ((Chest)player.getWorld().getBlockAt(chestLocation.getX(), chestLocation.getY(), chestLocation.getZ())).getBlockInventory().setContents(new ItemStack[]{});

        chest.update();

    }*/
}

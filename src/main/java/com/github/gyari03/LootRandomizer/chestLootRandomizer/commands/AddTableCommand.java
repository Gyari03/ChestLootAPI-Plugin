package com.github.gyari03.LootRandomizer.chestLootRandomizer.commands;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.Coordinate3D;
import org.bukkit.Bukkit;
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
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.bukkit.NamespacedKey;

public class AddTableCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Coordinate3D chestLocation;
            String lootTablenamespace;
            String lootTablekey;

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "You did not specify any arguments!");
                return false;
            } else if (args.length == 4) {
                int coordX = Integer.parseInt(args[0]);
                int coordY = Integer.parseInt(args[1]);
                int coordZ = Integer.parseInt(args[2]);
                String[] parts = args[3].split(":",2);
                lootTablenamespace = parts[0];
                lootTablekey = parts[1];
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

            NamespacedKey key = new NamespacedKey(lootTablenamespace, lootTablekey);
            LootTable customLootTable = Bukkit.getLootTable(key);
            if(customLootTable == null) {
                player.sendMessage(ChatColor.RED + chestLocation.toString() +": Not an existing loot table");
                return false;
            }
            chest.setLootTable(customLootTable);



            chest.update();
            player.sendMessage(ChatColor.DARK_GREEN + chestLocation.toString() +": Loot table added");


        }

        return true;
    }
}

package com.github.gyari03.LootRandomizer.chestLootRandomizer.commands;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.Coordinate3D;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;

public class InspectChestCommand implements CommandExecutor {

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

            if(!(block.getState() instanceof Chest)  && !(block.getState() instanceof Barrel)) {
                player.sendMessage(ChatColor.RED + chestLocation.toString() +": Not a chest");
                return false;
            }

            LootTable lootTable = null;
            Inventory inventory = null;
            if(block.getState() instanceof Chest) {
                Chest chest = (Chest) block.getState();
                lootTable = chest.getLootTable();
                inventory = chest.getInventory();
            }
            else if(block.getState() instanceof Barrel) {
                Barrel barrel = (Barrel) block.getState();
                lootTable = barrel.getLootTable();
                inventory = barrel.getInventory();
            }


            if(lootTable == null) {
                player.sendMessage(ChatColor.GOLD + chestLocation.toString() +": No loot table found");
            }else{
                NamespacedKey lootTableKey = lootTable.getKey();
                player.sendMessage(ChatColor.DARK_GREEN + chestLocation.toString() + ": " + lootTableKey.asString());
            }
            if(inventory != null) {
                for(ItemStack item : inventory.getContents()) {
                    if(item != null && item.getType() != Material.AIR) {
                        player.sendMessage(item.getAmount() + " x " + item.getType().name());
                    }
                }
            }
        }

        return true;
    }
}

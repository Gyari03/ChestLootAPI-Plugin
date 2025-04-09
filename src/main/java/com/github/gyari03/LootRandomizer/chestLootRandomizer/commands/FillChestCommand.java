package com.github.gyari03.LootRandomizer.chestLootRandomizer.commands;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.Coordinate3D;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.Collection;
import java.util.Random;

public class FillChestCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
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
            populateChest(chestLocation, player);
        }
        return true;
    }

    public static void populateChest(Coordinate3D chestLocation, Player player){
        Block block = player.getWorld().getBlockAt(chestLocation.getX(), chestLocation.getY(), chestLocation.getZ());

        if(!(block.getState() instanceof Chest chest)) {
            player.sendMessage(ChatColor.RED + chestLocation.toString() +": Not a chest");
            return;
        }

        if(chest.getLootTable() == null){
            player.sendMessage(ChatColor.RED + chestLocation.toString() +": was not populated");
            return;
        }
        LootTable lootTable = chest.getLootTable();
        LootContext context = new LootContext.Builder(chest.getLocation()).build();
        //2. way
        Collection<ItemStack> stacks= lootTable.populateLoot(new Random(),context);
        for(ItemStack stack : stacks){
            chest.getInventory().addItem(stack);
        }
        player.sendMessage(ChatColor.DARK_GREEN + chestLocation.toString() +": was populated");
    }
}

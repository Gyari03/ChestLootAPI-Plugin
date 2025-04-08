package com.github.gyari03.LootRandomizer.chestLootRandomizer.commands;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.Coordinate3D;
import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.SerializeJson;
import com.google.common.reflect.TypeToken;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.loot.LootTable;

import java.io.File;
import java.util.List;

public class ListChestsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            List<Coordinate3D> chestLocations = SerializeJson.loadListFromFile(new File("ChestLootRandomizer/chest_locations.json"),new TypeToken<List<Coordinate3D>>(){}.getType());

            for(Coordinate3D chestLocation : chestLocations) {
                Block block = player.getWorld().getBlockAt(chestLocation.getX(), chestLocation.getY(), chestLocation.getZ());
                if(!(block.getState() instanceof Chest)) {
                    player.sendMessage(ChatColor.DARK_GRAY + chestLocation.toString() +": Not a chest");
                    continue;
                }
                Chest chest = (Chest) block.getState();

                if(chest.getLootTable() == null) {
                    player.sendMessage(ChatColor.GOLD + chestLocation.toString() +": No loot table found");
                    continue;
                }
                LootTable lootTable = chest.getLootTable();
                NamespacedKey lootTableKey = lootTable.getKey();
                player.sendMessage(ChatColor.DARK_GREEN + chestLocation.toString() + ": " + lootTableKey.asString());

            }
        }
        return true;
    }
}

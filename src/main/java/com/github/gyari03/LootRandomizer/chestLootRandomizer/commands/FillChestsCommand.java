package com.github.gyari03.LootRandomizer.chestLootRandomizer.commands;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.Coordinate3D;
import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.SerializeJson;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
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
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.github.gyari03.LootRandomizer.chestLootRandomizer.util.SerializeJson.loadListFromFile;

public class FillChestsCommand implements CommandExecutor {
    List<Coordinate3D> chestLocations = new ArrayList<Coordinate3D>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            chestLocations = SerializeJson.loadListFromFile(new File("ChestLootRandomizer/chest_locations.json"),new TypeToken<List<Coordinate3D>>(){}.getType());

            for(Coordinate3D coord : chestLocations) {
                player.sendMessage(ChatColor.DARK_PURPLE + "Chest here:" + coord.toString());

                Block block = player.getWorld().getBlockAt(coord.getX(), coord.getY(), coord.getZ());

                // Chest check
                if(!(block.getState() instanceof Chest)) {
                    player.sendMessage(ChatColor.RED + "It is not actually chest!!");
                    continue;
                }
                Chest chest = (Chest) block.getState();

                if(chest.getLootTable() == null) {
                    player.sendMessage(ChatColor.RED + "It is a chest but NO loot table found!");
                    continue;
                }

                LootTable lootTable = chest.getLootTable();
                NamespacedKey lootTableKey = lootTable.getKey();
                player.sendMessage(ChatColor.DARK_PURPLE + coord.toString() + " LootTable: " + lootTableKey.asString());


            }
        }
        return true;
    }
}

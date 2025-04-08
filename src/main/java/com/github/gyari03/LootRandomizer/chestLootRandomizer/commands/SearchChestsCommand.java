package com.github.gyari03.LootRandomizer.chestLootRandomizer.commands;
import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.Coordinate3D;
import com.github.gyari03.LootRandomizer.chestLootRandomizer.util.SerializeJson;
import com.google.gson.Gson;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static com.github.gyari03.LootRandomizer.chestLootRandomizer.util.SerializeJson.saveListToFile;

public class SearchChestsCommand implements CommandExecutor {
    int fromX, fromY, fromZ;
    int toX, toY, toZ;
    List<Coordinate3D> chestLocations = new ArrayList<Coordinate3D>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                player.sendMessage(ChatColor.RED + "You did not specify any arguments!");
                return false;
            }
            else if(args.length == 6){
                fromX = Integer.parseInt(args[0]);
                fromY = Integer.parseInt(args[1]);
                fromZ = Integer.parseInt(args[2]);
                toX = Integer.parseInt(args[3]);
                toY = Integer.parseInt(args[4]);
                toZ =Integer.parseInt(args[5]);

                // Ha fromCoord nagyobb lenne mint toCoord az elrontja a 3-as loopot ezért:
                if (fromX > toX) {
                    int temp = fromX;
                    fromX = toX;
                    toX = temp;
                }
                if (fromY > toY) {
                    int temp = fromY;
                    fromY = toY;
                    toY = temp;
                }
                if (fromZ > toZ) {
                    int temp = fromZ;
                    fromZ = toZ;
                    toZ = temp;
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "Please specify a valid argument!");
                return false;
            }

            for(int i = fromX; i <= toX; i++){
                for(int j = fromY; j <= toY; j++){
                    for(int k = fromZ; k <= toZ; k++){
                        Block block = player.getWorld().getBlockAt(i, j, k);
                        if(block.getType() == Material.CHEST){
                            chestLocations.add(new Coordinate3D(i, j, k));
                            player.sendMessage(ChatColor.GREEN + "Found chest at: " + chestLocations.getLast().toString());
                        }
                    }
                }
            }
        }
        SerializeJson.saveListToFile(chestLocations,new File("ChestLootRandomizer/chest_locations.json"));
        return true;
    }
}

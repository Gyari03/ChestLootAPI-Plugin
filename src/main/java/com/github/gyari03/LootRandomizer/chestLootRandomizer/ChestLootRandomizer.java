package com.github.gyari03.LootRandomizer.chestLootRandomizer;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.commands.FillChestsCommand;
import com.github.gyari03.LootRandomizer.chestLootRandomizer.commands.InspectChestCommand;
import com.github.gyari03.LootRandomizer.chestLootRandomizer.commands.SearchChestsCommand;
import com.github.gyari03.LootRandomizer.chestLootRandomizer.commands.ListChestsCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ChestLootRandomizer extends JavaPlugin {

    @Override
    public void onEnable() {
        File directory = new File("ChestLootRandomizer");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        getLogger().info("ChestLootRandomizer has been enabled!");
        this.getCommand("search_chests").setExecutor(new SearchChestsCommand());
        this.getCommand("fill_chests").setExecutor(new FillChestsCommand());
        this.getCommand("list_chests").setExecutor(new ListChestsCommand());
        this.getCommand("inspect_chest").setExecutor(new InspectChestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

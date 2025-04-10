package com.github.gyari03.LootRandomizer.chestLootRandomizer;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.commands.*;
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
        this.getCommand("fill_chest").setExecutor(new FillChestCommand());
        this.getCommand("add_table").setExecutor(new AddTableCommand());
        this.getCommand("clear_chest").setExecutor(new ClearChestCommmand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

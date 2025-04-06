package com.github.gyari03.LootRandomizer.chestLootRandomizer;

import com.github.gyari03.LootRandomizer.chestLootRandomizer.commands.SearchChestsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChestLootRandomizer extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ChestLootRandomizer has been enabled!");
        this.getCommand("search_chests").setExecutor(new SearchChestsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

# ChestLootAPI Plugin

**ChestLootRandomizer** is a Minecraft 1.21.4 plugin that allows you to assign custom loot tables to chests within a specific region and search all chests within the region.

## 📦 Version
- `1.0-SNAPSHOT`
- API Version: `1.21.4`

## 🛠️ Requirements
- PaperMC 1.21.4 Server
- Minimum Java version: Java SE 21
- Gradle downloaded and installed

## 🏗️ Building the Jar File
1. Generate a `build.gradle` file using `generate_build_gradle.py`
2. Run `gradle clean build`

## ⚙️ Installation
1. Download the plugin from the releases on GitHub
2. Place the downloaded `.jar` file into your server's `plugins` folder.
3. Restart the server if it was running.

## 💬 Main Commands

| Command | Description | Usage | Permission |
|---------|-------------|-------|------------|
| `/search_chests <x1> <y1> <z1> <x2> <y2> <z2>` | Searches for chests in the given region. | `/<command> 0 64 0 100 80 100` | `permission.admin` |
| `/list_chests` | Lists the currently stored chests. | `/list_chests` | `permission.admin` |
| `/inspect_chest <x> <y> <z>` | Views the contents of a chest at the specified coordinates. | `/inspect_chest 10 65 10` | `permission.admin` |
| `/add_table <x> <y> <z> <namespace:path>` | Assigns a loot table to a chest. | `/add_table 10 65 10 myplugin:custom_loot` | `permission.admin` |

## 🔧 Commands for Debugging

| Command | Description | Usage | Permission |
|---------|-------------|-------|------------|
| `/fill_chests` | Fills previously found chests. It's better to let the contents generate when the chest is opened. | `/fill_chests` | `permission.admin` |
| `/fill_chest <x> <y> <z>` | Simulates (but not as accurately as vanilla generation) filling a specific chest with its assigned loot table. | `/fill_chest 10 65 10` | `permission.admin` |
| `/clear_chest <x> <y> <z>` | Empties a chest. | `/clear_chest 10 65 10` | `permission.admin` |

## 💡 Example Usage

1. Use the `/search_chests` command to scan an area for chests.
2. Use `/list_chests` to list all chests in the searched area and their loot tables.
3. Assign or change loot tables for the gathered chests using the `/add_table` command.
    - To load the official Minecraft loot table, use it like this: `minecraft:chests/simple_dungeon`
    - To use your own loot table, place a datapack into `Paper-server/world/datapacks`. An example datapack is included in the project, which you can place inside `/datapacks` and invoke with:  
      `/add_table <x> <y> <z> gyari:ExampleLootTable`
4. After adding a loot table to all chests on a map where you needed save the map, so you can re-use it for replayability.

## ❗ Notes
- Loot tables follow Minecraft’s internal format (e.g., `namespace:path`).
- By default, only players with `op` status can use the plugin. However, you can easily change this in the code and build the project yourself.
- `/clear_chest` replaces the original chest with an empty one due to limitations in Spigot.

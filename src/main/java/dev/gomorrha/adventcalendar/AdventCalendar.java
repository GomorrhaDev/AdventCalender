package dev.gomorrha.adventcalendar;

import dev.gomorrha.adventcalendar.commands.mainCommand;
import dev.gomorrha.adventcalendar.listeners.onInventoryClick;
import dev.gomorrha.adventcalendar.tools.getTime;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class AdventCalendar extends JavaPlugin {
    public static AdventCalendar instance;
    private HashMap<UUID, List<Integer>> usedDoors = new HashMap<>();
    FileConfiguration config = this.getConfig();
    File file = new File (getDataFolder(), "data.yml");
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onInventoryClick(), this);
        getCommand("adventcalender").setExecutor(new mainCommand());
        loadData();
        getTime timeGetter = new getTime();
        getServer().getLogger().info("[AdventCalender] Plugin loaded, today is the " + timeGetter.getInfo());
    }

    @Override
    public void onDisable() {
        saveData();
        getServer().getLogger().info("[AdventCalender] Everything saved!");
    }

    private void loadData() {
        if (file.exists()) {
            for (String key : cfg.getKeys(false)) {
                UUID playerUUID = UUID.fromString(key);
                List<Integer> playerDataList = cfg.getIntegerList(key);
                usedDoors.put(playerUUID, playerDataList);
            }
        }
    }

    private void saveData() {
        for (UUID playerUUID : usedDoors.keySet()) {
            String key = playerUUID.toString();
            List<Integer> playerDataList = usedDoors.get(playerUUID);
            cfg.set(key, playerDataList);
        }
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AdventCalendar getInstance() {
        return instance;
    }
    public HashMap<UUID, List<Integer>> getUsedDoors(){
        return usedDoors;
    }
}

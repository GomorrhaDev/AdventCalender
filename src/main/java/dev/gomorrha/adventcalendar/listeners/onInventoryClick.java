package dev.gomorrha.adventcalendar.listeners;

import dev.gomorrha.adventcalendar.AdventCalendar;
import dev.gomorrha.adventcalendar.tools.ColorTranslator;
import dev.gomorrha.adventcalendar.tools.getTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class onInventoryClick implements Listener {
    AdventCalendar main = AdventCalendar.getInstance();
    FileConfiguration config = main.getConfig();


    @EventHandler
    public void InventoryClick(InventoryClickEvent e){
        getTime timeGetter = new getTime();
        HashMap<UUID, List<Integer>> usedDoors = main.getUsedDoors();

        if(timeGetter.getMonth() == 12) {
            Player player = (Player) e.getWhoClicked();
            if (e.getView().getTitle().equals(ChatColor.BOLD + "" + ColorTranslator.translateColorCodes(config.getString("AdventCalenderName")))) {
                e.setCancelled(true);
                Integer day = timeGetter.getDay();
                Integer clicked = e.getSlot() + 1;
                if (clicked < 25) {
                    if(clicked <= day) {
                        if(!usedDoors.get(player.getUniqueId()).contains(clicked)) {
                            List<String> commands = config.getStringList("actions." + clicked);
                            for (String command : commands) {
                                command = ColorTranslator.translateColorCodes(command);
                                command = command.replace("%player%", player.getName());

                                if (command.contains("sendToPlayer")) {
                                    command = command.replace("sendToPlayer ", "");
                                    player.sendMessage(command);
                                } else {
                                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                    Bukkit.dispatchCommand(console, command);
                                }
                            }
                            player.closeInventory();
                            if(usedDoors.containsKey(player.getUniqueId())){
                                List<Integer> usedDoorList = usedDoors.get(player.getUniqueId());
                                usedDoorList.add(clicked);
                                usedDoors.put(player.getUniqueId(), usedDoorList);
                            } else {
                                List<Integer> usedDoorList = new ArrayList<>();
                                usedDoorList.add(clicked);
                                usedDoors.put(player.getUniqueId(), usedDoorList);
                            }
                        }
                    }
                }

            }
        }

    }
}

package dev.gomorrha.adventcalendar.commands;

import dev.gomorrha.adventcalendar.AdventCalendar;
import dev.gomorrha.adventcalendar.tools.ColorTranslator;
import dev.gomorrha.adventcalendar.tools.getTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class mainCommand implements CommandExecutor {
    getTime timeGetter = new getTime();
    AdventCalendar main = AdventCalendar.getInstance();
    FileConfiguration config = main.getConfig();
    HashMap<UUID, List<Integer>> usedDoors = main.getUsedDoors();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (timeGetter.getMonth() == 12 && timeGetter.getDay() <= 24) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                openCalender(p);
            }
        }
        return false;
    }

    public void openCalender(Player p){
        Inventory inv = Bukkit.createInventory(null, 3*9, ChatColor.BOLD + "" + ColorTranslator.translateColorCodes(config.getString("AdventCalenderName")));
        Integer day = timeGetter.getDay();
        if (!usedDoors.containsKey(p.getUniqueId())){
            List<Integer> binList = new ArrayList<>();
            binList.add(0);
            usedDoors.put(p.getUniqueId(), binList);
        }

        for (int i = 1; i <= 24; i++) {
            String path = "doorNames." + i;
            if (config.contains(path)) {
                if(day >= i && !usedDoors.get(p.getUniqueId()).contains(i)) {

                    ItemStack door = new ItemStack(Material.OAK_DOOR);
                    ItemMeta doorMeta = door.getItemMeta();
                    doorMeta.setDisplayName(ColorTranslator.translateColorCodes(config.getString(path)));
                    doorMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    doorMeta.setLore(null);
                    doorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    door.setItemMeta(doorMeta);
                    inv.setItem(i - 1, door);

                } else if (day < i){

                    ItemStack Iedoor = new ItemStack(Material.IRON_DOOR);
                    ItemMeta IedoorMeta = Iedoor.getItemMeta();
                    IedoorMeta.setDisplayName(ColorTranslator.translateColorCodes(config.getString(path)));
                    IedoorMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    IedoorMeta.setLore(null);
                    IedoorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    Iedoor.setItemMeta(IedoorMeta);
                    inv.setItem(i - 1, Iedoor);

                } else {

                    ItemStack Idoor = new ItemStack(Material.IRON_DOOR);
                    ItemMeta IdoorMeta = Idoor.getItemMeta();
                    IdoorMeta.setDisplayName(ColorTranslator.translateColorCodes(config.getString(path)));
                    Idoor.setItemMeta(IdoorMeta);
                    inv.setItem(i - 1, Idoor);
                }
            }
        }
        p.openInventory(inv);
    }
}

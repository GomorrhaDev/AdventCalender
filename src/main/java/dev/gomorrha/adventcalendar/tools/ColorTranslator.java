package dev.gomorrha.adventcalendar.tools;

import net.md_5.bungee.api.ChatColor;

public class ColorTranslator {
    public static String translateColorCodes(String input) {
        input = input.replaceAll("&4", ChatColor.DARK_RED.toString());
        input = input.replaceAll("&c", ChatColor.RED.toString());
        input = input.replaceAll("&6", ChatColor.GOLD.toString());
        input = input.replaceAll("&e", ChatColor.YELLOW.toString());
        input = input.replaceAll("&2", ChatColor.DARK_GREEN.toString());
        input = input.replaceAll("&a", ChatColor.GREEN.toString());
        input = input.replaceAll("&b", ChatColor.AQUA.toString());
        input = input.replaceAll("&3", ChatColor.DARK_AQUA.toString());
        input = input.replaceAll("&1", ChatColor.DARK_BLUE.toString());
        input = input.replaceAll("&9", ChatColor.BLUE.toString());
        input = input.replaceAll("&d", ChatColor.LIGHT_PURPLE.toString());
        input = input.replaceAll("&5", ChatColor.DARK_PURPLE.toString());
        input = input.replaceAll("&f", ChatColor.WHITE.toString());
        input = input.replaceAll("&7", ChatColor.GRAY.toString());
        input = input.replaceAll("&8", ChatColor.DARK_GRAY.toString());
        input = input.replaceAll("&0", ChatColor.BLACK.toString());
        input = input.replaceAll("&r", ChatColor.RESET.toString());
        input = input.replaceAll("&l", ChatColor.BOLD.toString());
        input = input.replaceAll("&o", ChatColor.ITALIC.toString());
        input = input.replaceAll("&n", ChatColor.UNDERLINE.toString());
        input = input.replaceAll("&m", ChatColor.STRIKETHROUGH.toString());
        input = input.replaceAll("&k", ChatColor.MAGIC.toString());

        return input;
    }
}

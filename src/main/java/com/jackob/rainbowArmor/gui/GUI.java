package com.jackob.rainbowArmor.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {

    private Player player;

    public GUI(Player player) {
        this.player = player;

        player.openInventory(createAnimationsMenu());
    }

    public static int getSpeed(Inventory inv) {
        int speed = 1;

        for (int i = 19; i < 27; i++) {
            if (inv.getItem(i).getType() != Material.RED_WOOL) {
                break;
            }

            speed++;
        }

        return speed;
    }

    public static void changeSpeedIcons(Inventory inv, int maxIndex) {
        int index = changeWoolIcons(inv, 18, maxIndex,1, Material.RED_WOOL, ChatColor.RED);

        changeWoolIcons(inv, maxIndex + 1, 27, index, Material.WHITE_WOOL, ChatColor.WHITE);
    }

    private static int changeWoolIcons(Inventory inv, int start, int end, int index, Material type, ChatColor woolColor) {
        for (int i = start; i <= end; i++) {
            inv.setItem(i, createIcon(type, woolColor + "" + ChatColor.BOLD + index));
            index++;
        }

        return index;
    }

    private Inventory createAnimationsMenu() {
        Inventory inv = Bukkit.createInventory(player, 36,ChatColor.DARK_PURPLE + "" + ChatColor.UNDERLINE + ChatColor.BOLD + "Animation picker");

        addAnimationButtons(inv);
        addSpeedButtons(inv);
        inv.setItem(35, createIcon(Material.RED_STAINED_GLASS_PANE, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Turn off"));
        addFillerIcons(inv);

        return inv;
    }

    private void addAnimationButtons(Inventory inv) {
        int index = 0;
        for (int i = 1; i <= 17; i += 2) {
            inv.setItem(i, createIcon(Material.CHAINMAIL_CHESTPLATE, ChatColor.BOLD + getNames()[index]));
            index++;
        }
    }

    private void addSpeedButtons(Inventory inv) {
        inv.setItem(18, createIcon(Material.RED_WOOL, ChatColor.RED + "" + ChatColor.BOLD + 1));

        changeWoolIcons(inv, 19,27,2,Material.WHITE_WOOL, ChatColor.RED);
    }

    private void addFillerIcons(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, createIcon(Material.BLACK_STAINED_GLASS_PANE,ChatColor.GRAY + "" + ChatColor.BOLD + "Info"));
            }
        }
    }

    private String[] getNames() {
        return new String[]{"Classic", "Chaotic", "Smooth",
                "Topbottom", "Fromtop", "Frombottom",
                "Smoothred", "Smoothgreen", "Smoothblue"};
    }

    private static ItemStack createIcon(Material type, String name) {
        ItemStack item = new ItemStack(type);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
    }

}
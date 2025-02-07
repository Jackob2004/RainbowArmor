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

    private Inventory createAnimationsMenu() {
        Inventory inv = Bukkit.createInventory(player, 36,ChatColor.DARK_PURPLE + "" + ChatColor.UNDERLINE + ChatColor.BOLD + "Animation picker");

        addAnimationButtons(inv);
        addSpeedButtons(inv);
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
        int index = 1;
        for (int i = 18; i <= 27    ; i++) {
            inv.setItem(i, createIcon(Material.WHITE_WOOL, ChatColor.RED + "" + ChatColor.BOLD + index));
            index++;
        }
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

    private ItemStack createIcon(Material type, String name) {
        ItemStack item = new ItemStack(type);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
    }

}

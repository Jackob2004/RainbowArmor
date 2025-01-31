package com.jackob.rainbowArmor.manager;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RainbowManager {

    private final String messageIndicator = "[Rainbow Armor] ";

    public void activateRainbow(Player player) {
        equipLeatherArmor(player);
    }

    private ItemStack[] equipLeatherArmor(Player player) {
        ItemStack[] leatherArmor = createLeatherArmor();
        
        if (hasNoArmor(player)) {
            player.getInventory().setArmorContents(leatherArmor);
            
            return leatherArmor;
        } else {
            player.sendMessage(messageIndicator + ChatColor.GRAY + "You need to remove your current armor");
        }
        
        return null;
    }

    private boolean hasNoArmor(Player player) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null) {
                return false;
            }
        }

        return true;
    }

    private ItemStack[] createLeatherArmor() {
        ItemStack[] armor = new ItemStack[4];

        armor[0] = new ItemStack(Material.LEATHER_BOOTS);
        armor[1] = new ItemStack(Material.LEATHER_LEGGINGS);
        armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
        armor[3] = new ItemStack(Material.LEATHER_HELMET);

        return armor;
    }

}

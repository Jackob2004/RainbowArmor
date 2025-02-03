package com.jackob.rainbowArmor.manager;

import com.jackob.rainbowArmor.RainbowArmor;
import com.jackob.rainbowArmor.task.AnimationTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class RainbowManager {

    private RainbowArmor plugin;

    public RainbowManager(RainbowArmor plugin) {
        this.plugin = plugin;
    }

    private final String messageIndicator = "[Rainbow Armor] ";

    public void activateRainbow(Player player) {
        ItemStack[] armor = equipLeatherArmor(player);

        if (armor != null) {
          //  BukkitTask animationTask = new AnimationTask().runTaskTimer(plugin, 1, 3);
        }

    }

    private ItemStack[] equipLeatherArmor(Player player) {
        if (hasNoArmor(player)) {
            player.getInventory().setArmorContents(createLeatherArmor());
            
            return player.getInventory().getArmorContents();
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

package com.jackob.rainbowArmor.manager;

import com.jackob.rainbowArmor.RainbowArmor;
import com.jackob.rainbowArmor.animation.*;
import com.jackob.rainbowArmor.task.AnimationTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RainbowManager {

    private RainbowArmor plugin;

    private Map<UUID, BukkitTask> taskMap;

    private final String messageIndicator = "[Rainbow Armor] ";

    public RainbowManager(RainbowArmor plugin) {
        this.plugin = plugin;
        this.taskMap = new HashMap<>();
    }

    public void animation(Player player) {

        if (rainbowActivated(player)) {
            deactivateRainbow(player);
        } else {
            activateRainbow(player);
        }

    }

    public ItemStack[] stopAnimation(Player player) {
        if (rainbowActivated(player)) {
            return deactivateRainbow(player);
        }

        return null;
    }

    public boolean rainbowActivated(Player player) {
        return taskMap.containsKey(player.getUniqueId());
    }

    private void activateRainbow(Player player) {
        ItemStack[] armor = equipLeatherArmor(player);

        if (armor != null) {
            BukkitTask animationTask = new AnimationTask(new SmoothOneColorAnimation(armor, player, SmoothOneColorAnimation.SmoothColorType.RED)).runTaskTimer(plugin, 1, 2);
            taskMap.put(player.getUniqueId(), animationTask);

            player.sendMessage(messageIndicator + ChatColor.GREEN + "rainbow has been activated");
        }
    }

    private ItemStack[] deactivateRainbow(Player player) {
        UUID uuid = player.getUniqueId();

        taskMap.get(uuid).cancel();
        taskMap.remove(uuid);

        ItemStack[] armor = player.getInventory().getArmorContents();

        player.getInventory().setArmorContents(new ItemStack[]{});
        player.sendMessage(messageIndicator + ChatColor.RED + "rainbow has been deactivated");

        return armor;
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
        setUnbreakable(armor);

        return armor;
    }

    private void setUnbreakable(ItemStack[] armor) {
        for (ItemStack item : armor) {
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setUnbreakable(true);
            item.setItemMeta(itemMeta);
        }
    }

}

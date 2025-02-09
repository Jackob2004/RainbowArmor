package com.jackob.rainbowArmor.listener;

import com.jackob.rainbowArmor.gui.GUI;
import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerMenuListener implements Listener {

    private RainbowManager manager;

    public PlayerMenuListener(RainbowManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.DARK_PURPLE + "" + ChatColor.UNDERLINE + ChatColor.BOLD + "Animation picker") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            switch (e.getRawSlot()) {
                case 1,3,5,7,9,11,13,15,17 -> {
                    manager.chosenAnimation(player,ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()), GUI.getSpeed(e.getClickedInventory()));
                    player.closeInventory();
                }
                case 18,19,20,21,22,23,24,25,26,27 -> {
                    GUI.changeSpeedIcons(e.getClickedInventory(), e.getRawSlot());
                }
                case 35 -> {
                    manager.stopAnimation(player);
                    player.closeInventory();
                }
            }
        }
    }


}
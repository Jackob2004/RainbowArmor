package com.jackob.rainbowArmor.listener;

import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ArmorMeta;


public class PlayerSlotsListener implements Listener {

    private RainbowManager manager;

    public PlayerSlotsListener(RainbowManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getClickedInventory().getType() == InventoryType.PLAYER && manager.rainbowActivated((Player) e.getWhoClicked())) {
            if (e.getRawSlot() == 5 || e.getRawSlot() == 6 || e.getRawSlot() == 7 || e.getRawSlot() == 8) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onArmorSwitch(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (manager.rainbowActivated(player)) {
            if ((player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getItemMeta() instanceof ArmorMeta)
                || (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().getItemMeta() instanceof ArmorMeta)) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    e.setCancelled(true);
                }
            }
        }
    }

}

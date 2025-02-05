package com.jackob.rainbowArmor.listener;

import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PlayerDeathListener implements Listener {

    private RainbowManager manager;

    public PlayerDeathListener(RainbowManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        ItemStack[] armor = manager.stopAnimation(e.getEntity());

        if (armor != null) {
            e.getDrops().removeAll(Arrays.stream(armor).toList());
        }
    }


}

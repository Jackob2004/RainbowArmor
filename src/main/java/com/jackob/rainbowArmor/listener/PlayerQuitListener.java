package com.jackob.rainbowArmor.listener;

import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private RainbowManager manager;

    public PlayerQuitListener(RainbowManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        manager.onQuit(event.getPlayer());
    }
}

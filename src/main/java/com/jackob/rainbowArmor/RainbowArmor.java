package com.jackob.rainbowArmor;

import com.jackob.rainbowArmor.command.ActivateCommand;
import com.jackob.rainbowArmor.listener.PlayerDeathListener;
import com.jackob.rainbowArmor.listener.PlayerQuitListener;
import com.jackob.rainbowArmor.listener.PlayerSlotsListener;
import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RainbowArmor extends JavaPlugin {

    @Override
    public void onEnable() {
        RainbowManager manager = new RainbowManager(this);

        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(manager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSlotsListener(manager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(manager), this);

        getCommand("rainbow").setExecutor(new ActivateCommand(manager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
        TODO:
            - /rainbow: (toggle)
                - more animations types
                - animation speed
                - commands for choosing desired animation and speed
                - command tab complete
                - gui for choosing desired animation and speed
                - config for default animation and speed
                - clickable/hoverable commands output
     */
}

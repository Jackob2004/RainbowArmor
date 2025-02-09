package com.jackob.rainbowArmor;

import com.jackob.rainbowArmor.command.ActivateCommand;
import com.jackob.rainbowArmor.command.RainbowTab;
import com.jackob.rainbowArmor.listener.PlayerDeathListener;
import com.jackob.rainbowArmor.listener.PlayerMenuListener;
import com.jackob.rainbowArmor.listener.PlayerQuitListener;
import com.jackob.rainbowArmor.listener.PlayerSlotsListener;
import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RainbowArmor extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        RainbowManager manager = new RainbowManager(this);

        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(manager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSlotsListener(manager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(manager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMenuListener(manager), this);

        getCommand("rainbow").setExecutor(new ActivateCommand(manager));
        getCommand("rainbow").setTabCompleter(new RainbowTab());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
        TODO:
                - clickable/hoverable commands output
     */
}

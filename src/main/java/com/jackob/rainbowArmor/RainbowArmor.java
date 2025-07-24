package com.jackob.rainbowArmor;

import com.jackob.rainbowArmor.command.ActivateCommand;
import com.jackob.rainbowArmor.command.RainbowTab;
import com.jackob.rainbowArmor.listener.PlayerDeathListener;
import com.jackob.rainbowArmor.listener.PlayerMenuListener;
import com.jackob.rainbowArmor.listener.PlayerQuitListener;
import com.jackob.rainbowArmor.listener.PlayerSlotsListener;
import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RainbowArmor extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        RainbowManager manager = new RainbowManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerQuitListener(manager), this);
        pluginManager.registerEvents(new PlayerSlotsListener(manager), this);
        pluginManager.registerEvents(new PlayerDeathListener(manager), this);
        pluginManager.registerEvents(new PlayerMenuListener(manager), this);

        getCommand("rainbow").setExecutor(new ActivateCommand(manager));
        getCommand("rainbow").setTabCompleter(new RainbowTab());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
        TODO:
            - refactor
     */
}

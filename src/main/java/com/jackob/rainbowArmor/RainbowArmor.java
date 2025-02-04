package com.jackob.rainbowArmor;

import com.jackob.rainbowArmor.command.ActivateCommand;
import com.jackob.rainbowArmor.listener.PlayerQuitListener;
import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RainbowArmor extends JavaPlugin {

    @Override
    public void onEnable() {
        RainbowManager manager = new RainbowManager(this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(manager), this);
        getCommand("rainbow").setExecutor(new ActivateCommand(manager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
        TODO:
            - /rainbow: (toggle)
                - edge cases like create mode


                - start animation task
                - stop animation on toggle remove leather armor
                - prevent player from changing/drooping armor when animation is activated
                - messages
                - edge cases
     */
}

package com.jackob.rainbowArmor;

import com.jackob.rainbowArmor.command.ActivateCommand;
import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RainbowArmor extends JavaPlugin {

    @Override
    public void onEnable() {
        RainbowManager manager = new RainbowManager(this);
        getCommand("rainbow").setExecutor(new ActivateCommand(manager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
        TODO:
            - /rainbow: (toggle)
                - equip leather armor if player has no armor
                - start animation task
                - stop animation on toggle remove leather armor
                - prevent player from changing/drooping armor when animation is activated
                - messages
                - edge cases
     */
}

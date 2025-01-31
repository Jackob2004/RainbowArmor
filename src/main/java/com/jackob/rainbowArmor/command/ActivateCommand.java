package com.jackob.rainbowArmor.command;

import com.jackob.rainbowArmor.manager.RainbowManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ActivateCommand implements CommandExecutor {

    private RainbowManager manager;

    public ActivateCommand(RainbowManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            manager.activateRainbow(player);

            return true;
        }

        return false;
    }
}

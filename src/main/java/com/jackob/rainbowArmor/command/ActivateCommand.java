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

            if (args.length == 1 && args[0].equals("help")) {
                manager.displayHelpMessage(player);
            } else if (args.length == 1 && args[0].equals("menu")) {
                manager.openMenu(player);
            } else if (args.length == 1) {
                String animationName = args[0];

                manager.chosenAnimation(player, animationName);
            } else if (args.length == 2) {
                String animationName = args[0];
                String number = args[1];

                manager.chosenAnimation(player, animationName,getNumber(number));
            } else {
                manager.animation(player);
            }

            return true;
        }

        return false;
    }

    private int getNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 1;
        }

    }
}

package com.jackob.rainbowArmor.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RainbowTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], getNames(), new ArrayList<>());
        }

        if (args.length == 2) {
            return getSpeeds();
        }

        return new ArrayList<>();
    }

    private List<String> getNames() {
        return Arrays.asList("classic", "chaotic", "topbottom",
                "fromtop", "frombottom", "smoothred",
                "smoothgreen", "smoothblue", "smooth");
    }

    private List<String> getSpeeds() {
        List<String> speeds = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            speeds.add(i + "");
        }

        return speeds;
    }

}

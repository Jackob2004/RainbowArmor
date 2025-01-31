package com.jackob.rainbowArmor.manager;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.Random;

public class RainbowManager {

    public void activateRainbow(Player player) {

    }

    private Color randomColor() {
        Random random = new Random();

        return Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

}

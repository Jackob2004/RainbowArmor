package com.jackob.rainbowArmor.animation;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ClassicAnimation extends Animation{
    public ClassicAnimation(ItemStack[] armorPieces, Player player) {
        super(armorPieces, player);
    }

    public static Color randomColor() {
        Random random = new Random();

        return Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    @Override
    public void animate() {
        super.animate();

        changeArmorColor(randomColor());
    }
}

package com.jackob.rainbowArmor.animation;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SmoothOneColorAnimation extends Animation {

    ColorProcessor processor;

    private SmoothColorType type;
    public SmoothOneColorAnimation(ItemStack[] armorPieces, Player player, SmoothColorType type) {
        super(armorPieces, player);
        this.processor = new ColorProcessor(5);
        this.type = type;
    }

    private Color getColor() {
        return switch (type) {
            case RED -> Color.fromRGB(processor.getNumber(), 0, 0);
            case GREEN ->  Color.fromRGB(0, processor.getNumber(), 0);
            case BLUE -> Color.fromRGB(0,0, processor.getNumber());
        };
    }

    @Override
    public void animate() {
        super.animate();

        changeArmorColor(getColor());
        processor.changeBrightness();
    }

    public enum SmoothColorType {
        RED,
        GREEN,
        BLUE;
    }
}

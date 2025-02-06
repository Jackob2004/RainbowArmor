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
            case SMOOTHRED -> Color.fromRGB(processor.getNumber(), 0, 0);
            case SMOOTHGREEN ->  Color.fromRGB(0, processor.getNumber(), 0);
            case SMOOTHBLUE -> Color.fromRGB(0,0, processor.getNumber());
        };
    }

    @Override
    public void animate() {
        super.animate();

        changeArmorColor(getColor());
        processor.changeBrightness();
    }

    public enum SmoothColorType {
        SMOOTHRED,
        SMOOTHGREEN,
        SMOOTHBLUE;
    }
}

package com.jackob.rainbowArmor.animation;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;

public class SmoothAnimation extends Animation {

    private ColorProcessor[] colorProcessors;

    private int colorPointer;

    public SmoothAnimation(ItemStack[] armorPieces) {
        super(armorPieces);

        this.colorProcessors = new ColorProcessor[]{new ColorProcessor(5), new ColorProcessor(5), new ColorProcessor(5)};
        this.colorPointer = 0;
    }

    public void transferColor() {
        if (colorProcessors[colorPointer].changeBrightness()) {

            if (colorProcessors[getPrevious()].getNumber() == 0) {

                movePointer();

            } else {
                colorPointer = getPrevious();
            }

        }
    }

    public void movePointer() {
        do {
            colorPointer = getNext();
        } while (colorProcessors[colorPointer].getNumber() == 255);
    }

    private int getNext() {
        return (colorPointer + 1 > 2) ? 0 : colorPointer + 1;
    }

    private int getPrevious() {
        return (colorPointer -1  < 0) ? 2 : colorPointer - 1;
    }

    public Color getColor() {
        return Color.fromRGB(colorProcessors[0].getNumber(), colorProcessors[1].getNumber(), colorProcessors[2].getNumber());
    }

    @Override
    public void animate() {
        transferColor();
        changeArmorColor(getColor());
    }


}
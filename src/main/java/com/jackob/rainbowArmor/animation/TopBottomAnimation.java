package com.jackob.rainbowArmor.animation;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class TopBottomAnimation extends Animation{

    private int armorPart;

    private int direction;

    private TopBottomType type;
    public TopBottomAnimation(ItemStack[] armorPieces, Player player, TopBottomType type) {
        super(armorPieces, player);

        this.armorPart = 3;
        this.direction = -1;
        this.type = type;
    }

    public void moveArmorPart() {
        armorPart += direction;

        if (armorPart < 0 || armorPart > 3) {
            direction *= -1;
            armorPart += direction;
        }
    }

    public void moveArmorPart(Function<Integer, Integer> restorePosition, int direction) {
        armorPart += direction;

        armorPart = restorePosition.apply(armorPart);
    }

    @Override
    protected void changeArmorColor(Color color) {
        ItemStack armorPiece = getArmorPieces()[armorPart];
        setArmorPieceColor(color, armorPiece);

        switch (type) {
            case Default -> moveArmorPart();
            case FromTop -> moveArmorPart(integer -> (integer < 0) ? 3 : integer,-1);
            case FromBottom -> moveArmorPart(integer -> (integer > 3) ? 0 : integer,1);
        }
    }

    @Override
    public void animate() {
        super.animate();

        changeArmorColor(ClassicAnimation.randomColor());
    }

    public enum TopBottomType {
        Default,
        FromTop,
        FromBottom;

    }

}

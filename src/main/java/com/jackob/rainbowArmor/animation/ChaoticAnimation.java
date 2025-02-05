package com.jackob.rainbowArmor.animation;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChaoticAnimation extends Animation{
    public ChaoticAnimation(ItemStack[] armorPieces, Player player) {
        super(armorPieces, player);
    }

    @Override
    protected void changeArmorColor(Color color) {
        for (ItemStack item : getArmorPieces()) {
            setArmorPieceColor(ClassicAnimation.randomColor(), item);
        }
    }

    @Override
    public void animate() {
        super.animate();

        changeArmorColor(null);
    }

}

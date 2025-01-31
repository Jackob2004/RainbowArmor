package com.jackob.rainbowArmor.task;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class AnimationTask extends BukkitRunnable {

    private ItemStack[] armorPieces;

    public AnimationTask(ItemStack[] armorPieces) {
        this.armorPieces = armorPieces;
    }

    @Override
    public void run() {
        changeArmorColor();
    }

    private void changeArmorColor() {
        Color color = randomColor();

        for (ItemStack item : armorPieces) {
            LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();

            armorMeta.setColor(color);
        }
    }

    private Color randomColor() {
        Random random = new Random();

        return Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

}

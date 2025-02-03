package com.jackob.rainbowArmor.animation;

import org.bukkit.inventory.ItemStack;

public class Animation implements AnimationBehavior {

    private ItemStack[] armorPieces;

    public Animation(ItemStack[] armorPieces) {
        this.armorPieces = armorPieces;
    }

    public ItemStack[] getArmorPieces() {
        return armorPieces;
    }

    public void setArmorPieces(ItemStack[] armorPieces) {
        this.armorPieces = armorPieces;
    }

    @Override
    public void animate() {

    }

    //    private void changeArmorColor(Color color) {
//        for (ItemStack item : armorPieces) {
//            LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();
//
//            armorMeta.setColor(color);
//            item.setItemMeta(armorMeta);
//        }
//    }
//
//    private Color getCustomColor(int blue) {
//        return Color.fromRGB(0,0, blue);
//    }

//    private Color randomColor() {
//        Random random = new Random();
//
//        return Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
//    }
}

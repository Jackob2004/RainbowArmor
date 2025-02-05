package com.jackob.rainbowArmor.animation;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public abstract class Animation implements AnimationBehavior {

    private ItemStack[] armorPieces;

    private Player player;

    public Animation(ItemStack[] armorPieces, Player player) {
        this.armorPieces = armorPieces;
        this.player = player;
    }

    public ItemStack[] getArmorPieces() {
        return armorPieces;
    }

    public void setArmorPieces(ItemStack[] armorPieces) {
        this.armorPieces = armorPieces;
    }

    protected void changeArmorColor(Color color) {
        for (ItemStack item : armorPieces) {
            setArmorPieceColor(color, item);
        }
    }

    protected void setArmorPieceColor(Color color, ItemStack item) {
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();

        armorMeta.setColor(color);
        item.setItemMeta(armorMeta);
    }

    @Override
    public void animate() {
        if (player.getGameMode() == GameMode.CREATIVE && player.getOpenInventory().getTopInventory().getType() == InventoryType.CRAFTING) {
            armorPieces = player.getInventory().getArmorContents();
        }
    }

}

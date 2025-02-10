package com.jackob.rainbowArmor.manager;

import com.jackob.rainbowArmor.RainbowArmor;
import com.jackob.rainbowArmor.animation.*;
import com.jackob.rainbowArmor.gui.GUI;
import com.jackob.rainbowArmor.task.AnimationTask;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RainbowManager {

    private RainbowArmor plugin;

    private Map<UUID, BukkitTask> taskMap;

    private final String messageIndicator;

    private final String defaultAnimation;

    private final int defaultSpeed;

    public RainbowManager(RainbowArmor plugin) {
        this.plugin = plugin;
        this.taskMap = new HashMap<>();

        this.messageIndicator = plugin.getConfig().getString("plugin-message-indicator");
        this.defaultAnimation = plugin.getConfig().getString("default-animation");
        this.defaultSpeed = plugin.getConfig().getInt("default-speed");
    }

    public void displayHelpMessage(Player player) {
        player.sendMessage( ChatColor.BOLD + "===============");
        player.sendMessage(messageIndicator + "\n");
        player.sendMessage("");

        sendTextComponent(player, "/rainbow", "§bClick to toggle animation", ClickEvent.Action.RUN_COMMAND);
        sendTextComponent(player, "/rainbow menu", "§bClick to open menu", ClickEvent.Action.RUN_COMMAND);
        sendTextComponent(player, "/rainbow classic","/rainbow <animation_name>", "§3Click auto type command", ClickEvent.Action.SUGGEST_COMMAND);
        sendTextComponent(player, "/rainbow classic 5","/rainbow <animation_name> <speed>", "§3Click auto type command", ClickEvent.Action.SUGGEST_COMMAND);

        player.sendMessage("");
        player.sendMessage( ChatColor.BOLD + "===============");
    }

    public void openMenu(Player player) {
        new GUI(player);
    }

    public void animation(Player player) {

        if (rainbowActivated(player)) {
            deactivateRainbow(player);
        } else {
            activateRainbow(player);
        }

    }

    public void chosenAnimation(Player player, String animationName) {
        chosenAnimation(player, animationName, 2);
    }

    public void chosenAnimation(Player player, String animationName, int speed) {
        if (rainbowActivated(player)) {
            taskMap.get(player.getUniqueId()).cancel();
        }

        activateRainbow(player, animationName, speed);
    }

    private AnimationBehavior getAnimation(String animationName, ItemStack[] armor, Player player) {
        return switch (animationName.toLowerCase()) {
            case "classic" -> new ClassicAnimation(armor, player);
            case "chaotic" -> new ChaoticAnimation(armor, player);
            case "topbottom", "fromtop", "frombottom" -> new TopBottomAnimation(armor, player, TopBottomAnimation.TopBottomType.valueOf(animationName.toUpperCase()));
            case "smoothred", "smoothgreen", "smoothblue" -> new SmoothOneColorAnimation(armor, player, SmoothOneColorAnimation.SmoothColorType.valueOf(animationName.toUpperCase()));
            default -> new SmoothAnimation(armor, player);
        };
    }

    private int getSpeed(int speed) {
        if (speed > 10) {
            return 10;
        } else if (speed < 1) {
            return 1;
        }

        return speed;
    }

    public ItemStack[] stopAnimation(Player player) {
        if (rainbowActivated(player)) {
            return deactivateRainbow(player);
        }

        return null;
    }

    public boolean rainbowActivated(Player player) {
        return taskMap.containsKey(player.getUniqueId());
    }

    private void activateRainbow(Player player) {
        activateRainbow(player, defaultAnimation, defaultSpeed);
    }

    private void activateRainbow(Player player, String animationName, int speed) {
        ItemStack[] armor = equipLeatherArmor(player);

        if (armor != null) {
            BukkitTask animationTask = new AnimationTask(getAnimation(animationName, armor, player)).runTaskTimer(plugin, 1, getSpeed(speed));
            taskMap.put(player.getUniqueId(), animationTask);

            player.sendMessage(messageIndicator + ChatColor.GREEN + "rainbow has been activated");
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE,1,1);
        }
    }

    private ItemStack[] deactivateRainbow(Player player) {
        UUID uuid = player.getUniqueId();

        taskMap.get(uuid).cancel();
        taskMap.remove(uuid);

        ItemStack[] armor = player.getInventory().getArmorContents();

        player.getInventory().setArmorContents(new ItemStack[]{});
        player.sendMessage(messageIndicator + ChatColor.RED + "rainbow has been deactivated");
        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE,1,1);

        return armor;
    }

    private ItemStack[] equipLeatherArmor(Player player) {
        if (hasNoArmor(player) || rainbowActivated(player)) {
            player.getInventory().setArmorContents(createLeatherArmor());
            
            return player.getInventory().getArmorContents();
        } else {
            player.sendMessage(messageIndicator + ChatColor.GRAY + "You need to remove your current armor");
        }
        
        return null;
    }

    private boolean hasNoArmor(Player player) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null) {
                return false;
            }
        }

        return true;
    }

    private ItemStack[] createLeatherArmor() {
        ItemStack[] armor = new ItemStack[4];

        armor[0] = new ItemStack(Material.LEATHER_BOOTS);
        armor[1] = new ItemStack(Material.LEATHER_LEGGINGS);
        armor[2] = new ItemStack(Material.LEATHER_CHESTPLATE);
        armor[3] = new ItemStack(Material.LEATHER_HELMET);
        setUnbreakable(armor);

        return armor;
    }

    private void setUnbreakable(ItemStack[] armor) {
        for (ItemStack item : armor) {
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setUnbreakable(true);
            item.setItemMeta(itemMeta);
        }
    }

    private void sendTextComponent(Player player, String command, String message, ClickEvent.Action action) {
        sendTextComponent(player, command, command, message, action);
    }

    private void sendTextComponent(Player player, String command, String componentText, String message, ClickEvent.Action action) {
        TextComponent text = new TextComponent("§6" + componentText);
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(message)));
        text.setClickEvent(new ClickEvent(action, command));

        player.spigot().sendMessage(text);
    }

}

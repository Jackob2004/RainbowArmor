package com.jackob.rainbowArmor.task;

import com.jackob.rainbowArmor.animation.AnimationBehavior;
import org.bukkit.scheduler.BukkitRunnable;

public class AnimationTask extends BukkitRunnable {

    private AnimationBehavior animationBehavior;

    public AnimationTask(AnimationBehavior behavior) {
        this.animationBehavior = behavior;
    }

    @Override
    public void run() {
        animationBehavior.animate();
    }

}
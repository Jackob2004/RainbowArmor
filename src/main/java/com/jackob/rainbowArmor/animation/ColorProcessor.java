package com.jackob.rainbowArmor.animation;

public class ColorProcessor {

    private int number;

    private int direction;

    private int transitionSpeed;

    public ColorProcessor(int transitionSpeed) {
        this.number = 0;
        this.direction = 1;
        this.transitionSpeed = transitionSpeed;
    }

    public int getNumber() {
        return number;
    }

    public boolean changeBrightness() {
        boolean reachedLimit = false;
        number += transitionSpeed * direction;

        if (number >= 255 || number <= 0) {
            direction *= -1;
            reachedLimit = true;
        }

        return reachedLimit;
    }

}

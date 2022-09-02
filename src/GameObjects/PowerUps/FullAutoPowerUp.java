package GameObjects.PowerUps;

import GameObjects.Tanks.Tank;
import Main.ResourcePool;
import Main.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FullAutoPowerUp extends Powerup {
    private float fireRate = 1.5f;
    private boolean toBeRemoved = false;

    protected static BufferedImage img = ResourcePool.getImages("fullAutoPowerUp");

    public FullAutoPowerUp(float y, float x) {
        super(y, x);
        this.hitbox = new Rectangle((int) x, (int) y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void applyPowerUp(Tank t) {
        t.setRateOfFire(this.fireRate);
        (new Sound(ResourcePool.getSounds("powerup"))).playSound();
    }

    @Override
    public void setToBeRemoved() {
        this.toBeRemoved = true;
    }
    @Override
    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(img, (int) x, (int) y, null);
    }

    @Override
    public boolean getToBeRemoved() {
        return toBeRemoved;
    }
}

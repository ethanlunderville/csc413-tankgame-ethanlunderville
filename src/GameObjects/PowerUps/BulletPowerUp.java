package GameObjects.PowerUps;

import GameObjects.PowerUps.Powerup;
import GameObjects.Tanks.Tank;
import Main.ResourcePool;
import Main.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BulletPowerUp extends Powerup {

    protected boolean toBeRemoved = false;
    protected static BufferedImage img = ResourcePool.getImages("rocketPowerUp");
    public BulletPowerUp(float y, float x) {
        super(y, x);
        this.hitbox = new Rectangle((int) x, (int) y, this.img.getWidth(), this.img.getHeight());
    }

    public void applyPowerUp(Tank t) {
        t.setBulletPowerUp(true);
        (new Sound(ResourcePool.getSounds("powerup"))).playSound();
    }

    @Override
    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(img, (int) x, (int) y, null);
    }

    @Override
    public void setToBeRemoved() {
        this.toBeRemoved = true;
    }

    @Override
    public boolean getToBeRemoved() {
        return toBeRemoved;
    }
}

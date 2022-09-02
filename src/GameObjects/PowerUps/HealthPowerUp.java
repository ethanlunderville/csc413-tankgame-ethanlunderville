package GameObjects.PowerUps;
import GameObjects.Tanks.Tank;
import Main.ResourcePool;
import Main.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthPowerUp extends Powerup {

    private boolean toBeRemoved = false;

    protected static BufferedImage img = ResourcePool.getImages("healthPowerUp");

    public HealthPowerUp(float y, float x) {
        super(y, x);
        this.hitbox = new Rectangle((int) x, (int) y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void applyPowerUp(Tank t) {
        (new Sound(ResourcePool.getSounds("powerup"))).playSound();
        if ((t.getHealth() + 40) <= 100) {
            t.setHealth(t.getHealth() + 40);
            return;
        }
        t.setHealth(100);
    }
    @Override
    public void setToBeRemoved() {
        this.toBeRemoved = true;
    }

    @Override
    public boolean getToBeRemoved() {
        return this.toBeRemoved;
    }

    @Override
    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(img, (int) x, (int) y, null);
    }

}

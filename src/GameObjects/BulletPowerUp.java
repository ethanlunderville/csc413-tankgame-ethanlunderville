package GameObjects;

import GameObjects.Tanks.Tank;
import java.awt.image.BufferedImage;

public class BulletPowerUp extends Powerup {

    private boolean toBeRemoved = false;

    public BulletPowerUp(float y, float x, BufferedImage img) {
        super(y, x, img);
    }

    public void applyPowerUp(Tank t) {
        t.setBulletType("Rocket");
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

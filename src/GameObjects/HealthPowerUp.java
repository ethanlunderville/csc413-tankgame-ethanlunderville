package GameObjects;
import GameObjects.Tanks.Tank;

import java.awt.image.BufferedImage;

public class HealthPowerUp extends Powerup {

    public HealthPowerUp(float y, float x, BufferedImage img) {
        super(y, x, img);
    }

    @Override
    public void applyPowerUp(Tank t) {

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
}

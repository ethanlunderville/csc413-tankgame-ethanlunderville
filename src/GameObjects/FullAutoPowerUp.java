package GameObjects;

import GameObjects.Tanks.Tank;

import java.awt.image.BufferedImage;

public class FullAutoPowerUp extends Powerup {

    private boolean toBeRemoved = false;

    public FullAutoPowerUp(float y, float x, BufferedImage img) {
        super(y, x, img);
    }

    @Override
    public void applyPowerUp(Tank t) {
        t.setRateOfFire(1.5f);
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

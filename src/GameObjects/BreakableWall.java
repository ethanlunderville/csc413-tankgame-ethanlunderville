package GameObjects;

import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {

    private int health = 50;

    public BreakableWall(float y, float x, BufferedImage img) {
        super(y, x, img);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean getToBeRemoved() {
        return super.getToBeRemoved();
    }

    @Override
    public void setToBeRemoved() {
        super.setToBeRemoved();
    }

    public int getHealth() {
        return health;
    }
}

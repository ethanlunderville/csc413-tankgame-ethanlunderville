package GameObjects.Walls;

import GameObjects.Walls.Wall;
import Main.ResourcePool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {

    protected static BufferedImage img = ResourcePool.getImages("break2");
    protected int health = 50;

    public BreakableWall(float y, float x) {
        super(y, x);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
    @Override
    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(this.img, (int) x, (int) y, null);
        buffer.setColor(Color.CYAN);
    }

}

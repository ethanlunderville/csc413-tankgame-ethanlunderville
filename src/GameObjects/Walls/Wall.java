package GameObjects.Walls;

import GameObjects.Collidable;
import GameObjects.GameObject;
import Main.ResourcePool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObject implements Collidable {

    protected float y, x;
    protected static BufferedImage img = ResourcePool.getImages("unbreakable");
    protected Rectangle hitBox;
    protected boolean toBeRemoved = false;

    public Wall(float y, float x) {
        this.y = y;
        this.x = x;
        this.img = img;
        this.hitBox = new Rectangle((int) x, (int) y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public String toString() {
        return "Wall{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }

    @Override
    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(this.img, (int) x, (int) y, null);
        buffer.setColor(Color.CYAN);
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
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

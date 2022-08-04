package GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObject implements Collidable {

    private float y, x;
    private BufferedImage img;
    private Rectangle hitBox;
    private boolean toBeRemoved = false;

    public Wall(float y, float x, BufferedImage img) {
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
        buffer.drawImage(img, (int) x, (int) y, null);
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

package GameObjects;

import Main.ResourcePool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private float y, x;

    protected static BufferedImage img = ResourcePool.getImages("floor");

    public Background(float y, float x) {
        this.y = y;
        this.x = x;
    }

    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(img, (int) x, (int) y, null);
    }
}

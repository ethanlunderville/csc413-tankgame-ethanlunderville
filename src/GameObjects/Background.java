package GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private float y, x;
    private BufferedImage img;

    public Background(float y, float x, BufferedImage img) {
        this.y = y;
        this.x = x;
        this.img = img;
    }

    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(img, (int) x, (int) y, null);
    }
}

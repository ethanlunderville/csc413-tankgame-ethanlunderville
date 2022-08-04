package GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject implements Collidable {

    protected float y, x;
    protected BufferedImage img;

    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(img, (int) x, (int) y, null);
    }

}

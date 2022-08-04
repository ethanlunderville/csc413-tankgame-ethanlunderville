package GameObjects;

import GameObjects.Tanks.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Powerup extends GameObject implements Collidable {

    protected Rectangle hitbox;
    protected float y, x;
    protected BufferedImage img;
    protected boolean toBeRemoved = false;

    public Powerup(float y, float x, BufferedImage img) {
        this.y = y;
        this.x = x;
        this.img = img;
        this.hitbox = new Rectangle((int) x, (int) y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void drawImage(Graphics2D buffer) {
        buffer.drawImage(img, (int) x, (int) y, null);
    }

    public abstract void applyPowerUp(Tank t);

    public Rectangle getHitBox() {
        return hitbox;
    }

}

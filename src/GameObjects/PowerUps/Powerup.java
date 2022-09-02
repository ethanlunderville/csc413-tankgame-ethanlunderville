package GameObjects.PowerUps;

import GameObjects.Collidable;
import GameObjects.GameObject;
import GameObjects.Tanks.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Powerup extends GameObject implements Collidable {

    protected Rectangle hitbox;
    protected float y, x;
    protected static BufferedImage img;
    protected boolean toBeRemoved = false;

    public Powerup(float y, float x) {
        this.y = y;
        this.x = x;
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

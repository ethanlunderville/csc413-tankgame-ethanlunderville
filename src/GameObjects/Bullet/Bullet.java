package GameObjects.Bullet;

import GameObjects.Walls.BreakableWall;
import GameObjects.Collidable;
import GameObjects.Moveable;
import GameObjects.Tanks.Tank;
import Main.ResourcePool;
import Main.Sound;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

public class Bullet implements Moveable, Collidable {
    protected final Main.Game game;
    protected float x;
    protected float y;
    protected float angle;
    protected Rectangle hitbox;
    protected float R = 5f;
    protected BufferedImage img = ResourcePool.getImages("bullet");
    protected static int damage = 20;
    protected Tank shooter;
    protected static List<BufferedImage> explosionAnimation = ResourcePool.getAnimations("hit");
    protected boolean toBeRemoved = false;

    public Bullet(Main.Game game, float x, float y, float angle, Tank shooter) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.hitbox = new Rectangle((int) x, (int) y, this.img.getWidth(), this.img.getHeight());
        this.shooter = shooter;
        (new Sound(ResourcePool.getSounds("shoot"))).playSound();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update() {
        this.moveForwards();
    }

    private void moveForwards() {
        x += Math.round(R * Math.cos(Math.toRadians(angle)));
        y += Math.round(R * Math.sin(Math.toRadians(angle)));
        this.hitbox.setLocation((int) x, (int) y);
    }
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }

    @Override
    public void setToBeRemoved() {
        this.toBeRemoved = true;
    }

    @Override
    public boolean getToBeRemoved() {
        return this.toBeRemoved;
    }

    public Rectangle getHitBox() {
        return this.hitbox.getBounds();
    }

    public List<BufferedImage> getAnimation() {
        return this.explosionAnimation;
    }

    public Tank getShooter() {
        return this.shooter;
    }

    @Override
    public void handleCollision(Collidable c) {

        if (!c.equals(this.getShooter()) && !c.equals(this)) {

            game.removeFromBullets(this);
            this.setToBeRemoved();

            game.addToAnimations((int) getX(), (int) getY(), getAnimation());

            if (c instanceof Tank) {
                ((Tank) c).setHealth(((Tank) c).getHealth() - this.damage);
            }
            if (c instanceof BreakableWall) {
                ((BreakableWall) c).setHealth(((BreakableWall) c).getHealth() - this.damage);
                if (1 > ((BreakableWall) c).getHealth()) {
                    c.setToBeRemoved();
                    this.game.removeFromWalls((BreakableWall) c);
                }

            }
        }

    }
}
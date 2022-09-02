package GameObjects.Bullet;

import GameObjects.Collidable;
import GameObjects.Tanks.Tank;
import GameObjects.Walls.BreakableWall;
import Main.Game;
import Main.ResourcePool;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

public class Rocket extends Bullet {

    protected static BufferedImage img = ResourcePool.getImages("rocket");
    protected static int damage = 35;
    public Rocket(Main.Game game, float x, float y, float angle, Tank shooter) {
        super(game, x, y, angle, shooter);
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
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }

}

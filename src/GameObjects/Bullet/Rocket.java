package GameObjects.Bullet;

import GameObjects.Tanks.Tank;

import java.awt.image.BufferedImage;

public class Rocket extends Bullet {

    public Rocket(Main.Game game, float x, float y, float angle, BufferedImage img, Tank shooter, int damage) {
        super(game, x, y, angle, img, shooter, damage);

    }
}

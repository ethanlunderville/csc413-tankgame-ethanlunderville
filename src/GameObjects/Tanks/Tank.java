package GameObjects.Tanks;

import GameObjects.*;
import GameObjects.Bullet.Bullet;
import GameObjects.Bullet.Rocket;
import GameObjects.PowerUps.Powerup;
import GameObjects.Walls.Wall;
import Main.Game;
import Main.GameConstants;
import Main.ResourcePool;
import Main.Sound;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;



public class Tank implements Moveable, Collidable {

    private final Main.Game Game;

    private final List<BufferedImage> explosionAnimation = ResourcePool.getAnimations("explosion");
    private final BufferedImage img;

    private int screenX;
    private int screenY;

    private final int startingX;
    private final int startingY;
    private float previousX;
    private float previousY;

    private float x;
    private float y;
    private float vx;
    private float vy;
    private float angle;
    private int lives;

    private final float R = 5;
    private final float ROTATIONSPEED = 2f;

    private float fireDelay = 20f;
    private float coolDown = 0f;
    private float rateOfFire = .8f;

    private int health;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    private final Rectangle hitbox;

    private Bullet b;
    private boolean bulletPowerUp;

    public Tank(float x, float y, float vx, float vy, float angle, BufferedImage img, Game g) {
        this.x = x;
        this.y = y;
        this.startingX = (int) x;
        this.startingY = (int) y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.hitbox = new Rectangle((int) x, (int) y, this.img.getWidth(), this.img.getHeight());
        this.health = 100;
        this.lives = 3;
        this.Game = g;
        this.bulletPowerUp = false;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.hitbox.setLocation((int) x, (int) y);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    private void centerScreen() {
        this.screenX = (int) this.getX() - GameConstants.GAME_SCREEN_WIDTH / 4;
        this.screenY = (int) this.getY() - GameConstants.GAME_SCREEN_HEIGHT / 2;

        if (this.screenX < 0) {
            this.screenX = 0;
        }
        if (this.screenY < 0) {
            this.screenY = 0;
        }
        if (screenX > GameConstants.WORLD_WIDTH - GameConstants.GAME_SCREEN_WIDTH / 2) {
            screenX = GameConstants.WORLD_WIDTH - GameConstants.GAME_SCREEN_WIDTH / 2;
        }
        if (screenY > (GameConstants.WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT)) {
            screenY = GameConstants.WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT;
        }
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

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void ToggleShootPressed() {
        this.ShootPressed = true;
    }

    public void unToggleShootPressed() {
        this.ShootPressed = false;
    }

    public void update(Game game) {

        if (this.UpPressed) {
            this.moveForwards();
        }

        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }

        if (this.RightPressed) {
            this.rotateRight();
        }

        if (this.ShootPressed && this.coolDown >= this.fireDelay) {
            this.coolDown = 0;

            if (!bulletPowerUp) {
                b = new Bullet(game, x, y, angle, this);
                game.addToMoveables(b);
                game.addToBullets(b);
            } else {
                b = new Rocket(game, x, y, angle, this);
                game.addToMoveables(b);
                game.addToBullets(b);
            }
        }
        coolDown += this.rateOfFire;
        this.centerScreen();

    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {

        previousX = x;
        previousY = y;
        vx = Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx / 1.5;
        y -= vy / 1.5;
        checkBorder();
        this.hitbox.setLocation((int) x, (int) y);

    }

    private void moveForwards() {

        previousX = x;
        previousY = y;
        vx = Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx / 1.5;
        y += vy / 1.5;
        checkBorder();
        this.hitbox.setLocation((int) x, (int) y);

    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.WORLD_WIDTH - 88) {
            x = GameConstants.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.WORLD_HEIGHT - 125) {
            y = GameConstants.WORLD_HEIGHT - 125;
        }
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
        g2d.setColor(Color.MAGENTA);

        int health1 = (int) (((float) health / 100) * this.img.getWidth());

        g2d.setColor(Color.black);
        g2d.drawRect((int) x, (int) y + 65, this.img.getWidth(), this.img.getHeight() - 40);
        g2d.setColor(Color.green);
        g2d.fillRect((int) x, (int) y + 65, health1, this.img.getHeight() - 40);

        for (int i = 0; i < this.lives; i++) {
            g2d.setColor(Color.black);
            g2d.drawOval((int) x + (20 * (i))  , (int) (y - 40), 15, 15);
            g2d.fillOval((int) x + (20 * (i))  , (int) (y - 40), 17, 17);
            g2d.setColor(Color.green);
            g2d.fillOval((int) x + (20 * (i)), (int) (y - 40), 15, 15);
        }

    }

    public Rectangle getHitBox() {
        return this.hitbox.getBounds();
    }

    @Override
    public void setToBeRemoved() {
    }

    @Override
    public boolean getToBeRemoved() {
        return false;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int i) {
        this.health = i;
        if (health < 1) {
            this.resetTank();
        }
    }

    public void resetTank() {
        Game.addToAnimations((int) this.getX(), (int) this.getY(), this.explosionAnimation);
        this.bulletPowerUp = false;
        this.rateOfFire = .4f;
        this.lives--;
        this.health = 100;
        this.setPosition(startingX, startingY);
    }

    public boolean lost() {
        return this.lives < 1;
    }

    public void setRateOfFire(float f) {
        this.rateOfFire = f;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isBulletPoweredUp() {
        return bulletPowerUp;
    }

    public void setBulletPowerUp(boolean bulletPowerUp) {
        this.bulletPowerUp = bulletPowerUp;
    }

    @Override
    public void handleCollision(Collidable c) {
        if (c instanceof Wall) {

            int distanceFromWall = 5;

            if( ((Math.abs(this.hitbox.getMaxY() - ((Wall)c).getHitBox().getMinY())) <= distanceFromWall)){
               this.setPosition(this.x, this.previousY-1); //-2
            } else
            if(((Math.abs(this.hitbox.getMinY() - ((Wall)c).getHitBox().getMaxY())) <= distanceFromWall)){
                this.setPosition(this.x, this.previousY+1);
            }

            if((Math.abs(this.hitbox.getMaxX() - ((Wall)c).getHitBox().getMinX())) <= distanceFromWall){
                this.setPosition(this.previousX-1, this.y);
            } else
            if((Math.abs(this.hitbox.getMinX() - ((Wall)c).getHitBox().getMaxX())) <= distanceFromWall){
                this.setPosition(this.previousX+1, this.y);
            }

        }
        if (c instanceof Powerup) {
            ((Powerup) c).applyPowerUp(this);
            Game.removeFromPowerUps((Powerup) c);
            c.setToBeRemoved();
        }
    }
}

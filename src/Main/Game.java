package Main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import GameObjects.*;
import GameObjects.Bullet.Bullet;
import GameObjects.PowerUps.BulletPowerUp;
import GameObjects.PowerUps.FullAutoPowerUp;
import GameObjects.PowerUps.HealthPowerUp;
import GameObjects.PowerUps.Powerup;
import GameObjects.Tanks.Tank;
import GameObjects.Tanks.TankControl;
import GameObjects.Walls.BreakableWall;
import GameObjects.Walls.Wall;

public class Game extends JPanel implements Runnable {

    private BufferedImage world;
    private Tank t1;
    private Tank t2;
    private Launcher lf;
    private long tick = 0;
    private List<Wall> walls = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private List<Background> Background = new ArrayList<>();
    private List<Powerup> PowerUps = new ArrayList<>();
    private List<Animation> Animations = new ArrayList<>();
    private BufferedImage lh;
    private BufferedImage rh;

    private List<Collidable> collidables = new ArrayList<>();
    private List<Moveable> moveables = new ArrayList<>();

    public Game(Launcher lf) {
        this.lf = lf;
    }
    /**
     * Main game loop
     */
    @Override
    public void run() {
        try {

            this.resetGame();

            while (true) {

                this.tick++;

                // All object values are updated
                this.t1.update(this);
                this.t2.update(this);
                Animations.forEach(e -> e.update(this));
                bullets.forEach(e -> e.update());

                /***
                 * All moveables have a handle collision method that is called on all collidables.
                 *
                 * Note: Moveables make all changes to the collidbles by requesting the collidables
                 * to make the changes by calling a method on the reference to that specific collidable
                 * from the moveable handleCollsion method in the moveable class.
                 *
                 */
                for (int i = 0; i < moveables.size(); i++) {
                    for (int j = 0; j < collidables.size(); j++) {
                        if (moveables.get(i).getHitBox().intersects(collidables.get(j).getHitBox())) {
                            moveables.get(i).handleCollision(collidables.get(j));
                        }
                    }
                }

                /**
                 * These two loops dynamically resize the moveable and collidable lists based
                 * on whether the given value in the lists should be removed which is determined
                 * in the loop above.
                 */

                int z = this.moveables.size();
                for (int i = 0; i < z; i++) {
                    if (moveables.get(i).getToBeRemoved()) {
                        moveables.remove(moveables.get(i));
                        i--;
                        z--;
                    }
                }
                z = this.collidables.size();
                for (int i = 0; i < z; i++) {
                    if (collidables.get(i).getToBeRemoved()) {
                        collidables.remove(collidables.get(i));
                        i--;
                        z--;
                    }
                }

                if (t1.lost() || t2.lost()) {
                    this.InitializeGame();
                    lf.setFrame("end");
                    break;
                }

                this.repaint();

                Thread.sleep(1000 / 144);

            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame() {
        this.tick = 0;

        this.t1.setLives(3);
        this.t2.setLives(3);

        Animations.clear();
        bullets.clear();
    }

    /**
     * This method intitializes all game objects and can be used to reset them when the game is finished as well
     * <p>
     * Note: All new collidable objects must be added to the collideables and all the moveable objects must
     * be added to the moveables list
     */
    public void InitializeGame() {
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH,
                GameConstants.WORLD_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        BufferedImage t1img = null;
        BufferedImage t2img = null;
        try {

            t1img = ResourcePool.getImages("tank1");
            t2img = ResourcePool.getImages("tank2");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        //Tanks are initialized and controls are assigned
        t1 = new Tank(50, 700, 0, 0, (short) 0, t1img, this);
        addToMoveables(t1);
        addToCollidables(t1);

        TankControl tc1 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        this.lf.getJf().addKeyListener(tc1);

        t2 = new Tank(1600, 725, 0, 0, (short) 0, t2img, this);
        addToMoveables(t2);
        addToCollidables(t2);

        TankControl tc2 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_P);
        this.lf.getJf().addKeyListener(tc2);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Background.add(new Background(i * 240, j * 315));
            }
        }
        /**
         * Map is read in from the maps folder
         * */
        try (BufferedReader mapReader = new BufferedReader(new InputStreamReader(Game.class.getClassLoader().getResourceAsStream("maps/Map.txt")))) {

            for (int i = 0; mapReader.ready(); i++) {
                String[] items = mapReader.readLine().split("");
                for (int j = 0; j < items.length; j++) {

                    switch (items[j]) {
                        case "9" -> {

                            Wall w = new Wall(i * 30, j * 30);
                            addToCollidables(w);
                            walls.add(w);
                        }
                        case "3" -> {

                            Wall w = new Wall(i * 30, j * 30);
                            walls.add(w);
                            addToCollidables(w);
                        }
                        case "2", "4" -> {
                            BreakableWall bw = new BreakableWall(i * 30, j * 30);
                            walls.add(bw);
                            addToCollidables(bw);
                        }
                        case "5" -> {
                            Powerup pu = new HealthPowerUp(i * 30, j * 30);
                            PowerUps.add(pu);
                            addToCollidables(pu);
                        }
                        case "6" -> {
                            Powerup pu = new FullAutoPowerUp(i * 30, j * 30);
                            PowerUps.add(pu);
                            addToCollidables(pu);
                        }
                        case "7" -> {
                            Powerup pu = new BulletPowerUp(i * 30, j * 30);
                            PowerUps.add(pu);
                            addToCollidables(pu);
                        }
                    }

                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * This is were the screen is drawn
     * <p>
     * Also, every list containing an object that is still in the game has its entire contents drawn to the screen
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        Background.forEach(w -> w.drawImage(buffer));
        walls.forEach(w -> w.drawImage(buffer));
        PowerUps.forEach(w -> w.drawImage(buffer));

        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);
        this.bullets.forEach(b -> b.drawImage(buffer));

        for (int i = 0; i < Animations.size(); i++) {
            Animations.get(i).drawImage(buffer);
            if (Animations.get(i).isFinished()) {
                Animations.remove(i);
            }
        }

        try {

            this.lh = world.getSubimage(t1.getScreenX(), t1.getScreenY(), GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);
            g2.drawImage(lh, 0, 0, null);

            this.rh = world.getSubimage(t2.getScreenX(), t2.getScreenY(), GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);
            g2.drawImage(rh, 640, 0, null);

        } catch (Exception w) {
        }

        BufferedImage nm = world.getSubimage(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);
        g2.scale(.2, .18);
        g2.drawImage(nm, 2255, 0, null);

    }

    @Override
    public void repaint(Rectangle r) {
        super.repaint(r);
    }

    public void addToBullets(Bullet b) {
        this.bullets.add(b);
    }

    public long getTick() {
        return this.tick;
    }

    public void addToAnimations(int x, int y, List<BufferedImage> a) {
        Animations.add(new Animation(x, y, tick, a));
    }

    public void addToMoveables(Moveable m) {
        this.moveables.add(m);
    }

    public void removeFromWalls(Wall w) {
        this.walls.remove(w);

    }
    public void addToCollidables(Collidable c) {
        this.collidables.add(c);
    }

    public void removeFromBullets(Bullet bullet) {
        this.bullets.remove(bullet);
    }

    public void removeFromPowerUps(Powerup powerup) {
        this.PowerUps.remove(powerup);
    }

}

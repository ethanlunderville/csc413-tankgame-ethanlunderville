package Main;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for storing all the resources into hashmaps so that they are quickly accessible
 * */

public class ResourcePool {

    private static final HashMap<String, BufferedImage> Images = new HashMap<>();
    private static final HashMap<String, Clip> Sounds = new HashMap<>();
    private static final HashMap<String, List<BufferedImage>> Animations = new HashMap<>();

    public static void initImages() throws IOException {
        try {

            Images.put("floor", ImageIO.read(ResourcePool.class.getClassLoader().getResource("floor/bg.bmp")));

            Images.put("tank1", ImageIO.read(ResourcePool.class.getClassLoader().getResource("tank/tank1.png")));
            Images.put("tank2", ImageIO.read(ResourcePool.class.getClassLoader().getResource("tank/tank2.png")));

            Images.put("bullet", ImageIO.read(ResourcePool.class.getClassLoader().getResource("bullet/Bullet.jpg")));
            Images.put("rocket", ImageIO.read(ResourcePool.class.getClassLoader().getResource("bullet/Weapon.png")));

            Images.put("break1", ImageIO.read(ResourcePool.class.getClassLoader().getResource("walls/break1.jpg")));
            Images.put("break2", ImageIO.read(ResourcePool.class.getClassLoader().getResource("walls/break2.jpg")));
            Images.put("unbreakable", ImageIO.read(ResourcePool.class.getClassLoader().getResource("walls/unbreak.jpg")));

            Images.put("healthPowerUp", ImageIO.read(ResourcePool.class.getClassLoader().getResource("Powerups/Health.png")));
            Images.put("fullAutoPowerUp", ImageIO.read(ResourcePool.class.getClassLoader().getResource("Powerups/Fullauto.png")));
            Images.put("rocketPowerUp", ImageIO.read(ResourcePool.class.getClassLoader().getResource("Powerups/Pickup.png")));


        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static void initSounds() {
        try {

            AudioInputStream as;
            Clip clip;

            as = AudioSystem.getAudioInputStream(ResourcePool.class.getClassLoader().getResource("sounds/LOZ_Secret.wav"));
            clip = AudioSystem.getClip();
            clip.open(as);
            ResourcePool.Sounds.put("powerup", clip);

            as = AudioSystem.getAudioInputStream(ResourcePool.class.getClassLoader().getResource("sounds/bullet.wav"));
            clip = AudioSystem.getClip();
            clip.open(as);
            ResourcePool.Sounds.put("shoot", clip);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void initAnimations() {
        List<BufferedImage> temp = new ArrayList<>();

        try {

            List<BufferedImage> j = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                j.add(ImageIO.read(ResourcePool.class.getClassLoader().getResource("hit/hit_000" + i + ".png")));
            }
            Animations.put("hit", j);
            j.clear();
            for (int i = 0; i < 10; i++) {
                j.add(ImageIO.read(ResourcePool.class.getClassLoader().getResource("explosion/expl_01_000" + i + ".png")));

            }
            for (int i = 10; i < 24; i++) {
                j.add(ImageIO.read(ResourcePool.class.getClassLoader().getResource("explosion/expl_01_00" + i + ".png")));
            }
            Animations.put("explosion", j);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Reads in all the resources
     * */
    public static void initResources() {
        try {
            initImages();
            initAnimations();
            initSounds();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Resources did not load correctly");
        }
    }

    public static BufferedImage getImages(String name) {
        return Images.get(name);
    }

    public static Clip getSounds(String name) {
        return Sounds.get(name);
    }

    public static List<BufferedImage> getAnimations(String name) {
        return Animations.get(name);
    }

}


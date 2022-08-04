package Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Class used to draw animations easily from the main game class
 * */
public class Animation {

    private int x;
    private int y;
    private List<BufferedImage> anim;
    private Long startFrame;
    private Long currentFrame;
    private Boolean isFinished;

    public Animation(int x, int y, long startFrame, List<BufferedImage> anim) {
        this.x = x;
        this.y = y;
        this.anim = anim;
        this.startFrame = startFrame;
        this.isFinished = false;
    }

    public void update(Game g) {
        this.currentFrame = g.getTick();
    }

    public Boolean isFinished() {

        return this.isFinished;

    }
    // This function is in charge of managing drawing images to the screen and getting rid of them
    // when they no longer need to be drawn
    public void drawImage(Graphics g) {

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;

        if (framesFromTick() < this.anim.size() - 1) {

            g2d.drawImage(anim.get(framesFromTick()), rotation, null);

        } else {
            this.isFinished = true;
        }

    }

    private int framesFromTick() {
        if( this.currentFrame != null) {
            return (int) (currentFrame - startFrame);
        }
        return 0;
    }

}

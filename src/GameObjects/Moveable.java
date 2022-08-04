package GameObjects;
import java.awt.*;

public interface Moveable {

    public void handleCollision(Collidable c);

    public Rectangle getHitBox();

    public void setToBeRemoved();

    public boolean getToBeRemoved();

}

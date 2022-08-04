package GameObjects;
import java.awt.*;

public interface Collidable {

    public void setToBeRemoved();

    public boolean getToBeRemoved();

    public Rectangle getHitBox();
}

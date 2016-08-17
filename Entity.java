import javafx.scene.image.Image;

public class Entity {
    private boolean collidable;
    private Image image;

    public Entity(Image image) {
        this.image = image;
    }

    public void update() {

    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public boolean isCollidable() {
        return collidable;
    }
}

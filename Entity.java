import javafx.scene.image.Image;

public abstract class Entity {
    private boolean collidable;
    private Image image;

    public Entity(Image image) {
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public abstract void update();
}

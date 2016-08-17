import javafx.scene.image.Image;

public abstract class Entity {
    private boolean collidable;
    private Image image;

    protected GameState game;

    private int x;
    private int y;

    public Entity(GameState game) {
        this.game = game;
        this.image = null;
    }

    public Entity(GameState game, Image image) {
        this.image = image;
        this.game = game;
    }

    public Entity(GameState game, String imagePath) {
        this.image = new Image(imagePath);
        this.game = game;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

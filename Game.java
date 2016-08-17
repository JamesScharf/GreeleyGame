import javafx.application.Application;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.stage.*;


public class Game extends Application {
	private static int tileWidth = 64;
	private static int tileHeight = 64;

	private GameState game;
	private int width = 10;
	private int height = 10;

	private StackPane rootPane;
	private GraphicsContext gc;
	private Canvas canvas;

	private static Image defaultTile = new Image("assets/images/Tile/medievalTile_57.png");

	@Override
	public void start(Stage stage) {
		stage.setTitle("GreeleyGame");

		canvas = new Canvas(tileWidth * width, tileHeight * height);
	   	gc = canvas.getGraphicsContext2D();

		rootPane = new StackPane();
		rootPane.getChildren().add(canvas);

		this.loadGame();
		this.render(0, 0);

		Scene scene = new Scene(rootPane, width * 64, height * 64);
		stage.setScene(scene);
		stage.show();
	}

	public void loadGame() {
		game = new GameState(50, 50);
		game.setTile(0, 0, new RoadEntity(this.game));
		game.setTile(0, 1, new RoadEntity(this.game));
		game.setTile(0, 2, new RoadEntity(this.game));
		game.setTile(1, 1, new RoadEntity(this.game));

		new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.update();
				render(0, 0);
            }
        }.start();
	}

	// startX/startY should be the starting tile that will be rendered
	// Renders one part of the map at once
	public void render(int startX, int startY) {
		// gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(int x = startX; x < startX + this.width; x++ ) {
			for ( int y = startY; y < startY + this.height; y++) {
				Image tileImage = null;

				Entity tile = this.game.getTile(x, y);

				if ( tile == null ) {
					tileImage = defaultTile;
				}
				else {
					tileImage = tile.getImage();
				}

				gc.drawImage(tileImage, y * tileHeight, x * tileWidth);

				Entity object = this.game.getObject(x, y);

				if ( object != null ) {
					gc.drawImage(object.getImage(), y * tileHeight, x * tileWidth);
				}
			}
		}
	}

	public static void main(String[] argv) {
		launch(argv);
	}
}

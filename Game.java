import com.sun.javafx.perf.PerformanceTracker;

import javafx.application.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
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
	private Scene scene;
	private Stage stage;

	private float fps;
	private PerformanceTracker tracker;

	private static Image defaultTile = new Image("assets/images/Tile/medievalTile_57.png");

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("GreeleyGame");

		canvas = new Canvas(tileWidth * width, tileHeight * height);
	   	gc = canvas.getGraphicsContext2D();

		rootPane = new StackPane();
		rootPane.getChildren().add(canvas);

		this.scene = new Scene(rootPane, width * 64, height * 64);

		this.loadGame();

		new AnimationTimer() {
            @Override
            public void handle(long now) {
                tick();
            }
        }.start();

		this.scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        	@Override
        	public void handle(KeyEvent keyEvent) {
				String code = keyEvent.getCode().toString();
				keyboardHandled(code);
			}
		});

		this.tracker = PerformanceTracker.getSceneTracker(scene);

		this.render(0, 0);

		stage.setScene(this.scene);
		stage.show();
	}

	// Initialzie the GameState
	public void loadGame() {
		game = new GameState(50, 50);

		game.setTile(0, 0, new RoadEntity(this.game));
		game.setTile(0, 1, new RoadEntity(this.game));
		game.setTile(0, 2, new RoadEntity(this.game));
		game.setTile(1, 1, new RoadEntity(this.game));

		Character prim = new Character(this.game);
		game.setObject(5, 1, prim);
		game.setPrimaryCharacter(prim);
	}

	// Called every frame
	public void tick() {
		game.update();
		render(0, 0);

		this.fps = tracker.getAverageFPS();
		this.stage.setTitle("GreeleyGame - " + String.format("%.1f", fps) + "FPS");
        tracker.resetAverageFPS();
	}

	// Called whenever a keyboard button is pressed.
	// Code is the keyboard character pressed.
	public void keyboardHandled(String code) {
		if ( game.getPrimaryCharacter() != null && game.getPrimaryCharacter() instanceof Character ) {
			Character character = (Character) game.getPrimaryCharacter();

			if ( code.equals("UP") ) {
				character.moveUp();
			}
			else if ( code.equals("DOWN") ) {
				character.moveDown();
			}
			else if ( code.equals("LEFT") ) {
				character.moveLeft();
			}
			else if ( code.equals("RIGHT") ) {
				character.moveRight();
			}
			else if ( code.equals("ESCAPE") ) {
				Platform.exit();
				System.exit(0);
			}
			else {
				System.out.println("Got character " + code);
			}
		}
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

				gc.drawImage(tileImage, y * tileHeight - startY, x * tileWidth - startX);

				Entity object = this.game.getObject(x, y);

				if ( object != null ) {
					gc.drawImage(object.getImage(), y * tileHeight - startY, x * tileWidth - startX);
				}
			}
		}
	}

	public static void main(String[] argv) {
		launch(argv);
	}
}

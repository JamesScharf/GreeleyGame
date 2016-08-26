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
import java.util.*;


public abstract class Game extends Application {
	protected static int tileWidth = 64;
	protected static int tileHeight = 64;

	protected GameState game;
	protected int width = 10;
	protected int height = 10;

	protected StackPane rootPane;
	protected GraphicsContext gc;
	protected Canvas canvas;
	protected Scene scene;
	protected Stage stage;

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("GreeleyGame");

		canvas = new Canvas(tileWidth * width, tileHeight * height);
	   	gc = canvas.getGraphicsContext2D();

		rootPane = new StackPane();
		rootPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
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

		this.render(0, 0);

		stage.setScene(this.scene);
		stage.show();
	}

	// Initialzie the GameState
	public abstract void loadGame();

	// Called every frame
	public abstract void tick();

	// Draws information helpful for debugging
	public abstract void drawDebugInfo();

	// Called whenever a keyboard button is pressed.
	// Code is the keyboard character pressed.
	public abstract void keyboardHandled(String code);

	// startX/startY should be the starting tile that will be rendered
	// Renders one part of the map at once
	public abstract void render(int startX, int startY);

	public static void main(String[] argv) {
		launch(argv);
	}
}

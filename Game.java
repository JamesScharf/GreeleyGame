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

	private HashMap<String, String> uiElements;

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

		this.uiElements = new HashMap<String, String>();

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
				renderUIElements();
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

	// Used for registering UI elements
	public void registerUIElement(String name) {
		this.uiElements.put(name, null);
	}

	public void updateUIElement(String name, String value) {
		this.uiElements.put(name, value);
	}

	public void renderUIElements() {
		int i = 1;

		for ( String key : this.uiElements.keySet() ) {
			this.gc.fillText(key + ": " + this.uiElements.get(key), 0, (14 * (i)));
			i++;
		}
	}

	// Initialzie the GameState
	public abstract void loadGame();

	// Called every frame
	public abstract void tick();

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

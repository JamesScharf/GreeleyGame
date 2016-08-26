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


public class GreeleyGame extends Game {
	private float fps;
	private PerformanceTracker tracker;

	private static Image defaultTile = new Image("assets/images/Tile/medievalTile_57.png");

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

		this.tracker = PerformanceTracker.getSceneTracker(scene);
	}

	// Called every frame
	public void tick() {
		game.update();

		Character prim = game.getPrimaryCharacter();
		// This looks like it does nothing, but integer divisions automatically round down.
		render((prim.getX() / this.width) * this.width, (prim.getY() / this.height) * this.height);

		this.fps = tracker.getAverageFPS();
		tracker.resetAverageFPS();
		this.drawDebugInfo();
	}

	// Draws information helpful for debugging
	public void drawDebugInfo() {
		// gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

		Character prim = game.getPrimaryCharacter();

		gc.fillText("Player Pos: (" + prim.getY() + ", " + prim.getX() +")", 0, (14 * 1));
		gc.fillText("FPS: " + String.format("%.1f", fps) + "FPS",            0, (14 * 2));
		gc.fillText("Camera: (" + ((prim.getY() / this.height) * this.height) + ", "
								+ ((prim.getX() / this.width) * this.width) + ")", 0, (14 * 3));
	}

	// Called whenever a keyboard button is pressed.
	// Code is the keyboard character pressed.
	public void keyboardHandled(String code) {
		if ( game.getPrimaryCharacter() != null && game.getPrimaryCharacter() instanceof Character ) {
			Character character = game.getPrimaryCharacter();

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

				gc.drawImage(tileImage, (y - startY) * tileHeight, (x - startX) * tileWidth);

				Entity object = this.game.getObject(x, y); //The character

				if ( object != null ) {
					gc.drawImage(object.getImage(), (y - startY) * tileHeight, (x - startX) * tileWidth);
				}
			}
		}
	}

	public static void main(String[] argv) {
		launch(argv);
	}
}

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
	private TilePane tilePane;

	@Override
	public void start(Stage stage) {
		stage.setTitle("GreeleyGame");

		tilePane = new TilePane();
		tilePane.setHgap(0);
		tilePane.setVgap(0);
		tilePane.setPrefRows(this.width);
		tilePane.setPrefColumns(this.height);

		rootPane = new StackPane();
		rootPane.getChildren().add(tilePane);

		this.loadGame();
		this.render(0, 0);

		Scene scene = new Scene(rootPane, width * 64, height * 64);
		stage.setScene(scene);
		stage.show();
	}

	public void loadGame() {
		game = new GameState(50, 50);
		game.set(0, 0, new RoadEntity(this.game));
		game.set(0, 1, new RoadEntity(this.game));
		game.set(1, 1, new RoadEntity(this.game));

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
		tilePane.getChildren().clear();

		for(int x = startX; x < startX + this.width; x++ ) {
			for ( int y = startY; y < startY + this.height; y++) {
				Image entityImage = null;
				Entity entity = this.game.get(x, y);

				if ( entity == null ) {
					entityImage = new Image("assets/images/Tile/medievalTile_57.png");
				}
				else {
					entityImage = entity.getImage();
				}

				tilePane.getChildren().add(new ImageView(entityImage));
			}
		}
	}

	public static void main(String[] argv) {
		launch(argv);
	}
}

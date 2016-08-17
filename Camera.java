import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Camera extends Application {
	private Pane root = new Pane();

	public Camera(String[] args, String[][] totalMap, int physicalHeight, int physicalWidth) {
		start(Stage primaryStage, physicalHeight, physicalWidth)
		primaryStage.setScene(new Scene(root, physicalHeight, physicalWidth)); //Needs to go last
		primaryStage.show();
		launch(args);
	}
	@Override
	public void start(Stage primaryStage, int physicalHeight, int physicalWidth) {
		primaryStage.setTitle("GreeleyGame");
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});
	}

	//startX/startY should be the starting tile that will be rendered
	public renderOnePart(String[][] totalMap, int physicalHeight, int physicalWidth, int startX, int startY) { //Renders one part of the map at once
		int tilesPerHeight = physicalHeight/64; //64 is the x y size of the image tile
		int tilesPerWidth = physicalWidth/64;
		TilePane tile = new TilePane();
	    tile.setHgap(0);
	    tile.setPrefColumns(tilesPerHeight);
		//int totalTiles = tilesPerHeight*tilesPerWidth; //The max number of tiles that can be rendered on the screen at once
		for(int x=startX; x<tilesPerWidth; x++) {
			for(int y=startY; y<tilesPerHeight; y++) {
				Image entityImage = totalMap[x][y].getImage();
				ImageView imagePlacement = new ImageView();
				imagePlacement.setImage(entityImage);
				imagePlacement.relocate(x*64, y*64);
				root.getChildren().add(imagePlacement;
			}
		}
	}

}

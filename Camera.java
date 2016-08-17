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


public class Camera extends Application {
	private Group root;

	public Camera(String[] args, String[][] totalMap, int gridHeight, int gridWidth) {

		primaryStage.setScene(new Scene(root, physicalHeight, physicalWidth)); //Needs to go last
		primaryStage.show();
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("GreeleyGame");
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		Group root = new Group();
		root.getChildren().add(btn);
	}
	public renderOnePart(String[][] totalMap, int physicalHeight, int physicalWidth) { //Renders one part of the map at once
		int tilesPerHeight = physicalHeight/64; //64 is the x y size of the image tile
		int tilesPerWidth = physicalWidth/64;
		//int totalTiles = tilesPerHeight*tilesPerWidth; //The max number of tiles that can be rendered on the screen at once
		for(int x=0; x<tilesPerWidth; x++) {
			for(int y=0; y<tilesPerHeight; y++) {
				Entity location = totalMap[x*64][y*64];
				root.getChildren().add(new Image(location.getImage());
			}
		}
	}

}

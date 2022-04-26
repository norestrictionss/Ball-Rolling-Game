

import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.FileNotFoundException;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class Main extends Application {
	
	private Level currentLevel = new Level(1);
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		Stage mainStage;
		Button previousLevel;
		Button nextLevel;
		Scene currentScene;
		Test gameTest = new Test();
		Test.setLevel(currentLevel);
		gameTest.start(new Stage());
		mainStage = Test.getGameStage();
		currentScene = mainStage.getScene();
		HBox buttonBox = ((HBox)((BorderPane)currentScene.getRoot()).getCenter());
		previousLevel = (Button)buttonBox.getChildren().get(0);
		nextLevel = (Button)buttonBox.getChildren().get(1);
		mainStage.setResizable(false);
		mainStage.show();
		previousLevel.setOnAction(e->{
			if(currentLevel.getId() > 1) {
				try {
				currentLevel.decrementId();	
				gameTest.start(Test.getGameStage());
				}
				catch(Exception epic) {
					
				}
			}
		});
		nextLevel.setOnAction(e->{
			try {

			if(currentLevel.getId() < currentLevel.getTotalLevels() && currentLevel.getId() <= currentLevel.getLevelsFinished()) {
				currentLevel.incrementId();
				gameTest.start(Test.getGameStage());			
			}
			}
			catch(Exception epic) {
				
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

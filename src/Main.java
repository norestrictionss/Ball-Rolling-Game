

import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.FileNotFoundException;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
		Scene mainScene;
		Stage mainStage = new Stage();
		Button startGame = new Button("Start Game");
		Button returnToMainMenu = new Button ("Return to Main Menu");
		
	private static Level currentLevel = new Level(1);
	@Override
	public void start(Stage primaryStage){
		startGame.setLayoutX(150);
		startGame.setLayoutY(200);
		
		startGame.setFont(new Font(50));
		setToMainMenu();
		returnToMainMenu.setTranslateX(300);
		Test.setReturnToMainMenu(returnToMainMenu);
		returnToMainMenu.setOnAction(returnToMainMenu -> {
			setToMainMenu();
		});
		startGame.setOnAction(startingGame -> {
		Button previousLevel;
		Button nextLevel;
		Test gameTest = new Test();
		Test.setLevel(currentLevel);
		try {
			gameTest.start(mainStage);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		mainStage = Test.getGameStage();
		mainScene = mainStage.getScene();
		HBox buttonBox = ((HBox)((BorderPane)mainScene.getRoot()).getCenter());
		previousLevel = (Button)buttonBox.getChildren().get(0);
		nextLevel = (Button)buttonBox.getChildren().get(1);
		mainStage.setScene(mainScene);
		previousLevel.setOnAction(e->{
			if(currentLevel.getId() > 1) {
				try {
				currentLevel.decrementId();	
				gameTest.start(Test.getGameStage());
				gameTest.stopAnimation();
				}
				catch(Exception epic) {
					
				}
			}
		});
		nextLevel.setOnAction(e->{
			try {
				
			if(currentLevel.getId() < currentLevel.getTotalLevels() && currentLevel.getId() <= currentLevel.getLevelsFinished()) {
				gameTest.stopAnimation();
				currentLevel.incrementId();
				gameTest.start(Test.getGameStage());			
			}
			}
			catch(Exception epic) {
				
			}
		});

	});
		
		mainStage.setResizable(false);
		mainStage.show();
		
}	
	public void setToMainMenu() {
		Pane mainMenuPane = new Pane();
		ImageView background = new ImageView(new Image("file:Background.png"));
		mainMenuPane.setPrefSize(600, 600);
		mainMenuPane.getChildren().addAll(background,startGame);
		mainScene = new Scene(mainMenuPane);
		mainStage.setScene(mainScene);
		
	}
	public static void getToNextLevel() {
		try {
			if(currentLevel.getId() < currentLevel.getTotalLevels() && currentLevel.getId() <= currentLevel.getLevelsFinished()) {
			Test newGame = new Test();
			currentLevel.incrementId();
			newGame.stopAnimation();
			newGame.start(Test.getGameStage());	
		}
		}
		catch (Exception exception) {
			
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

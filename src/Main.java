

import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.FileNotFoundException;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
		Button credits = new Button("Credits");
		boolean isContributionClosed = true;
	private static Level currentLevel = new Level(1);
	@Override
	public void start(Stage primaryStage){
		startGame.setLayoutX(144);
		startGame.setLayoutY(126);
		startGame.setStyle("-fx-background-color:#AF5525;");
		credits.setLayoutX(160);
		credits.setLayoutY(180);
		credits.setStyle("-fx-background-color:#AF5525;");
		startGame.setFont(new Font(20));
		credits.setFont(new Font(20));
		setToMainMenu();
		returnToMainMenu.setTranslateX(300);
		Test.setReturnToMainMenu(returnToMainMenu);
		credits.setOnAction(openCredits -> {
			if(isContributionClosed) {
			isContributionClosed = false;
			Stage creditsStage = new Stage();
			Pane creditsPane = new Pane();
			Text contributorNames = new Text("\n\tName\nFurkan GÖKGÖZ\nBarýþ Something");
			Text contributorNumbers = new Text ("\nSchool Number\n  150120076\n  Somenumber");
			contributorNumbers.setTranslateX(200);
			creditsPane.setStyle("-fx-background-color:#AF5525;");
			creditsPane.setPrefSize(300,100);
			creditsPane.getChildren().addAll(contributorNames,contributorNumbers);
			creditsStage.setScene(new Scene(creditsPane));
			creditsStage.setTitle("Contributors");
			creditsStage.setOnCloseRequest(closingCredits ->{
				isContributionClosed = true;
			});
			creditsStage.show();
			}
		});
		returnToMainMenu.setOnAction(returnToMainMenu -> {
			setToMainMenu();
		});
		startGame.setOnAction(startingGame -> {
			if(isContributionClosed) {
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
			}
	});
		mainStage.setTitle("Tile Grid Game");
		mainStage.setResizable(false);
		mainStage.show();
		
}	
	public void setToMainMenu() {
		Pane mainMenuPane = new Pane();
		Text tileGridGame = new Text(100,66,"Tile Grid Game");
		tileGridGame.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 33));
		tileGridGame.setFill(Color.SANDYBROWN);
		tileGridGame.setStroke(Color.BLACK);
		ImageView background = new ImageView(new Image("file:Background.png",400,400,true,true));
		mainMenuPane.setPrefSize(400,400);
		mainMenuPane.getChildren().addAll(background,startGame,tileGridGame,credits);
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

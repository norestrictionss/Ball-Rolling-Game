
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.io.File;
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


/* PROJECT NAME: TILE GRID GAME 
   CONTRIBUTORS
   STUDENT NAME: BARI� G�RAY AKMAN -> STUDENT ID: 150121822
   STUDENT NAME: FURKAN G�KG�Z-> STUDENT ID: 150120076 
   PURPOSE OF THAT PROGRAM IS SOLVING THE PUZZLE AND HAVING THE BALL MOVED 
   IF THE PUZZLE HAS BEEN SET IN CORRECT COMBINATION.
   */

public class Main extends Application {
		Scene mainScene; //  This variable represents the scene which will be used in program.
		Stage mainStage = new Stage(); /* This variable represents the stage which 
		will be used in program. */
		Button startGame = new Button("Start Game"); /* This button provides us to 
		start the game. */
		Button returnToMainMenu = new Button ("Return to Main Menu"); /* This button 
		provides us to return to main menu.*/
		Button credits = new Button("Credits"); /* This button provides us to view 
		contributors. */
		boolean isContributionClosed = true;
	private static Level currentLevel = new Level(1);
	@Override
	public void start(Stage primaryStage){
		// Button adjustments 
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
		Game.setReturnToMainMenu(returnToMainMenu);
		// With that button, player can check contributors. 
		credits.setOnAction(openCredits -> {
			if(isContributionClosed) {
			isContributionClosed = false;
			Stage creditsStage = new Stage();
			Pane creditsPane = new Pane();
			Text contributorNames = new Text("\n\tName\nBar�� Giray AKMAN \n Furkan G�KG�Z");
			Text contributorNumbers = new Text ("\nSchool Number\n  150121822\n  150120076");
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
		// With that button, player can go back to main menu.
		returnToMainMenu.setOnAction(returnToMainMenu -> {
			setToMainMenu();
		});
		// With that button, player can start the game.
		startGame.setOnAction(startingGame -> {
			if(isContributionClosed) {
		Button previousLevel;
		Button nextLevel;
		// Before starting the game, currentLevel is adjusted.
		Game game = new Game();
		Game.setLevel(currentLevel);
		try {
			game.start(mainStage);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// mainStage and mainScene are achieved from game class.
		mainStage = Game.getGameStage();
		mainScene = mainStage.getScene();
		// Also, buttons are fetched from mainScene.
		HBox buttonBox = ((HBox)((BorderPane)mainScene.getRoot()).getCenter());
		previousLevel = (Button)buttonBox.getChildren().get(0);
		nextLevel = (Button)buttonBox.getChildren().get(1);
		mainStage.setScene(mainScene);
		// With that button, player can easily pass to the previous level.
		previousLevel.setOnAction(e->{
			if(currentLevel.getId() > 1) {
				try {
				currentLevel.decrementId();	
				game.start(Game.getGameStage());
				Game.stopAnimation();
				}
				catch(Exception epic) {
					
				}
			}
		});
		/* If player has completed the current level successfully, with that button, 
		 player can pass to the next level. */
		nextLevel.setOnAction(e->{
			try {
				
			if(new File("./levels/CSE1242_spring2022_project_level" + (currentLevel.getId()+1 )+ ".txt").exists() && currentLevel.getId() <= currentLevel.getLevelsFinished()) {
				Game.stopAnimation();
				currentLevel.incrementId();
				game.start(Game.getGameStage());			
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
	// This method provides to go back to menu page.
	// In the method, mainStage's scene is adjusted as menu scene.
	public void setToMainMenu() {
		Pane mainMenuPane = new Pane();
		Text tileGridGame = new Text(100,66,"Tile Grid Game");
		tileGridGame.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 33));
		tileGridGame.setFill(Color.SANDYBROWN);
		tileGridGame.setStroke(Color.BLACK);
		mainMenuPane.setPrefSize(400,400);
		mainMenuPane.getChildren().addAll(FileReader.getBackground(),startGame,tileGridGame,credits);
		mainScene = new Scene(mainMenuPane);
		mainStage.setScene(mainScene);
		
	}
	/* This method provides to get to the next level automatically if player has 
	 completed level succesfully. */
	public static void getToNextLevel() {
		try {
			if(new File("./levels/CSE1242_spring2022_project_level" + (currentLevel.getId()+1 )+ ".txt").exists() && currentLevel.getId() <= currentLevel.getLevelsFinished()) {
				currentLevel.incrementId();
				Game newGame = new Game();
			
			Game.stopAnimation();
			newGame.start(Game.getGameStage());
			
		}
		}
		catch (Exception exception) {
			
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
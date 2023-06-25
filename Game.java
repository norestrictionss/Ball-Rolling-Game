import java.io.FileNotFoundException;
import java.util.ArrayList;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.application.Application;
public class Game extends Application {
	/*Global variables for events and methods*/
		private static Level currentLevel;
		private static Stage gameStage;
		private static Button previousLevel = new Button("Previous Level");
		private static Button nextLevel = new Button("Next Level");
		private static Button returnToMainMenu;
		private static PathTransition circlePathTransition;
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		try {
			
		Game.gameStage = primaryStage; /*Changes the global variable to the given stage*/
		final double BALL_SPEED = 4; /*In terms of tile/second */
		Tile[] currentTileObject = new Tile[1]; /*For event to access*/
		int[] currentTileGrid = new int[4]; /*For event to access,stores information about the tile,row index,column index,X,Y coordinates,*/
		boolean[] isFinished = {false};	/*For event to access,stops the user from further interaction after the level is finished*/
		
		/*Button coordinates,uses a specific value for correct placement*/
		previousLevel.setTranslateX(170);
		nextLevel.setTranslateX(230);
		
		
		/*Gridpane for storing the tile*/
		GridPane gridPane=new GridPane();
		FileReader fileReader=new FileReader("./levels/CSE1242_spring2022_project_level" + currentLevel.getId() + ".txt"); /*For reading the files based on current level*/
		Circle gameBall = FileReader.getBall(); /*Gets ball from filereader*/
		
		ArrayList<Tile> allTiles= fileReader.start_reading(); /*Gets an array list full of tiles from filereader*/
		setBall(gameBall,CheckPath.findStarterIndex(allTiles)); /*Sets the ball location*/
		
		
		BorderPane borderPane=new BorderPane();/*Border pane for storing gridpane buttons and counter*/
		
		setGridPane(gridPane, allTiles); /*Adds tiles to the gridpane accordingly*/
		
		gridPane.getChildren().add(gameBall); /*Adds the ball to the gridpane*/
		/*Text for showing the currentlevel*/
		Text currentLevelText = new Text("Current Level : " + currentLevel.getId());
		currentLevelText.setFont(new Font(50));
		currentLevelText.setTranslateX(150);
		currentLevelText.setFill(Color.WHITE);
		currentLevelText.setStroke(Color.WHITE);
		
		/*Counter for total moves*/
		Text totalMoveCounter = new Text("Total Moves : " + currentLevel.getCounterFor(currentLevel.getId()));
		totalMoveCounter.setTranslateX(260); /*Setting the coordinates for the counter*/
		totalMoveCounter.setStroke(Color.WHITE); /*Changes the color of the text*/
		
		
		VBox vbox = new VBox(); /*Vbox for information text*/
		vbox.getChildren().addAll(totalMoveCounter,currentLevelText); /*Adds text*/
		vbox.setSpacing(0); /*Spacing for the vbox*/
		
		HBox hbox=new HBox(); /*HBox for buttons*/
		hbox.getChildren().addAll(previousLevel,nextLevel,returnToMainMenu); /*Adds the necessary buttons*/
		hbox.setSpacing(0); /*Spacing for the HBox*/
		
		/*Adding parts to the borderpane*/
		borderPane.setTop(gridPane); 
		borderPane.setCenter(hbox);
		borderPane.setBottom(vbox);/*Adds move counter to the bottom of the pane*/
		borderPane.getChildren().add(gameBall); /*Adds the ball to the borderpane*/
		borderPane.setStyle("-fx-background-color: #000000;"); /*Background for the whole borderpane (black)*/
		
		/*Setting the scene to borderpane*/
		Scene scene=new Scene(borderPane);
		
		/*On click animation for gridPane
		 * Stores information for tiles with the purpose of moving the tiles,*/
		gridPane.setOnMousePressed(e->{
			/*Checks if the game is finished*/
			if(isFinished[0] == false) {
				
				int columnIndex=(int) (e.getX()/150);
				int rowIndex=(int) (e.getY()/150);
				currentTileObject[0] = allTiles.get(columnIndex + rowIndex * 4); /*Finds the array equilavent of the mouse location*/
				/*Stores necessary information about the tile*/
				currentTileGrid[0] = rowIndex;
				currentTileGrid[1] = columnIndex;
				currentTileGrid[2] = (int)e.getX();
				currentTileGrid[3] = (int)e.getY();
			}
		});
		/*Animation for dragging the tile around,moves the tile accordingly*/
		gridPane.setOnMouseDragged(e->{
			/*Checks if the game is finished*/
			if(isFinished[0] == false) {
				Tile currentTile = currentTileObject[0];
				/*Checks if the tile is Movable from the interface*/
				if(currentTile.getIsMovable()) {
					currentTile.getImage().setTranslateX(e.getX() - currentTileGrid[2]);
					currentTile.getImage().setTranslateY(e.getY() - currentTileGrid[3]);
			}
			}
		});
		/*For replacing the tile when stopping the dragging*/
		gridPane.setOnMouseReleased(e->{
			try {
			/*Checks if the game is finished*/
			if(isFinished[0] == false) {
				/*For finding the tile that the mouse is on top of and for easier access of the tile that is being dragged*/
				Tile currentTile = currentTileObject[0];
				int otherRow = (int)(e.getY() / 150);
				int otherColumn = (int)(e.getX() / 150);
				Tile otherTile = allTiles.get(otherColumn + otherRow * 4);
				
				/*Checks if the placed tile is Free and the currentTile is movable*/
				if(otherTile.getProperty().equals("Free") && currentTile.getIsMovable()) {
					/*For calculation purposes,finds the row and column of the tiles and the differences*/
					int currentRow = currentTileGrid[0];
					int currentColumn = currentTileGrid[1];
					int rowDifference = currentRow - otherRow;
					int columnDifference = currentColumn - otherColumn;
					
				/*Checks if the tiles are adjacent to each other*/
				if(((currentColumn == otherColumn) && (rowDifference == -1 || rowDifference == 1)) ||
						((currentRow == otherRow) && (columnDifference == -1 || columnDifference == 1))) {
					
					
					
					
					/*Swaps the tiles with each other in gridpane*/
					GridPane.setColumnIndex(otherTile.getImage(),currentColumn);
					GridPane.setRowIndex(otherTile.getImage(),currentRow);
					GridPane.setColumnIndex(currentTile.getImage(),otherColumn);		
					GridPane.setRowIndex(currentTile.getImage(),otherRow);
					
					/*Swaps the tiles in the all tiles array*/
					allTiles.set(otherColumn  + otherRow * 4, currentTile);
					allTiles.set(currentColumn + currentRow * 4, otherTile);		
					
					/*Increments the total moves count and updates the counter*/
					totalMoveCounter.setText("Total Moves : " + currentLevel.incrementCounterFor(currentLevel.getId()));
					
					/*Checks the pipe path to see if it is correct(Returns null if its not)*/
					ArrayList<Tile> currentPathTileArray = CheckPath.generatePath(allTiles);
					/*If the path is not null,basically if the path is correct or leading to the end*/
						if(currentPathTileArray != null) { 
							isFinished[0] = true; /*Updating the variable isFinished*/
							if(currentLevel.getLevelsFinished() + 1 == currentLevel.getId())
							currentLevel.incrementLevelsFinished(); /*Increments the totalfinished levels for access for the next level*/
							circlePathTransition = new PathTransition(); /*Makes a new animation*/
							circlePathTransition.setPath(CircleAnimation.returnCirclePath(currentPathTileArray,allTiles)); /*Finds the path from the path array*/
							circlePathTransition.setNode(gameBall); /*Sets the ball for movement*/
							 /*Sets the duration of the ball by using a calculation of speed constant*/
							
							circlePathTransition.setDuration(Duration.seconds(((Path)circlePathTransition.getPath()).getElements().size() / BALL_SPEED));
							circlePathTransition.play(); /*Starts the circle animation*/
							/*Gets to next level when the animation is finished*/
							circlePathTransition.setOnFinished(event->{ 
								Main.getToNextLevel();
							});
				}
						
				}
				}
			}
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
			finally {
				
			/*Resets the dragging animation for the dragged tile*/
			currentTileObject[0].getImage().setTranslateX(0);
			currentTileObject[0].getImage().setTranslateY(0);
			
			
			}
		});
		/*Sets the scene for the game stage*/
		gameStage.setScene(scene);
		}
		catch(Exception ex) {
		ex.printStackTrace();		
		}
	}
	/*Changes the current level for the game*/
	public static void setLevel(Level currentLevel) {
		Game.currentLevel = currentLevel;
	}
	/*Stops the current animation*/
	public static void stopAnimation() {
		circlePathTransition.pause();
	}
	/*Returns the game stage*/
	public static Stage getGameStage() {
		return gameStage;
	}
	/*Sets the ball in the correct position*/
	private void setBall(Circle gameBall,int startingTileIndex) {

		
		gameBall.setCenterX((startingTileIndex % 4)* 150 + 72);
		gameBall.setCenterY((startingTileIndex / 4)* 150 + 78);
	}
	/*Sets the returnToMainMenu button*/
	public static void setReturnToMainMenu(Button returnToMainMenu) {
		Game.returnToMainMenu = returnToMainMenu;
	}
	/*Adds tiles from a given array list to the gridpane*/
	private void setGridPane(GridPane gridPane, ArrayList<Tile> tiles) throws FileNotFoundException {
		for(int i=0;i<tiles.size();i++) {	
			gridPane.add(tiles.get(i).getImage(), i %4, i/4);
		}
	}		
}
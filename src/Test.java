

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.application.Application;
public class Test extends Application {
		private static Level currentLevel;
		private static Stage actualStage = null;
		private static Button previousLevel = new Button("Previous Level");
		private static Button nextLevel = new Button("Next Level");
		PathTransition circlePathTransition = new PathTransition();
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		actualStage = primaryStage;
		final double BALL_SPEED = 1; /*In terms of tile/second */
		Tile[] currentTileObject = new Tile[1];
		int[] currentTileGrid = new int[4];
		boolean[] isFinished = {false};
		Tile.setTotalMoves(0);
		Circle gameCircle = new Circle();
		gameCircle.setFill(Color.YELLOW);
		gameCircle.setStroke(Color.BLACK);
		gameCircle.setRadius(18);
		previousLevel.setTranslateX(170);
		nextLevel.setTranslateX(230);
		Text totalMoveCounter = new Text("Total Moves : " + Tile.getTotalMoves());
		totalMoveCounter.setTranslateX(260);
		GridPane gridPane=new GridPane();
		FileReader fileReader=new FileReader("CSE1242_spring2022_project_level" + currentLevel.getId() + ".txt");
		ArrayList<Tile> all_tiles=fileReader.start_reading();
		int startingTileIndex = CheckPath.findStarterIndex(all_tiles);
		gameCircle.setCenterX((startingTileIndex % 4)* 150 + 72);
		gameCircle.setCenterY((startingTileIndex / 4)* 150 + 75);
		BorderPane borderPane=new BorderPane();
		borderPane.setBottom(totalMoveCounter);
		setGridPane(gridPane, all_tiles);
		gridPane.getChildren().add(gameCircle);
		HBox hbox=new HBox(2);
		hbox.getChildren().addAll(previousLevel,nextLevel);
		hbox.setSpacing(0);
		borderPane.setStyle("-fx-background-color: #000000;");
		totalMoveCounter.setStroke(Color.WHITE);
		borderPane.setTop(gridPane);
		borderPane.setCenter(hbox);
		Scene scene=new Scene(borderPane);
		borderPane.getChildren().add(gameCircle);
		
		gridPane.setOnMousePressed(e->{
			if(isFinished[0] == false) {
				int columnIndex=(int) (e.getX()/150);
				int rowIndex=(int) (e.getY()/150);
				currentTileObject[0] = all_tiles.get(columnIndex + rowIndex * 4);
				currentTileGrid[0] = rowIndex;
				currentTileGrid[1] = columnIndex;
				currentTileGrid[2] = (int)e.getX();
				currentTileGrid[3] = (int)e.getY();
			}
		});
		gridPane.setOnMouseDragged(e->{
			if(isFinished[0] == false) {
				Tile currentTile = currentTileObject[0];
				if(currentTile instanceof Movable) {
					currentTile.getImage().setTranslateX(e.getX() - currentTileGrid[2]);
					currentTile.getImage().setTranslateY(e.getY() - currentTileGrid[3]);
			}
			}
		});
		gridPane.setOnMouseReleased(e->{
			try {
			if(isFinished[0] == false) {
				
				int currentRow = currentTileGrid[0];
				int currentColumn = currentTileGrid[1];
				int otherRow = (int)(e.getY() / 150);
				int otherColumn = (int)(e.getX() / 150);
				int rowDifference = currentRow - otherRow;
				int columnDifference = currentColumn - otherColumn;
				Tile otherTile = all_tiles.get(otherColumn + otherRow * 4);
				Tile currentTile = currentTileObject[0];
			
				if(otherTile instanceof Free && currentTile instanceof Movable && (((currentColumn == otherColumn) && (rowDifference == -1 || rowDifference == 1)) ||
						((currentRow == otherRow) && (columnDifference == -1 || columnDifference == 1)))) {
					currentTile.getImage().setTranslateX(0);
					currentTile.getImage().setTranslateY(0);
					GridPane.setColumnIndex(otherTile.getImage(),currentColumn);
					GridPane.setRowIndex(otherTile.getImage(),currentRow);
					GridPane.setColumnIndex(currentTile.getImage(),otherColumn);		
					GridPane.setRowIndex(currentTile.getImage(),otherRow);
					all_tiles.set(otherColumn  + otherRow * 4, currentTile);
					all_tiles.set(currentColumn + currentRow * 4, otherTile);
					Tile.incrementTotalMoves();
					totalMoveCounter.setText("Total Moves : " + Tile.getTotalMoves());
					ArrayList<Tile> currentPathTileArray = CheckPath.generatePath(all_tiles);
						if(currentPathTileArray != null) {
							isFinished[0] = true;
							if(currentLevel.getLevelsFinished() + 1 == currentLevel.getId())
							currentLevel.incrementLevelsFinished();
							
							circlePathTransition = new PathTransition();
							circlePathTransition.setPath(CircleAnimation.returnCirclePath(currentPathTileArray,all_tiles));
							circlePathTransition.setNode(gameCircle);
							circlePathTransition.setCycleCount(1);
							circlePathTransition.setDuration(Duration.seconds(((Path)circlePathTransition.getPath()).getElements().size() / BALL_SPEED));
							circlePathTransition.autoReverseProperty();
							circlePathTransition.play();
							circlePathTransition.setOnFinished(event->{
								Main.getToNextLevel();
							});
				}
						
			}
			}
			}
			catch (Exception exception) {
			}
			finally {
			currentTileObject[0].getImage().setTranslateX(0);
			currentTileObject[0].getImage().setTranslateY(0);
			
			
			}
		});
		actualStage.setResizable(false);
		actualStage.setScene(scene);

	}
	public static void setLevel(Level currentLevel) {
		Test.currentLevel = currentLevel;
	}
	public void stopAnimation() {
		circlePathTransition.pause();
	}
	public static Stage getGameStage() {
		return actualStage;
	}
	public void setGridPane(GridPane gridPane, ArrayList<Tile> tiles) throws FileNotFoundException {
		for(int i=0;i<tiles.size();i++) {
			tiles.get(i).setColumnIndex(i/4);
			tiles.get(i).setRowIndex((i%4));
			
			gridPane.add(tiles.get(i).getImage(), i %4, i/4);
		}
	}		
}

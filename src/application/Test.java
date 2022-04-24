package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
public class Test extends Application {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Tile[] currentTileObject = new Tile[1];
		int[] currentTileGrid = new int[4];
 		Pane mainPane=new Pane();
		Button level1=new Button("Level 1");
		Button level2=new Button("Level 2");
		Button level3=new Button("Level 3");
		Button level4=new Button("Level 4");
		Button level5=new Button("Level 5");
		Button level6=new Button("Level 6");
		GridPane gridPane=new GridPane();
		FileReader fileReader=new FileReader("CSE1242_spring2022_project_level5.txt");
		ArrayList<Tile> all_tiles=fileReader.start_reading();
		BorderPane borderPane=new BorderPane();
		BorderPane buttonPane=new BorderPane();
		buttonPane.setCenter(level1);
		buttonPane.setCenter(level2);
		buttonPane.getChildren().add(level3);
		buttonPane.getChildren().add(level4);
		buttonPane.getChildren().add(level5);
		buttonPane.getChildren().add(level6);
		setGridPane(gridPane, all_tiles);
		HBox hbox=new HBox(6);
		hbox.getChildren().addAll(level1, level2, level3, level4, level5, level6);
		buttonPane.setCenter(hbox);
		// TODO Auto-generated method stub
		hbox.setSpacing(55);
		borderPane.setCenter(gridPane);
		borderPane.setBottom(buttonPane);
		Scene scene=new Scene(borderPane);
		scene.setFill(javafx.scene.paint.Color.BLACK);
		
		gridPane.setOnMousePressed(e->{
			
			int columnIndex=(int) (e.getX()/150);
			int rowIndex=(int) (e.getY()/150);
			currentTileObject[0] = all_tiles.get(columnIndex + rowIndex * 4);
			currentTileGrid[0] = rowIndex;
			currentTileGrid[1] = columnIndex;
			currentTileGrid[2] = (int)e.getX();
			currentTileGrid[3] = (int)e.getY();	
		});
		borderPane.setOnMouseDragged(e->{
			Tile currentTile = currentTileObject[0];
			if(currentTile instanceof Movable) {
			currentTile.getImage().setTranslateX(e.getX() - currentTileGrid[2]);
			currentTile.getImage().setTranslateY(e.getY() - currentTileGrid[3]);
			}
		});
		gridPane.setOnMouseReleased(e->{
			try {
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
				System.out.println(currentColumn + " " + currentRow + " " + otherColumn + " " + otherRow);
				currentTile.getImage().setTranslateX(0);
				currentTile.getImage().setTranslateY(0);
				GridPane.setColumnIndex(otherTile.getImage(),currentColumn);
				GridPane.setRowIndex(otherTile.getImage(),currentRow);
				GridPane.setColumnIndex(currentTile.getImage(),otherColumn);		
				GridPane.setRowIndex(currentTile.getImage(),otherRow);
				
				all_tiles.set(otherColumn  + otherRow * 4, currentTile);
				all_tiles.set(currentColumn + currentRow * 4, otherTile);
			}
			}
			catch (Exception exception) {
			}
			finally {
			currentTileObject[0].getImage().setTranslateX(0);
			currentTileObject[0].getImage().setTranslateY(0);
			}
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void setGridPane(GridPane gridPane, ArrayList<Tile> tiles) throws FileNotFoundException {
		for(int i=0;i<tiles.size();i++) {
			tiles.get(i).setColumnIndex(i/4);
			tiles.get(i).setRowIndex((i%4));
			
			gridPane.add(tiles.get(i).getImage(), i %4, i/4);
		}
		
	}

	
	public Tile findTile(ArrayList<Tile> all_tiles, int columnIndex, int rowIndex) {
		for(int i=0;i<all_tiles.size();i++) {
			if(all_tiles.get(i).getRowIndex()==rowIndex && 
					all_tiles.get(i).getColumnIndex()==columnIndex)
				return all_tiles.get(i);
		}
		return null;
	}
	public boolean isRight(ArrayList<Tile> all_tiles, int index) {
		Tile tile=null;
		for(int j=0;j<all_tiles.size();j++) {
			if(GridPane.getRowIndex(all_tiles.get(j).getImage())*4+
				GridPane.getColumnIndex(all_tiles.get(j).getImage())==index+1) {
					tile=all_tiles.get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
	public boolean isLeft(ArrayList<Tile> all_tiles, int index) {
		Tile tile=null;
		
		for(int j=0;j<all_tiles.size();j++) {
			
			if(GridPane.getRowIndex(all_tiles.get(j).getImage())*4+
			   GridPane.getColumnIndex(all_tiles.get(j).getImage())==index-1) {
					tile=all_tiles.get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
	public boolean isUp(ArrayList<Tile> all_tiles, int index) {
		Tile tile=null;
		
		for(int j=0;j<all_tiles.size();j++) {
			if(GridPane.getColumnIndex(all_tiles.get(j).getImage())+
			   GridPane.getRowIndex(all_tiles.get(j).getImage())*4==index-4) {
					tile=all_tiles.get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
	public boolean isDown(ArrayList<Tile> all_tiles, int index) {
		Tile tile=null;
		for(int j=0;j<all_tiles.size();j++) {

			if(GridPane.getColumnIndex(all_tiles.get(j).getImage())+
			   GridPane.getRowIndex(all_tiles.get(j).getImage())*4==index+4) {
					tile=all_tiles.get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
}

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
		Pane mainPane=new Pane();
		Button level1=new Button("Level 1");
		Button level2=new Button("Level 2");
		Button level3=new Button("Level 3");
		Button level4=new Button("Level 4");
		Button level5=new Button("Level 5");
		Button level6=new Button("Level 6");
		GridPane gridPane=new GridPane();
		FileReader fileReader=new FileReader("CSE1242_spring2022_project_level4.txt");
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
			
			int rowIndex=(int) (e.getY()/150);
			int columnIndex=(int) (e.getX()/150);
			
			move_block(gridPane, rowIndex, columnIndex, all_tiles);
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void setGridPane(GridPane gridPane, ArrayList<Tile> tiles) throws FileNotFoundException {
		for(int i=0;i<tiles.size();i++) {
			tiles.get(i).setColumnIndex(i%4);
			tiles.get(i).setRowIndex((i/4));
			
			gridPane.add(tiles.get(i).getImage(), i %4, i/4);
		}
		
	}
	public void move_block(GridPane gridPane, int rowIndex, int columnIndex, ArrayList<Tile> all_tiles) {
		all_tiles.get(columnIndex+4*rowIndex).getImage().setOnMouseDragged(e->{
			Tile tile1=all_tiles.get(rowIndex*4+columnIndex);
			
			System.out.println(rowIndex+" "+columnIndex);
			if(isRight(all_tiles, rowIndex*4+columnIndex) &&
					e.getX()>150 && e.getX()<300 && e.getY()>0 && e.getY()<150
				&& !(all_tiles.get(rowIndex*4+columnIndex) instanceof PipeStatic
						|| all_tiles.get(rowIndex*4+columnIndex) instanceof End ||
						all_tiles.get(rowIndex*4+columnIndex) instanceof Starter)) {
				Tile tile2=all_tiles.get(4*rowIndex+columnIndex+1);
			
				GridPane.setColumnIndex(tile1.getImage(), columnIndex+1);
				GridPane.setRowIndex(tile1.getImage(), rowIndex);
				GridPane.setColumnIndex(tile2.getImage(), columnIndex);
				GridPane.setRowIndex(tile2.getImage(), rowIndex); 
				all_tiles.set(4*rowIndex+columnIndex+1, tile1);
				all_tiles.set(4*rowIndex+columnIndex, tile2);	
			}
			
			else if(isLeft(all_tiles, rowIndex*4+columnIndex) &&
					e.getX()>-150 && e.getX()<0 && e.getY()>0 && e.getY()<150
					&& !(all_tiles.get(rowIndex*4+columnIndex) instanceof PipeStatic ||
							all_tiles.get(rowIndex*4+columnIndex) instanceof End 
							|| all_tiles.get(rowIndex*4+columnIndex) instanceof Starter)) {
				Tile tile2=all_tiles.get(4*rowIndex+columnIndex-1);
				
				GridPane.setColumnIndex(tile1.getImage(), columnIndex-1);
				GridPane.setRowIndex(tile1.getImage(), rowIndex);
				GridPane.setColumnIndex(tile2.getImage(), columnIndex);
				GridPane.setRowIndex(tile2.getImage(), rowIndex);
				all_tiles.set(4*rowIndex+columnIndex-1, tile1);
				all_tiles.set(4*rowIndex+columnIndex, tile2);
		
			}
			else if(isDown(all_tiles, rowIndex*4+columnIndex) &&
					e.getX()<150 && e.getX()>0 && e.getY()>150 && e.getY()<300
					&& !(all_tiles.get(rowIndex*4+columnIndex) instanceof PipeStatic
						|| all_tiles.get(rowIndex*4+columnIndex) instanceof End
						|| all_tiles.get(rowIndex*4+columnIndex) instanceof Starter)) {
				
				Tile tile2=all_tiles.get(4*(rowIndex+1)+columnIndex);
				GridPane.setColumnIndex(tile1.getImage(), columnIndex);
				GridPane.setRowIndex(tile1.getImage(), rowIndex+1);
				GridPane.setColumnIndex(tile2.getImage(), columnIndex);
				GridPane.setRowIndex(tile2.getImage(), rowIndex);
				all_tiles.set(4*(rowIndex+1)+columnIndex, tile1);
				all_tiles.set(4*rowIndex+columnIndex, tile2);
			}
			else if(isUp(all_tiles, rowIndex*4+columnIndex) &&
					e.getX()>0 && e.getX()<150 && e.getY()<0 && e.getY()>-150
					&& !(all_tiles.get(rowIndex*4+columnIndex) instanceof PipeStatic
						 || all_tiles.get(rowIndex*4+columnIndex) instanceof End)) {
				Tile tile2=all_tiles.get(4*(rowIndex-1)+columnIndex);
				GridPane.setColumnIndex(tile1.getImage(), columnIndex);
				GridPane.setRowIndex(tile1.getImage(), rowIndex-1);
				GridPane.setColumnIndex(tile2.getImage(), columnIndex);
				GridPane.setRowIndex(tile2.getImage(), rowIndex);
				all_tiles.set(4*(rowIndex-1)+columnIndex, tile1);
				all_tiles.set(4*rowIndex+columnIndex, tile2);
			}
		});
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

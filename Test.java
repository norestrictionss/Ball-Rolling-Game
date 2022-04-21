import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Test extends Application {
	public void start(Stage primaryStage) throws IOException{
		File file=new File("CSE1242_spring2022_project_level1.txt");
		File file2=new File("CSE1242_spring2022_project_level2.txt");
		File file3=new File("CSE1242_spring2022_project_level3.txt");
		File file4=new File("CSE1242_spring2022_project_level4.txt");
		File file5=new File("CSE1242_spring2022_project_level5.txt");
		File file6=new File("CSE1242_spring2022_project_level6.txt");
		GamePane pane=new GamePane();
		FileReader fileReader=new FileReader(pane, file4);
		fileReader.startReading();
		
		int sayi;
		for(int i=0;i<pane.getChildren().size();i++) {
			System.out.println(((Tile) pane.getChildren().get(i)).getXLocation()+
					" "+((Tile) pane.getChildren().get(i)).getYLocation());
		}
		for(int i=0;i<16;i++) {
			
			final int num=i;
			pane.getChildren().get(i).setOnMouseDragged(e->{
				
				System.out.println(e.getX()+" "+e.getY());
				ImageView image=new ImageView(new Image("Pipe1.png"));
				Tile tile1=(Tile) pane.getChildren().get(num);
				System.out.println(num);
				
				Tile tile=new Pipe(new Image("01.png"));
				
						if(isRight(pane, tile1.getYLocation()*4+tile1.getXLocation()) && e.getX()>=70 && e.getX()<140 && e.getY()>=0 && e.getY()<=70) {
							
							Tile tile2=findTile(pane, tile1.getYLocation()*4+tile1.getXLocation()+1);
							int row=tile1.getYLocation();
							int column=tile1.getXLocation();
							GridPane.setColumnIndex(tile1, column+1);
							GridPane.setRowIndex(tile1, row);
							GridPane.setColumnIndex(tile2, column);
							GridPane.setRowIndex(tile2, row);
							tile1.setXLocation(column+1);
							tile1.setYLocation(row);
							tile2.setXLocation(column);
							tile2.setYLocation(row);
						}
						
						if(isLeft(pane, tile1.getYLocation()*4+tile1.getXLocation()) && e.getX()<=0 && e.getX()>=-70 && e.getY()>=0 && e.getY()<=70) {
							
						
							int row=tile1.getYLocation();
							int column=tile1.getXLocation();
							Tile tile2=findTile(pane, tile1.getYLocation()*4+tile1.getXLocation()-1);
							GridPane.setColumnIndex(tile1, column-1);
							GridPane.setRowIndex(tile1, row);
							GridPane.setColumnIndex(tile2, column);
							GridPane.setRowIndex(tile2, row);
							tile1.setXLocation(column-1);
							tile1.setYLocation(row);
							tile2.setXLocation(column);
							tile2.setYLocation(row);
						}
						if(isDown(pane, tile1.getYLocation()*4+tile1.getXLocation()) && e.getX()>=0 && e.getX()<=70 && e.getY()>70 && e.getY()<=140) {
							
							Tile tile2=findTile(pane, tile1.getYLocation()*4+tile1.getXLocation()+4);
							int row=tile1.getYLocation();
							int column=tile1.getXLocation();
							
							GridPane.setColumnIndex(tile1, column);
							GridPane.setRowIndex(tile1, row+1);
							GridPane.setColumnIndex(tile2, column);
							GridPane.setRowIndex(tile2, row);
							tile1.setXLocation(column);
							tile1.setYLocation(row+1);
							tile2.setXLocation(column);
							tile2.setYLocation(row);
						}
						if(isUp(pane, tile1.getYLocation()*4+tile1.getXLocation()) && e.getX()>=0 && e.getX()<=70 && e.getY()<=0 && e.getY()>=-70) {
							System.out.println("Gött");
							Tile tile2=findTile(pane, tile1.getYLocation()*4+tile1.getXLocation()-4);
							int row=tile1.getYLocation();
							int column=tile1.getXLocation();
							
							GridPane.setColumnIndex(tile1, column);
							GridPane.setRowIndex(tile1, row-1);
							GridPane.setColumnIndex(tile2, column);
							GridPane.setRowIndex(tile2, row);
							tile1.setXLocation(column);
							tile1.setYLocation(row-1);
							tile2.setXLocation(column);
							tile2.setYLocation(row);
						}
						
				});
		}
		
		
		
		
		Scene scene=new Scene(pane, javafx.scene.paint.Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public Tile findTile(GridPane pane, int index) {
		for(int j=0;j<pane.getChildren().size();j++) {
			if(((Tile) pane.getChildren().get(j)).getYLocation()*4+
					((Tile) pane.getChildren().get(j)).getXLocation()==index)
				return (Tile) pane.getChildren().get(j);
		}
		return null;
	}
	public int getRow(int index) {
		if(index<=15 && index>=11)
			return 3;
		else if(index<11 && index>=8)
			return 2;
		else if(index<=7 && index>=4)
			return 1;
		else return 0;
	}
	public int getColumn(int index) {
		if(index % 4==0) return 0;
		else if(index % 4==1) return 1;
		else if(index % 4 ==2) return 2;
		else return 3;
	}
	public boolean isRight(GridPane pane, int index) {
		Tile tile=null;
		for(int j=0;j<pane.getChildren().size();j++) {
			if(((Tile) pane.getChildren().get(j)).getYLocation()*4+
					((Tile) pane.getChildren().get(j)).getXLocation()==index+1) {
					tile=(Tile) pane.getChildren().get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
	public boolean isLeft(GridPane pane, int index) {
		Tile tile=null;
		for(int j=0;j<pane.getChildren().size();j++) {
			if(((Tile) pane.getChildren().get(j)).getYLocation()*4+
					((Tile) pane.getChildren().get(j)).getXLocation()==index-1) {
					tile=(Tile) pane.getChildren().get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
	public boolean isUp(GridPane pane, int index) {
		Tile tile=null;
		
		for(int j=4;j<pane.getChildren().size();j++) {
			if(((Tile) pane.getChildren().get(j)).getYLocation()*4+
					((Tile) pane.getChildren().get(j)).getXLocation()==index-4) {
					
					tile=(Tile) pane.getChildren().get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
	public boolean isDown(GridPane pane, int index) {
		Tile tile=null;
		for(int j=0;j<pane.getChildren().size();j++) {

			if(((Tile) pane.getChildren().get(j)).getYLocation()*4+
					((Tile) pane.getChildren().get(j)).getXLocation()==index+4) {
					tile=(Tile) pane.getChildren().get(j);
			}
		}
		if(tile instanceof Free)
			return true;
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}

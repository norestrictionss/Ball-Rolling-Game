import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	private GridPane pane;
	private File file;
	private Image image;
	private int rowIndex;
	private int columnIndex;
	private ArrayList<Tile> all_tiles=new ArrayList<>();
	public FileReader(GridPane pane, File file) throws FileNotFoundException {
		this.pane=pane;
		this.file=file;
		this.rowIndex=0;
		this.columnIndex=0;
	}
	public ArrayList<Tile> startReading2() throws FileNotFoundException {
		Scanner input=new Scanner(file);
		String [] arguments;
		while(input.hasNextLine()) {
			arguments=(input.nextLine()).split(",");
			Tile starter=null;
			if(arguments[1].equals("Starter")){
				if(arguments[2].equals("Vertical")) {
					image=new Image("Starter1.png", 70, 70, false, false);
					starter=new Starter(image);
					ImageView actual_image=new ImageView(starter.getImage());
					pane.add(starter, columnIndex, rowIndex);
					System.out.println(pane.getChildren().indexOf(starter));
					all_tiles.add(starter);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("Starter2.png", 70, 70, false, false);
					starter=new Starter(image);
					ImageView actual_image=new ImageView(starter.getImage());
					all_tiles.add(starter);
					pane.add(starter, columnIndex, rowIndex);
				}
				starter.setXLocation(columnIndex);
				starter.setYLocation(rowIndex);
			}
			else if(arguments[1].equals("Empty")) {
				Tile empty=null;
				if(arguments[2].equals("Free")) {
					image=new Image("Free.png", 70, 70, false, false);
					empty=new Empty(image);
					
					ImageView actual_image=new ImageView(empty.getImage());
				
					pane.add(empty, columnIndex, rowIndex);
					all_tiles.add(empty);
				}
				else {
					image=new Image("Empty.png", 70, 70, false, false);
					empty=new Empty(image);
					ImageView actual_image=new ImageView(empty.getImage());
					all_tiles.add(empty);
					pane.add(empty, columnIndex, rowIndex);
				}
				empty.setXLocation(columnIndex);
				empty.setYLocation(rowIndex);
			}
			else if(arguments[1].equals("Pipe")) {
				Tile pipe=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("Pipe1.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("Pipe2.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
				
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("00")) {
					image=new Image("00.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("01")) {
					image=new Image("01.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("10")) {
					image=new Image("10.png", 70, 70, false ,false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					actual_image.setFitHeight(70);
					actual_image.setFitWidth(70);
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("11")) {
					image=new Image("11.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				all_tiles.add(pipe);
				pipe.setXLocation(columnIndex);
				pipe.setYLocation(rowIndex);
			}	
			else if(arguments[1].equals("PipeStatic")) {
				Tile pipeStatic=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("PipeStatic1.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(image);
					ImageView actual_image=new ImageView(pipeStatic.getImage());
					
					pane.add(pipeStatic, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("PipeStatic2.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(image);
					ImageView actual_image=new ImageView(pipeStatic.getImage());
					
					pane.add(pipeStatic, columnIndex, rowIndex);
				}
				all_tiles.add(pipeStatic);
				pipeStatic.setXLocation(columnIndex);
				pipeStatic.setYLocation(rowIndex);
			}
			else if(arguments[1].equals("End")) {
				Tile end=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("End1.png", 70, 70, false, false);
					end=new PipeStatic(image);
					ImageView actual_image=new ImageView(end.getImage());
				
					pane.add(end, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("End2.png", 70, 70, false, false);
					end=new PipeStatic(image);
					ImageView actual_image=new ImageView(end.getImage());
				
					pane.add(end, columnIndex, rowIndex);
				}
				all_tiles.add(end);
				end.setXLocation(columnIndex);
				end.setYLocation(rowIndex);
			}
			if(columnIndex<3)
				columnIndex++;
			else {
				rowIndex++;
				columnIndex=0;
			}
		}
		return all_tiles;
	}
	public void startReading() throws FileNotFoundException {
		Scanner input=new Scanner(file);
		String [] arguments;
		while(input.hasNextLine()) {
		  arguments=(input.nextLine()).split(",");
		  if(arguments.length!=1) {
			  
			if(arguments[1].equals("Starter")){
				Tile starter=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("Starter1.png", 70, 70, false, false);
					starter=new Starter(image);
					ImageView actual_image=new ImageView(starter.getImage());
					pane.add(starter, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("Starter2.png", 70, 70, false, false);
					starter=new Starter(image);
					ImageView actual_image=new ImageView(starter.getImage());
			
					pane.add(starter, columnIndex, rowIndex);
				}
				starter.setXLocation(columnIndex);
				starter.setYLocation(rowIndex);
			}
			else if(arguments[1].equals("Empty")) {
				Tile empty=null;
				if(arguments[2].equals("Free")) {
					image=new Image("Free.png", 70, 70, false, false);
					empty=new Free(image);
					
					ImageView actual_image=new ImageView(empty.getImage());
				
					pane.add(empty, columnIndex, rowIndex);
				
				}
				else {
					image=new Image("Empty.png", 70, 70, false, false);
					empty=new Empty(image);
					ImageView actual_image=new ImageView(empty.getImage());
			
					pane.add(empty, columnIndex, rowIndex);
				}
				empty.setXLocation(columnIndex);
				empty.setYLocation(rowIndex);
			}
			else if(arguments[1].equals("Pipe")) {
				Tile pipe=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("Pipe1.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
				
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("Pipe2.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("00")) {
					image=new Image("00.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("01")) {
					image=new Image("01.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("10")) {
					image=new Image("10.png", 70, 70, false ,false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					actual_image.setFitHeight(70);
					actual_image.setFitWidth(70);
					pane.add(pipe, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("11")) {
					image=new Image("11.png", 70, 70, false, false);
					pipe=new Pipe(image);
					ImageView actual_image=new ImageView(pipe.getImage());
					
					pane.add(pipe, columnIndex, rowIndex);
				}
				pipe.setXLocation(columnIndex);
				pipe.setYLocation(rowIndex);
			}	
			else if(arguments[1].equals("PipeStatic")) {
				Tile pipeStatic=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("PipeStatic1.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(image);
					ImageView actual_image=new ImageView(pipeStatic.getImage());
					
					pane.add(pipeStatic, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("PipeStatic2.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(image);
					ImageView actual_image=new ImageView(pipeStatic.getImage());
					
					pane.add(pipeStatic, columnIndex, rowIndex);
				}
				pipeStatic.setXLocation(columnIndex);
				pipeStatic.setYLocation(rowIndex);
			}
			else if(arguments[1].equals("End")) {
				Tile end=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("End1.png", 70, 70, false, false);
					end=new PipeStatic(image);
					ImageView actual_image=new ImageView(end.getImage());
				
					pane.add(end, columnIndex, rowIndex);
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("End2.png", 70, 70, false, false);
					end=new PipeStatic(image);
					ImageView actual_image=new ImageView(end.getImage());
				
					pane.add(end, columnIndex, rowIndex);
				}
				end.setXLocation(columnIndex);
				end.setYLocation(rowIndex);
			}
			if(columnIndex<3)
				columnIndex++;
			else {
				rowIndex++;
				columnIndex=0;
			}
		  }
		}
	}
}

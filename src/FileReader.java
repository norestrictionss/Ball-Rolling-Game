import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
/* PROJECT NAME: TILE GRID GAME 
CONTRIBUTORS
STUDENT NAME: BARIŞ GİRAY AKMAN -> STUDENT ID: 150121822
STUDENT NAME: FURKAN GÖKGÖZ-> STUDENT ID: 150120076 
PURPOSE OF THAT PROGRAM IS SOLVING THE PUZZLE AND HAVING THE BALL MOVED 
IF THE PUZZLE HAS BEEN SET IN CORRECT COMBINATION.
*/
// This class provides us to read files which is invoked via directory.
public class FileReader {
	public ArrayList<Tile> all_tiles=new ArrayList<>();
	private File file;
	public FileReader(String file_name) {
		this.file=new File(file_name);
	}
	// This method returns ball shape.
	public static Circle getBall() {
		Circle ballTemplate = new Circle();
		ImagePattern gameBall = new ImagePattern(new Image("file:ball.png"));
		ballTemplate.setFill(gameBall);
		ballTemplate.setRadius(18);
		ballTemplate.setEffect(new DropShadow(10,Color.BLACK));
		ballTemplate.setStroke(Color.BLACK);
		
		return ballTemplate;
	}
	// This method returns background.
	public static ImageView getBackground() {
		return new ImageView(new Image("file:Background.png",400,400,true,true));
	}
	// This method reads files.
	public ArrayList<Tile> start_reading() throws FileNotFoundException{
		int id=1;
		Scanner input=new Scanner(file);
		String [] arguments;
		while(input.hasNextLine()) {
			
		  // Line-by-line reading has been done in that loop.
		  // Depending on comma seperator, array has been created.
		  arguments=(input.nextLine()).split(",");
		  Tile starter=null;
		  if(arguments.length!=1) {
			if(arguments[1].equalsIgnoreCase("Starter")){
				if(arguments[2].equalsIgnoreCase("Vertical")) {
					starter=new Starter(id, "Vertical", new ImageView(new Image("file:Starter1.png")));
				}
				else if(arguments[2].equalsIgnoreCase("Horizontal")) {
					starter=new Starter(id, "Horizontal", new ImageView(new Image("file:Starter2.png")));
				}
				all_tiles.add(starter);
				
			}
			/* If the second element of array is equal to empty, empty tile will be created and
			added to all_tiles arraylist. */
			else if(arguments[1].equalsIgnoreCase("Empty")) {
				Tile empty=null;
				/* If the type of empty is free,  free tile will be created and added to 
				 all_tiles arraylist.*/
				if(arguments[2].equalsIgnoreCase("Free")) {
					empty=new Free(id, "Free", new ImageView(new Image("file:Free.png")));
				}
				/* If the type of empty is none, tile will be created and added to all_tiles
				 arraylist. 
				 */
				else {
					empty=new Empty(id, "Empty", new ImageView(new Image("file:Empty.png")));
				}
				all_tiles.add(empty);
			}
			/* If the first element of array is equal to pipe, pip tile will be created and
			added to all_tiles arraylist. */
			else if(arguments[1].equalsIgnoreCase("Pipe")) {
				Tile pipe=null;
				/* If the second element of array is equal to one of strings which is indicated 
				 in if structures, pipe will be created depending it's type and will be added 
				 to all_tiles arraylist.*/
				if(arguments[2].equalsIgnoreCase("Vertical")) {
					pipe=new Pipe(id, "Vertical", new ImageView(new Image("file:Pipe1.png")));
				}
				
				else if(arguments[2].equalsIgnoreCase("Horizontal")) {
					pipe=new Pipe(id, "Horizontal", new ImageView(new Image("file:Pipe2.png")));

				}
				
				else if(arguments[2].equalsIgnoreCase("00")) {
					pipe=new Pipe(id, "00", new ImageView(new Image("file:00.png")));
				}

				else if(arguments[2].equalsIgnoreCase("01")) {
					pipe=new Pipe(id, "01", new ImageView(new Image("file:01.png")));
				}
				else if(arguments[2].equalsIgnoreCase("10")) {
					pipe=new Pipe(id, "10", new ImageView(new Image("file:10.png")));

				}
				else if(arguments[2].equalsIgnoreCase("11")) {
					pipe=new Pipe(id, "11", new ImageView(new Image("file:11.png")));
				}
				all_tiles.add(pipe);
			}
			/* If the first element of array is equal to PipeStatic, PipeStatic tile will be created and
			added to all_tiles arraylist. */
			else if(arguments[1].equalsIgnoreCase("PipeStatic")) {
				Tile pipeStatic=null;
				/* If the second element of array is equal to one of strings which is indicated 
				 in if structures, pipe will be created depending it's type and will be added 
				 to all_tiles arraylist.*/
				if(arguments[2].equalsIgnoreCase("Vertical")) {
					pipeStatic=new PipeStatic(id, "Vertical", new ImageView(new Image("file:PipeStatic1.png")));
				}
				else if(arguments[2].equalsIgnoreCase("Horizontal")) {
					pipeStatic=new PipeStatic(id, "Horizontal", new ImageView(new Image("file:PipeStatic2.png")));
				}
				else if(arguments[2].equalsIgnoreCase("00")) {
					pipeStatic=new PipeStatic(id, "00", new ImageView(new Image("file:Static00.png")));
				}
				else if(arguments[2].equalsIgnoreCase("01")) {
					pipeStatic=new PipeStatic(id, "01", new ImageView(new Image("file:Static01.png")));
				}
				else if(arguments[2].equalsIgnoreCase("10")) {
					pipeStatic=new PipeStatic(id, "10", new ImageView(new Image("file:Static10.png")));
				}
				else if(arguments[2].equalsIgnoreCase("11")) {
					pipeStatic=new Pipe(id, "11", new ImageView(new Image("file:Static11.png")));
				}
				
				all_tiles.add(pipeStatic);
			}
			/* If the first element of array is equal to End, End tile will be created and
			added to all_tiles arraylist. */
			else if(arguments[1].equalsIgnoreCase("End")) {
				Tile end=null;
				/* If the second element of array is equal to one of strings which is indicated 
				 in if structures, pipe will be created depending it's type and will be added 
				 to all_tiles arraylist.*/
				if(arguments[2].equalsIgnoreCase("Vertical")) {
					end=new End(id, "Vertical", new ImageView(new Image("file:End1.png")));
				}
				else if(arguments[2].equalsIgnoreCase("Horizontal")) {
					end=new End(id, "Horizontal", new ImageView(new Image("file:End2.png")));
				}
				all_tiles.add(end);
			}
			id++;
		  }
		}
		// At the last step, input file will be closed and all_tiles arraylist will be returned. 
		input.close();
		return all_tiles;
	}
}

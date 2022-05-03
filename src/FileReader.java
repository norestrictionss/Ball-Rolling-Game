

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class FileReader {
	public ArrayList<Tile> all_tiles=new ArrayList<>();
	private File file;
	public FileReader(String file_name) {
		this.file=new File(file_name);
	}
	public static Circle getBall() {
		Circle ballTemplate = new Circle();
		ImagePattern gameBall = new ImagePattern(new Image("file:ball.png"));
		ballTemplate.setFill(gameBall);
		ballTemplate.setRadius(18);
		ballTemplate.setEffect(new DropShadow(10,Color.BLACK));
		ballTemplate.setStroke(Color.BLACK);
		
		return ballTemplate;
	}
	public static ImageView getBackground() {
		return new ImageView(new Image("file:Background.png",400,400,true,true));
	}
	public ArrayList<Tile> start_reading() throws FileNotFoundException{
		int id=1;
		Scanner input=new Scanner(file);
		String [] arguments;
		while(input.hasNextLine()) {

		  arguments=(input.nextLine()).split(",");
		  Tile starter=null;
		  if(arguments.length!=1) {
			if(arguments[1].equals("Starter")){
				if(arguments[2].equals("Vertical")) {
					starter=new Starter(id, "Vertical", new ImageView(new Image("file:Starter1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					starter=new Starter(id, "Horizontal", new ImageView(new Image("file:Starter2.png")));
				}
				all_tiles.add(starter);
				
			}
			else if(arguments[1].equals("Empty")) {
				Tile empty=null;
				if(arguments[2].equals("Free")) {
					empty=new Free(id, "Free", new ImageView(new Image("file:Free.png")));
				}
				else {
					empty=new Empty(id, "Empty", new ImageView(new Image("file:Empty.png")));
				}
				all_tiles.add(empty);
			}
			else if(arguments[1].equals("Pipe")) {
				Tile pipe=null;
				if(arguments[2].equals("Vertical")) {
					pipe=new Pipe(id, "Vertical", new ImageView(new Image("file:Pipe1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					pipe=new Pipe(id, "Horizontal", new ImageView(new Image("file:Pipe2.png")));

				}
				else if(arguments[2].equals("00")) {
					pipe=new Pipe(id, "00", new ImageView(new Image("file:00.png")));
				}
				else if(arguments[2].equals("01")) {
					pipe=new Pipe(id, "01", new ImageView(new Image("file:01.png")));
				}
				else if(arguments[2].equals("10")) {
					pipe=new Pipe(id, "10", new ImageView(new Image("file:10.png")));

				}
				else if(arguments[2].equals("11")) {
					pipe=new Pipe(id, "11", new ImageView(new Image("file:11.png")));
				}
				all_tiles.add(pipe);
			}	
			else if(arguments[1].equals("PipeStatic")) {
				Tile pipeStatic=null;
				if(arguments[2].equals("Vertical")) {
					pipeStatic=new PipeStatic(id, "Vertical", new ImageView(new Image("file:PipeStatic1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					pipeStatic=new PipeStatic(id, "Horizontal", new ImageView(new Image("file:PipeStatic2.png")));
				}
				else if(arguments[2].equals("00")) {
					pipeStatic=new PipeStatic(id, "00", new ImageView(new Image("file:Static00.png")));
				}
				else if(arguments[2].equals("01")) {
					pipeStatic=new PipeStatic(id, "01", new ImageView(new Image("file:Static01.png")));
				}
				else if(arguments[2].equals("10")) {
					pipeStatic=new PipeStatic(id, "10", new ImageView(new Image("file:Static10.png")));
				}
				else if(arguments[2].equals("11")) {
					pipeStatic=new Pipe(id, "11", new ImageView(new Image("file:Static11.png")));
				}
				
				all_tiles.add(pipeStatic);
			}
			else if(arguments[1].equals("End")) {
				Tile end=null;
				if(arguments[2].equals("Vertical")) {
					end=new End(id, "Vertical", new ImageView(new Image("file:End1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					end=new End(id, "Horizontal", new ImageView(new Image("file:End2.png")));
				}
				all_tiles.add(end);
			}
			id++;
		  }
		}
		input.close();
		return all_tiles;
	}
}


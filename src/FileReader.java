

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FileReader {
	public ArrayList<Tile> all_tiles=new ArrayList<>();
	private File file;
	public FileReader(String file_name) {
		this.file=new File(file_name);
	}
	public ArrayList<Tile> start_reading() throws FileNotFoundException{
		int id=1;
		Scanner input=new Scanner(file);
		String [] arguments;
		while(input.hasNextLine()) {

		  arguments=(input.nextLine()).split(",");
		  Tile starter=null;
		  if(arguments.length!=1) {
			  Image image;
			if(arguments[1].equals("Starter")){
				if(arguments[2].equals("Vertical")) {
					image=new Image("file:Starter1.png", 70, 70, false, false);
					starter=new Starter(id, "Starter", new ImageView(new Image("file:Starter1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("file:Starter2.png", 70, 70, false, false);
					starter=new Starter(id, "Starter", new ImageView(new Image("file:Starter2.png")));
				}
				all_tiles.add(starter);
				
			}
			else if(arguments[1].equals("Empty")) {
				Tile empty=null;
				if(arguments[2].equals("Free")) {
					image=new Image("file:Free.png", 70, 70, false, false);
					empty=new Free(id, "Free", new ImageView(new Image("file:Free.png")));
				}
				else {
					image=new Image("file:Empty.png", 70, 70, false, false);
					empty=new Empty(id, "Empty", new ImageView(new Image("file:Empty.png")));
				}
				all_tiles.add(empty);
			}
			else if(arguments[1].equals("Pipe")) {
				Tile pipe=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("file:Pipe1.png", 70, 70, false, false);
					pipe=new Pipe(id, "Vertical", new ImageView(new Image("file:Pipe1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("file:Pipe2.png", 70, 70, false, false);
					pipe=new Pipe(id, "Horizontal", new ImageView(new Image("file:Pipe2.png")));

				}
				else if(arguments[2].equals("00")) {
					image=new Image("file:00.png", 70, 70, false, false);
					pipe=new Pipe(id, "00", new ImageView(new Image("file:00.png")));
				}
				else if(arguments[2].equals("01")) {
					image=new Image("file:01.png", 70, 70, false, false);
					pipe=new Pipe(id, "01", new ImageView(new Image("file:01.png")));
				}
				else if(arguments[2].equals("10")) {
					image=new Image("file:10.png", 70, 70, false ,false);
					pipe=new Pipe(id, "10", new ImageView(new Image("file:10.png")));

				}
				else if(arguments[2].equals("11")) {
					image=new Image("file:11.png", 70, 70, false, false);
					pipe=new Pipe(id, "11", new ImageView(new Image("file:11.png")));
				}
				all_tiles.add(pipe);
			}	
			else if(arguments[1].equals("PipeStatic")) {
				Tile pipeStatic=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("file:PipeStatic1.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(id, "Vertical", new ImageView(new Image("file:PipeStatic1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("file:PipeStatic2.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(id, "Horizontal", new ImageView(new Image("file:PipeStatic2.png")));
				}
				else if(arguments[2].equals("00")) {
					image=new Image("file:00.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(id, "00", new ImageView(new Image("file:Static00.png")));
				}
				else if(arguments[2].equals("01")) {
					image=new Image("file:01.png", 70, 70, false, false);
					pipeStatic=new PipeStatic(id, "01", new ImageView(new Image("file:Static01.png")));
				}
				else if(arguments[2].equals("10")) {
					image=new Image("file:10.png", 70, 70, false ,false);
					pipeStatic=new PipeStatic(id, "10", new ImageView(new Image("file:Static10.png")));
				}
				else if(arguments[2].equals("11")) {
					image=new Image("file:11.png", 70, 70, false, false);
					pipeStatic=new Pipe(id, "11", new ImageView(new Image("file:Static11.png")));
				}
				
				all_tiles.add(pipeStatic);
			}
			else if(arguments[1].equals("End")) {
				Tile end=null;
				if(arguments[2].equals("Vertical")) {
					image=new Image("file:End1.png", 70, 70, false, false);
					end=new End(id, "Vertical", new ImageView(new Image("file:End1.png")));
				}
				else if(arguments[2].equals("Horizontal")) {
					image=new Image("file:End2.png", 70, 70, false, false);
					end=new End(id, "Horizontal", new ImageView(new Image("file:End2.png")));
				}
				all_tiles.add(end);
			}
			id++;
		  }
		}
		return all_tiles;
	}
}

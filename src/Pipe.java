import javafx.scene.image.ImageView;
/* PROJECT NAME: TILE GRID GAME 
CONTRIBUTORS
STUDENT NAME: BARIŞ GİRAY AKMAN -> STUDENT ID: 150121822
STUDENT NAME: FURKAN GÖKGÖZ-> STUDENT ID: 150120076 
PURPOSE OF THAT PROGRAM IS SOLVING THE PUZZLE AND HAVING THE BALL MOVED 
IF THE PUZZLE HAS BEEN SET IN CORRECT COMBINATION.
*/
//That class represents Pipe tile.
//That class inherits the features of Pipe class and implements movable interface.
public class Pipe extends Tile implements Movable{
	public Pipe(int id, String property, ImageView image) {
		super(id, property, image);
	}
}

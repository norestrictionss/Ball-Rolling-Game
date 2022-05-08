import javafx.scene.image.ImageView;
/* PROJECT NAME: TILE GRID GAME 
CONTRIBUTORS
STUDENT NAME: BARIŞ GİRAY AKMAN -> STUDENT ID: 150121822
STUDENT NAME: FURKAN GÖKGÖZ-> STUDENT ID: 150120076 
PURPOSE OF THAT PROGRAM IS SOLVING THE PUZZLE AND HAVING THE BALL MOVED 
IF THE PUZZLE HAS BEEN SET IN CORRECT COMBINATION.
*/
public abstract class Tile {
	private int id; // ID of tile
	private String property; // This variable indicates the type of tile
	private ImageView image; // This variable hold the picture of tile.
	
	private static int totalMoves; // This variable hold total move  which was done by the player.
	private static boolean isAnimationOngoing; /* This variable indicates whether 
	animation is going or not. */
	
	// Parameterized constructor
	public Tile(int id, String property, ImageView image) {
		this.setId(id);
		this.setProperty(property);
		this.setImage(image);
	}
	// Setter-getter methods
	public static void setIsAnimationOngoing(boolean isAnimationOnGoing) {
		Tile.isAnimationOngoing = isAnimationOnGoing;
	}
	
	public static boolean getIsAnimationOnGoing() {
		return isAnimationOngoing;
	}
	
	public static void incrementTotalMoves(){
		totalMoves++;
	}
	
	public static int getTotalMoves() {
		return totalMoves;
	}
	
	public static void setTotalMoves(int totalMoves) {
		Tile.totalMoves = totalMoves;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProperty() {
		return property;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	public ImageView getImage() {
		return image;
	}
	
	public void setImage(ImageView image) {
		this.image = image;
	}

	
}

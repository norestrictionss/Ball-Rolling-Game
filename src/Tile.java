


import javafx.scene.image.ImageView;

public abstract class Tile {
	private int id;
	private String property;
	private ImageView image;
	private static int totalMoves;
	public Tile(int id, String property, ImageView image) {
		this.setId(id);
		this.setProperty(property);
		this.setImage(image);
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




import javafx.scene.image.ImageView;

public abstract class Tile {
	private int id;
	private String property;
	private ImageView image;
	private int rowIndex;
	private int columnIndex;
	private static int totalMoves;
	private static boolean isAnimationOngoing;
	public Tile(int id, String property, ImageView image) {
		this.setId(id);
		this.setProperty(property);
		this.setImage(image);
	}
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
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	
}
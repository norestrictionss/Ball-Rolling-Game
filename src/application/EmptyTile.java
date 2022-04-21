package application;

public class EmptyTile extends Tile implements Movable{
	private int location;
	private final String imageFileName = "Empty.png";
	public EmptyTile(int location) {
		super();
		this.location = location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	public int getLocation() {
		return location;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
}

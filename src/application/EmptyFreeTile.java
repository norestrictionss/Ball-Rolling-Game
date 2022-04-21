package application;

public class EmptyFreeTile extends Tile implements Movable{
	private int location;
	private final String imageFileName = "EmptyFree.png";
	public EmptyFreeTile(int location) {
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

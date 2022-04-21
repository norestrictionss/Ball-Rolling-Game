package application;

public class EndTile extends Tile implements hasPipe {
	private final int pipeDirection;
	private static int location;
	
	public EndTile(int pipeDirection,int location) {
		super();
		super.imageFileName = "End" + pipeDirection + ".png";
		this.pipeDirection = pipeDirection;
		this.location = location;
	}
	
	public int getLocation() {
		return location;
	}
	
	public int getPipeDirection() {
		return pipeDirection;
	}

	public String getImageFileName() {
		return imageFileName;
	}
}

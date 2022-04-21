package application;

public class StarterTile extends Tile implements hasPipe{
	private final int pipeDirection;
	private static int location;
	public StarterTile(int pipeDirection,int location) {
		super();
		super.imageFileName = "Starter" + pipeDirection + ".png";
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
		return super.imageFileName;
	}
	
}

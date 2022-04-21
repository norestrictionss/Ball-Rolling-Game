package application;

public class StaticPipeTile extends Tile implements hasPipe {
	
	private final int pipeDirection;
	private static int location; 
	public StaticPipeTile(int pipeDirection,int location) {
		super();
		super.imageFileName = "PipeStatic" + pipeDirection + ".png";
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

package application;

public class CurvedPipeTile extends Tile implements hasPipe,Movable{
	
	/*Need variable as int because of hasPipe interface forces the method public int getPipeDirection meaning pipeDirection needs to be an int*/
	private final int pipeDirection;
	private int location; 

	public CurvedPipeTile(String pipeDirection,int location) {
		super();
		super.imageFileName = "CurvedPipe" + pipeDirection + ".png";
		this.pipeDirection = byteStringToInt(pipeDirection);
		this.location = location;
	}
	
	private int byteStringToInt(String byteString) {
		return 2 * (byteString.charAt(0) - ('0')) + byteString.charAt(1) - ('0');
	}
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public int getPipeDirection() {
		return pipeDirection;
	}

	public String getImageFileName() {
		return imageFileName;
	}
	



	
}

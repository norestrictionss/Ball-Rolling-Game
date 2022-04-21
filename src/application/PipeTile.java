package application;

public class PipeTile extends Tile implements hasPipe,Movable{
		private final int pipeDirection;
		private int location; 
		public PipeTile(int pipeDirection,int location) {
			super();
			super.imageFileName = "Pipe" + pipeDirection + ".png";
			this.pipeDirection = pipeDirection;
			this.location = location;
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

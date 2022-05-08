
public class Level {
	private int id;
	private int levelsFinished = 0;
	private int[] moveCounter = new int[10];
	public Level(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void decrementId() {
		id--;
	}
	public void incrementId() {
		id++;
	}
	public void incrementLevelsFinished() {
		levelsFinished++;
	}
	public int getLevelsFinished() {
		return levelsFinished;
	}

	public void setCounterFor(int index,int moves) {
		moveCounter[index] = moves;
	}
	 public int getCounterFor(int index) {
	       return moveCounter[index];
	 }
	public int incrementCounterFor(int index) {
		return ++moveCounter[index];
	}
	
}

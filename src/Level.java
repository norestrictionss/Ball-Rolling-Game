
public class Level {
	private int id;
	private final int TOTAL_LEVELS = 15;
	private int levelsFinished = 0;
	private int[] moveCounter = new int[16];
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
		if(levelsFinished < 20)
		levelsFinished++;
	}
	public int getLevelsFinished() {
		return levelsFinished;
	}
	public int  getTotalLevels() {
		return TOTAL_LEVELS;
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

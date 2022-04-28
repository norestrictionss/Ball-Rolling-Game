
public class Level {
	private int id;
	private final int TOTAL_LEVELS = 15;
	private int levelsFinished = 0;
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
	
}

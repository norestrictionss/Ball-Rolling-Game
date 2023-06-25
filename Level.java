/* PROJECT NAME: TILE GRID GAME 
CONTRIBUTORS
STUDENT NAME: BARIÞ GÝRAY AKMAN -> STUDENT ID: 150121822
STUDENT NAME: FURKAN GÖKGÖZ-> STUDENT ID: 150120076 
PURPOSE OF THAT PROGRAM IS SOLVING THE PUZZLE AND HAVING THE BALL MOVED 
IF THE PUZZLE HAS BEEN SET IN CORRECT COMBINATION.
*/

public class Level {
    private int id; // This variable presents the id of the level.
    private int levelsFinished = 0;
    private int[] moveCounter = new int[1];  // This array holds total moves done for each level.

    // Parameterized constructor
    public Level(int id) {
        this.id = id;

    }
    public int getId() {
        return id;
    }
    // incrementId and decrementId methods increments and decrements id respectively.
    public void decrementId() {
        id--;
    }
    public void incrementId() {
        id++;
    }
    // This method increments total levels finished in game.
    public void incrementLevelsFinished() {
        levelsFinished++;
    }
    // This method returns number of levels finished.
    public int getLevelsFinished() {
        return levelsFinished;
    }
    // This method sets moveCounter array's elements depending on index given as parameter.
    public void setCounterFor(int index,int moves) {
        moveCounter[index] = moves;
    }
    // This method gets any element of getCounter array.
    // If index exceeds the last index of moveCounter array, new array with more size will be created.
    // This new array will be assigned to moveCounter.
    public int getCounterFor(int index) {
        if(index>moveCounter.length-1) {
            int [] moveCounter2=new int[moveCounter.length+1];
            for(int j=0;j<moveCounter.length;j++) 
                moveCounter2[j]=moveCounter[j];
            moveCounter=moveCounter2;
        }
        return moveCounter[index];
    }
    public int incrementCounterFor(int index) {
        return ++moveCounter[index];
    }

}

import java.util.ArrayList;
/* PROJECT NAME: TILE GRID GAME 
CONTRIBUTORS
STUDENT NAME: BARIÞ GÝRAY AKMAN -> STUDENT ID: 150121822
STUDENT NAME: FURKAN GÖKGÖZ-> STUDENT ID: 150120076 
PURPOSE OF THAT PROGRAM IS SOLVING THE PUZZLE AND HAVING THE BALL MOVED 
IF THE PUZZLE HAS BEEN SET IN CORRECT COMBINATION.
*/

// This class checks the path which created by the player.

public class CheckPath {
	/* If path that player has created is correct, generatePath method  returns 
	the blocks that forms path as an arraylist. */
	public static ArrayList<Tile> generatePath(ArrayList<Tile> all_tiles){
		ArrayList<Tile> path=new ArrayList<>(); /* In each invocation of method, 
		new path arraylist is being created. */
		path.add(all_tiles.get(findStarterIndex(all_tiles))); /* Initially, index of starter
		is found.*/
		Tile currentTile; /* This object will hold the tile which is achieved in while loop 
		in each iteration. */
		int i=findStarterIndex(all_tiles);
		/* Loop continues until program reaches the end block. But there is 
		 one exception. If path can't be set by the user correctly, method will return null. 
		 */
		while(!(path.get(path.size()-1).getTile_type().equals("End"))) {
				currentTile = all_tiles.get(i); /* currentTile variable will be updated 
				depending on i value. */
				/* While checking the block around currentTile, it is also checked that if 
				 blocks around currentTile is already exists in path arraylist. Otherwise, 
				 program won't work completely.*/
				
				/*If current tile suits with the block which is at it's upper side, 
				i value will be decremented 4 and the upper block will be added to path list .*/
				if(i>=4 && (currentTile.getProperty().equals("Vertical") || currentTile.getProperty().equals("00") || 
                currentTile.getProperty().equals("01")) && 
				   (all_tiles.get(i-4).getProperty().equals("Vertical") || all_tiles.get(i-4).getProperty().equals("10")
                   || all_tiles.get(i-4).getProperty().equals("11")) && 
				   !isInList(path, all_tiles.get(i-4)) && i % 4 == (i-4) % 4){
					path.add(all_tiles.get(i-4));
					i-=4;
				}
				/* If current tile suits with the block which is at it's down side, i value incremented
				 for 4 and the lower block will be added to path list. */
				else if(i<12 && (currentTile.getProperty().equals("Vertical") || currentTile.getProperty().equals("10") || 
                currentTile.getProperty().equals("11")) && 
					(all_tiles.get(i+4).getProperty().equals("Vertical") || all_tiles.get(i+4).getProperty().equals("00")
                    || all_tiles.get(i+4).getProperty().equals("01")) && 
					!isInList(path, all_tiles.get(i+4)) && i%4 == (i+4) %4){
					
					path.add(all_tiles.get(i+4));
					i+=4;
				}
				/* If current tile suits with block which is at it's left side, i value
				 will be decremented for 1 and the block at the left side will be added to path list. 
				 */
				else if(i>=1 && (currentTile.getProperty().equals("10") || currentTile.getProperty().equals("Horizontal") || 
                currentTile.getProperty().equals("00")) && 
						(all_tiles.get(i-1).getProperty().equals("11") || all_tiles.get(i-1).getProperty().equals("01") || 
                        all_tiles.get(i-1).getProperty().equals("Horizontal")) && 
						!isInList(path, all_tiles.get(i-1)) && (i-1)/4==i/4) {
						path.add(all_tiles.get(i-1));
						i-=1;
				}
				/* If current tile suits with block which is at it's right side, i value
				 will be decremented for 1 and the block at the right side will be added to path list. */
				else if(i<15 && (currentTile.getProperty().equals("01") || currentTile.getProperty().equals("11") || 
                currentTile.getProperty().equals("Horizontal")) && 
						(all_tiles.get(i+1).getProperty().equals("00") || all_tiles.get(i+1).getProperty().equals("10") ||
                        all_tiles.get(i+1).getProperty().equals("Horizontal")) && 
						!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
						path.add(all_tiles.get(i+1));
						i+=1;
				}
				/* If none of those if's don't work, that means current tile doesn't suit 
				 any blocks that covers around current tile. 
				 So, method will return null in that circumstance. 
				 */
				else {
					return null;
				}
		}
		/* If path has been set succesfully by the player, path will be returned as 
		arraylist. 
		*/
		
		return path;
	}
	/* This method checks if the tile which is entered as a second argument presents in 
	 path arraylist. If it presents, method will return true. Otherwise, it will return false. 
	 */
	private static boolean isInList(ArrayList<Tile> path, Tile tile) {
		boolean isInArrayList = false;
		for (int i = 0; i < path.size(); i++) {
			if(path.get(i) == tile)
				isInArrayList = true;
		}
		return isInArrayList;
	}
	// As it stands, this method finds the starter block's index.
	public static int findStarterIndex(ArrayList<Tile> all_tiles) {
			for(int i = 0; i < all_tiles.size(); i++) {

				if(all_tiles.get(i).getTile_type().equals("Starter")) {
					return i;
				}
					
			}
			return -1;
	}
	
 
}
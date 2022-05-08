import java.util.ArrayList;
import javafx.scene.shape.*;

public class CircleAnimation {
	public static Path returnCirclePath(ArrayList<Tile> tileArray,ArrayList<Tile> all_tiles) {
		/*Declaring variables*/
		/*Direction is 1 for north,2 for east,3 for south,4 for west*/
		Path returnPath = new Path();
		Tile currentTile;
		String currentTileString = "1";
		boolean isArcPositive = false;
		
		/*Getting direction for the starting tile,also the same variable going to be used for getting direction*/
		int startingDirection = Integer.parseInt(findDirectionString(tileArray.get(0))) == 1 ? 3 : 4;
		/*Index for finding location of the tile in the all tile*/
		int starterIndex = all_tiles.indexOf(tileArray.get(0));
		/*Starting point for the path*/
		returnPath.getElements().add(new MoveTo((starterIndex % 4) * 150 + 72,(starterIndex / 4) * 150 + 78));
		/*Loop for finding all path except end tiles*/
		for(int i = 0; i < tileArray.size() - 1; i++) {
			/*To avoid repetitiveness,added variables for use*/
			currentTile = tileArray.get(i);
			currentTileString = findDirectionString(currentTile);
			/*Finds the next direction and the arc value for animation*/
			switch(currentTileString) {
				case "1" : 
				case "2" : 
					break;
					/*For cases with 2 values,it checks for one of the direction and if that's incorrect selects the other value*/
				case "01" :if (startingDirection == 3) {
					startingDirection = 2;
					isArcPositive = false;
				}
				else { startingDirection = 1;
				isArcPositive = true;
				}
					break;
				case "10" :  if(startingDirection == 1) {
					startingDirection = 4;
					isArcPositive = false ;
				}
				else {startingDirection = 3;
				isArcPositive = true;
				}
					break;
				case "11" : if(startingDirection == 1) {
					startingDirection = 2;
					isArcPositive = true ;
				}
				else {startingDirection = 3;
				isArcPositive = false;
				}
					break;
				case "00" : if(startingDirection == 2) {
					startingDirection = 1;
					isArcPositive = false ;
				}
				else {startingDirection = 4;
				isArcPositive = true;
				}
					break;
			}
			/*Finds the ArcTo or LineTo and adds it to path*/
			returnPath.getElements().add(findTilePath(currentTile,startingDirection,currentTileString.length(),isArcPositive,all_tiles));
			
			}
		/*Its not starting direction,just reusing variables*/
			startingDirection = all_tiles.indexOf(tileArray.get(tileArray.size()-1));
			/*Find the middle of the end tile,basically the ending point for the animation*/
		returnPath.getElements().add(new LineTo(150 * (startingDirection % 4) + 72.0,150 * (startingDirection / 4) + 78.0));
		return returnPath;	
	}
	
	/*Returns ArcTo or LineTo accordingly*/
	private static PathElement findTilePath(Tile currentTile,int direction,int length,boolean sweepFlag,ArrayList<Tile> all_tiles) {
		/*Change coordinates for the tile.*/
			double[] coordinates = directionToCoordinateTranslation(direction);
			/*Finds the index of the tile*/
			int currentIndex = all_tiles.indexOf(currentTile);
			/*Depending on the length (either 01,10,11,00 or 1,2,3,4) of the string, returns ArcTo or LineTo*/
			if(length == 2)
				/*80,80 for X and Y radius during animation, then X,Y coordinates for the location of the ArcTo/LineTo ending, false for largearcflag and sweepflag for the direction of the animation which was given*/
				return new ArcTo(80,80,0,150 * (currentIndex % 4) + coordinates[0],150 * (currentIndex / 4) + coordinates[1],false,sweepFlag);
			else 
				return new LineTo(150 * (currentIndex % 4) + coordinates[0],150 * (currentIndex / 4) + coordinates[1]);
		
	}
	/*Finds the numbers for direction from filename*/
	private static String findDirectionString(Tile tile) {
		/*Getting the filename*/
		String urlString = tile.getImage().getImage().getUrl();
			/*Variables*/
			String directionString = "";
			char currentChar;
			/*Adds number to directionstring if they are a number between 0 to 4*/
			for(int i = 0; i < urlString.length(); i++) {
				currentChar = urlString.charAt(i);
				switch(currentChar) {
				case '0' : 
				case '1' : 
				case '2' : 
				case '3' : 
				case '4' : directionString += currentChar;
				break;
				default :
				}
			
		
			}
			return directionString;
		
			
	}
	/*Purpose of it is to find the middle of any of it's sides according to direction*/
	private static double[] directionToCoordinateTranslation(int direction) {
		/*For saving the change of coordinates in a two sized array
		 * X for 0th index
		 * Y for 1st index*/
		double[] coordinates = new double[2];

		switch(direction) {
		case 1 : coordinates[0] = 72;
			coordinates[1] = 0;
			break;
		case 2 : coordinates[0] = 150;
		coordinates[1] = 78; 
			break;
		case 3 : coordinates[0] = 72;
		coordinates[1] = 150;
			break;
		case 4 : coordinates[0] = 0;
		coordinates[1] = 78;
			break;
			
		}
		return coordinates;
	}
}

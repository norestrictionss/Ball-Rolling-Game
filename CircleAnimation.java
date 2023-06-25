
import java.util.ArrayList;
import javafx.scene.shape.*;
/* PROJECT NAME: TILE GRID GAME 
CONTRIBUTORS
STUDENT NAME: BARIÞ GÝRAY AKMAN -> STUDENT ID: 150121822
STUDENT NAME: FURKAN GÖKGÖZ-> STUDENT ID: 150120076 
PURPOSE OF THAT PROGRAM IS SOLVING THE PUZZLE AND HAVING THE BALL MOVED 
IF THE PUZZLE HAS BEEN SET IN CORRECT COMBINATION.
*/
public class CircleAnimation {
	public static Path returnCirclePath(ArrayList<Tile> tileArray,ArrayList<Tile> all_tiles) {
		Path returnPath = new Path();
		Tile currentTile;
		String currentTileString = "1";
		boolean isArcPositive = false;
		PathElement currentPathElement;

		int startingDirection = Integer.parseInt(findDirectionString(tileArray.get(0))) == 1 ? 3 : 4;
		int currentIndex = all_tiles.indexOf(tileArray.get(0));
		returnPath.getElements().add(new MoveTo((currentIndex % 4) * 150 + 72,(currentIndex / 4) * 150 + 75));
		for(int i = 0; i < tileArray.size() - 1; i++) {
			currentTile = tileArray.get(i);
			currentTileString = findDirectionString(currentTile);
			switch(currentTileString) {
			case "1" : 
            case "2" : 
                break;
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
			currentPathElement = findTilePath(currentTile,startingDirection,currentTileString.length(),isArcPositive,all_tiles);
			returnPath.getElements().add(currentPathElement);
			
			}
		/*Its not starting direction,just reusing variables,will be fixed later on*/
			startingDirection = all_tiles.indexOf(tileArray.get(tileArray.size()-1));
		returnPath.getElements().add(new LineTo(150 * (startingDirection % 4) + 75.0,150 * (startingDirection / 4) +75.0));
		return returnPath;	
	}
	
	public static int directionCorrespondence(int direction) {
		switch (direction) {
		case 1 : return 3;
		case 2 : return 4;
		case 3 : return 1;
		case 4 : return 2;
		default : return 0;
		}
	}
	public static PathElement findTilePath(Tile currentTile,int direction,int length,boolean sweepFlag,ArrayList<Tile> all_tiles) {
			double[] coordinates = directionToCoordinateTranslation(direction);
			int currentIndex = all_tiles.indexOf(currentTile);
			if(length == 2)
				return new ArcTo(75,75,0,150 * (currentIndex % 4) + coordinates[0],150 * (currentIndex / 4) + coordinates[1],false,sweepFlag);
			else 
			
			return new LineTo(150 * (currentIndex % 4) + coordinates[0],150 * (currentIndex / 4) + coordinates[1]);
		
	}
	public static String findDirectionString(Tile tile) {
		
		String urlString = tile.getImage().getImage().getUrl();
		
			String directionString = "";
			char currentChar;
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
	public static double[] directionToCoordinateTranslation(int direction) {
		double[] coordinates = new double[2];
		switch(direction) {
		case 1 : coordinates[0] = 75;
			coordinates[1] = 0;
			break;
		case 2 : coordinates[0] = 150;
		coordinates[1] = 75; 
			break;
		case 3 : coordinates[0] = 75;
		coordinates[1] = 150;
			break;
		case 4 : coordinates[0] = 0;
		coordinates[1] = 75;
			break;
			
		}
		return coordinates;
	}
}

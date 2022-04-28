import java.util.ArrayList;

public class CheckPath {
	public static ArrayList<Tile> generatePath(ArrayList<Tile> all_tiles){
		ArrayList<Tile> path=new ArrayList<>(0);
		path.add(all_tiles.get(findStarterIndex(all_tiles)));
		Tile currentTile;
		int i=findStarterIndex(all_tiles);
		while(!(path.get(path.size()-1) instanceof End)) {
			System.out.println(path);
				currentTile = all_tiles.get(i);
				if(i>=4 && currentTile.getProperty().equals("Vertical") && 
				   all_tiles.get(i-4).getProperty().equals("Vertical") && 
				   !isInList(path, all_tiles.get(i-4)) && i % 4 == (i-4) % 4){
					path.add(all_tiles.get(i-4));
					i-=4;
				}
				else if(i<12 && currentTile.getProperty().equals("Vertical") && 
					all_tiles.get(i+4).getProperty().equals("Vertical") && 
					!isInList(path, all_tiles.get(i+4)) && i%4 == (i+4) %4){
					
					path.add(all_tiles.get(i+4));
					i+=4;
				}
				else if(i>=4 && currentTile.getProperty().equals("Vertical") && 
					all_tiles.get(i-4).getProperty().equals("10") && 
					!isInList(path, all_tiles.get(i-4)) && (i-4) %4 ==i%4) {
					path.add(all_tiles.get(i-4));
					i-=4;
				}
				else if(i>=4 && currentTile.getProperty().equals("Vertical") && 
					all_tiles.get(i-4).getProperty().equals("11") && 
					!isInList(path, all_tiles.get(i-4)) && (i-4) % 4 ==i%4) {
					path.add(all_tiles.get(i-4));
					i-=4;
				}
				else if(i<12 && currentTile.getProperty().equals("Vertical") && 
					all_tiles.get(i+4).getProperty().equals("00") && 
					!isInList(path, all_tiles.get(i+4)) && (i+4) % 4 ==i%4){
					path.add(all_tiles.get(i+4));
					i+=4;
				}
				else if(i<12 && currentTile.getProperty().equals("Vertical") && 
					all_tiles.get(i+4).getProperty().equals("01") && 
					!isInList(path, all_tiles.get(i+4)) && (i+4) % 4 ==i%4){
					path.add(all_tiles.get(i+4));
					i+=4;
				}
				else if(i>=4 && (currentTile.getProperty().equals("00")) && 
					all_tiles.get(i-4).getProperty().equals("Vertical") && 
					!isInList(path, all_tiles.get(i-4)) && (i-4) % 4 ==i%4) {
					path.add(all_tiles.get(i-4));
					i-=4;
				}
				else if(i>=1 && (currentTile.getProperty().equals("00")) && 
					all_tiles.get(i-1).getProperty().equals("Horizontal") && 
					!isInList(path, all_tiles.get(i-1)) && (i-1) / 4 ==i/4) {
					path.add(all_tiles.get(i-1));
					i-=1;
				}
				else if(i>=4 && (currentTile.getProperty().equals("00")) && 
						all_tiles.get(i-4).getProperty().equals("10") && 
						!isInList(path, all_tiles.get(i-4)) &&  (i-4) % 4 ==i%4) {
						path.add(all_tiles.get(i-4));
						i-=4;
				}
				else if(i>=4 && (currentTile.getProperty().equals("00")) && 
						all_tiles.get(i-4).getProperty().equals("11") && 
						!isInList(path, all_tiles.get(i-4)) && (i-4) % 4 ==i%4) {
						path.add(all_tiles.get(i-4));
						i-=4;
				}
				else if(i<15 && (currentTile.getProperty().equals("00")) && 
						all_tiles.get(i-1).getProperty().equals("11") && 
						!isInList(path, all_tiles.get(i-1)) && (i-1) / 4 ==i/4) {
						path.add(all_tiles.get(i-1));
						i-=1;
				}
				else if(i<15 && (currentTile.getProperty().equals("00")) && 
						all_tiles.get(i-1).getProperty().equals("01") && 
						!isInList(path, all_tiles.get(i-1)) && (i-1) / 4 ==i/4) {
						path.add(all_tiles.get(i-1));
						i-=1;
				}
				else if(i>=4 && (currentTile.getProperty().equals("01")) && 
					all_tiles.get(i-4).getProperty().equals("Vertical") && 
					!isInList(path, all_tiles.get(i-4)) && (i-4) %4 ==i%4) {
					path.add(all_tiles.get(i-4));
					i-=4;
				}
				
				else if(i<15 && (currentTile.getProperty().equals("01")) && 
						all_tiles.get(i+1).getProperty().equals("00") && 
						!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
						path.add(all_tiles.get(i+1));
						i+=1;
				}
				else if(i<15 && (currentTile.getProperty().equals("01")) && 
						all_tiles.get(i+1).getProperty().equals("10") && 
						!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
						path.add(all_tiles.get(i+1));
						i+=1;
				}
				else if(i<12 && (currentTile.getProperty().equals("01")) && 
						all_tiles.get(i-4).getProperty().equals("10") && 
						!isInList(path, all_tiles.get(i-4)) && (i-4)%4==i%4) {
						path.add(all_tiles.get(i-4));
						i-=4;
				}
				else if(i<15 && (currentTile.getProperty().equals("01")) && 
					all_tiles.get(i+1).getProperty().equals("Horizontal") && 
					!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
					path.add(all_tiles.get(i+1));
					i+=1;
				}
				else if(i<12 && (currentTile.getProperty().equals("10")) && 
					all_tiles.get(i+4).getProperty().equals("Vertical") && 
					!isInList(path, all_tiles.get(i+4)) && (i+4)%4==i%4) {
					path.add(all_tiles.get(i+4));
					i+=4;
				}
				else if(i>1 && (currentTile.getProperty().equals("10")) && 
						all_tiles.get(i-1).getProperty().equals("11") && 
						!isInList(path, all_tiles.get(i-1)) && (i-1)/4==i/4) {
						path.add(all_tiles.get(i-1));
						i-=1;
				}
				else if(i>1 && (currentTile.getProperty().equals("10")) && 
						all_tiles.get(i-1).getProperty().equals("01") && 
						!isInList(path, all_tiles.get(i-1)) && (i-1)/4==i/4) {
						
						path.add(all_tiles.get(i-1));
						i-=1;
				}
				else if(i<12 && (currentTile.getProperty().equals("10")) && 
						all_tiles.get(i+4).getProperty().equals("01") && 
						!isInList(path, all_tiles.get(i+4)) && (i+4)%4==i%4) {
						
						path.add(all_tiles.get(i+4));
						i+=4;
				}
				else if(i>1 && (currentTile.getProperty().equals("10")) && 
					all_tiles.get(i-1).getProperty().equals("Horizontal") && 
					!isInList(path, all_tiles.get(i-1)) && (i-1)/4==i/4) {
					path.add(all_tiles.get(i-1));
					i-=1;
				}
				else if(i<12 && (currentTile.getProperty().equals("11")) && 
					all_tiles.get(i+4).getProperty().equals("Vertical") && 
					!isInList(path, all_tiles.get(i+4)) && (i+4)%4==i%4) {
					path.add(all_tiles.get(i+4));
					i+=4;
				}
				else if(i<15 && (currentTile.getProperty().equals("11")) && 
						all_tiles.get(i+1).getProperty().equals("10") && 
						!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
						path.add(all_tiles.get(i+1));
						i+=1;
				}
				else if(i<12 && (currentTile.getProperty().equals("11")) && 
						all_tiles.get(i+4).getProperty().equals("01") && 
						!isInList(path, all_tiles.get(i+4)) && (i+4)%4==i%4) {
						path.add(all_tiles.get(i+4));
						i+=4;
				}
				else if(i<15 && (currentTile.getProperty().equals("11")) && 
						all_tiles.get(i+1).getProperty().equals("00") && 
						!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
						path.add(all_tiles.get(i+1));
						i+=1;
				}
				else if(i<12 && (currentTile.getProperty().equals("11")) && 
						all_tiles.get(i+4).getProperty().equals("00") && 
						!isInList(path, all_tiles.get(i+4)) && (i+4)%4==i%4) {
						path.add(all_tiles.get(i+4));
						i+=4;
				}
				else if(i<15 && (currentTile.getProperty().equals("11")) && 
					all_tiles.get(i+1).getProperty().equals("Horizontal") && 
					!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
					path.add(all_tiles.get(i+1));
					i+=1;
				}
				else if(i>=1 && (currentTile.getProperty().equals("Horizontal")) && 
						all_tiles.get(i-1).getProperty().equals("Horizontal")  && 
						   !isInList(path, all_tiles.get(i-1)) && i /4 == (i-1) /4) {
						path.add(all_tiles.get(i-1));
						i-=1;
				}
				else if(i<15 && (currentTile.getProperty().equals("Horizontal")) && 
						all_tiles.get(i+1).getProperty().equals("Horizontal") && 
						   !isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
						path.add(all_tiles.get(i+1));
						i+=1;
				}
				else if(i<15 && (currentTile.getProperty().equals("Horizontal")) && 
					all_tiles.get(i+1).getProperty().equals("00") && 
					!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
					path.add(all_tiles.get(i+1));
					i+=1;
				}
				else if(i>1 && (currentTile.getProperty().equals("Horizontal")) && 
					all_tiles.get(i-1).getProperty().equals("01") && 
					!isInList(path, all_tiles.get(i-1)) && (i-1)/4==i/4) {
					path.add(all_tiles.get(i-1));
					i-=1;
				}
				else if(i<15 && (currentTile.getProperty().equals("Horizontal")) && 
					all_tiles.get(i+1).getProperty().equals("10") &&
					!isInList(path, all_tiles.get(i+1)) && (i+1)/4==i/4) {
					path.add(all_tiles.get(i+1));
					i+=1;
				}
				else if(i>1 && (currentTile.getProperty().equals("Horizontal")) && 
					all_tiles.get(i-1).getProperty().equals("11") && 
					!isInList(path, all_tiles.get(i-1)) && (i-1)/4==i/4) {
					path.add(all_tiles.get(i-1));
					i-=1;
				}
				else {
					return null;
					
				}
				
					
		}
		
		return path;
	}


	private static boolean isInList(ArrayList<Tile> path, Tile tile) {
		boolean isInArrayList = false;
		for (int i = 0; i < path.size(); i++) {
			if(path.get(i) == tile)
				isInArrayList = true;
		}
		return isInArrayList;
	}

	public static int findStarterIndex(ArrayList<Tile> all_tiles) {
			for(int i = 0; i < all_tiles.size(); i++) {
				if(all_tiles.get(i) instanceof Starter)
					return i;
			}
			return -1;
	}
 
}

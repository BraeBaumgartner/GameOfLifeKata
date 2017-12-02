package GameOfLife;

import java.io.*;
import java.util.*;
public class World {
	protected boolean[][] grid;
	public World(String filename) {
		File file = new File(filename);
		BufferedReader reader = null;
		
		ArrayList<boolean[]> gridBuilder= new ArrayList<>();
		
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line = reader.readLine();
			int worldWidth = line.length();
			boolean valid = true;
			while(line != null && valid) {
				int lineWidth = line.length();
				if(lineWidth != worldWidth) {
					valid = false;
					this.grid = new boolean[0][0];
					break;
				}
				boolean[] worldRow = new boolean[worldWidth];
				for(int i = 0;i<worldWidth;i++) {
					if(line.charAt(i)=='.') {
						worldRow[i] = false;
					} else if(line.charAt(i)=='0') {
						worldRow[i] = true;
					} else {
						valid = false;
						this.grid = new boolean[0][0];
						break;
					}
				}
				gridBuilder.add(worldRow);
				//read next line
			    line = reader.readLine();
			}
			if(valid) {
				this.grid = gridBuilder.toArray(new boolean[gridBuilder.size()][]);
			}
		    
		} catch (FileNotFoundException e) {
			System.out.println("File "+filename+" not found.");
		} catch (IOException e) {
			System.out.println("Error reading "+filename+".");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					System.out.println("File "+filename+" failed to close.");
				}
			}
		}
	}
}

package GameOfLife;

import java.io.*;
import java.util.*;
public class World {
	protected boolean[][] grid;//The world for the game
	private int worldWidth; //The number of columns in the world
	private int worldHeight; //The number of rows in the world
	
	
	public World(String filename) {
		this.grid = null;
		File file = new File(filename);
		BufferedReader reader = null;
		
		ArrayList<boolean[]> gridBuilder= new ArrayList<>();
		
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line = reader.readLine();
			this.worldWidth = line.length();
			/*
			 * valid is a flag that is set to false when either the input file is not a
			 * rectangular grid, or the input file contains characters besides '.' and '0'
			 */
			boolean valid = true;
			while(line != null && valid) {
				int lineWidth = line.length();
				if(lineWidth != this.worldWidth) {
					valid = false;
					System.out.println("File "+filename+" is not a rectangular grid.");
					break;
				}
				boolean[] worldRow = new boolean[this.worldWidth];
				for(int i = 0;i<this.worldWidth;i++) {
					if(line.charAt(i)=='.') {
						worldRow[i] = false;
					} else if(line.charAt(i)=='0') {
						worldRow[i] = true;
					} else {
						valid = false;
						System.out.println("File "+filename+" contains invalid characters.");
						break;
					}
				}
				gridBuilder.add(worldRow);
				//read next line
			    line = reader.readLine();
			}
			if(valid) {
				this.grid = gridBuilder.toArray(new boolean[gridBuilder.size()][]);
				this.worldHeight=this.grid.length;
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
	
	//Return true if the cell at [row][col] is alive, or false if the cell is either dead or outside of the grid
	private boolean isAlive(int row, int col) {
		if(row<0 || row>=this.worldHeight) {
			return false;
		}
		if(col<0 || col>=this.worldWidth) {
			return false;
		}
		return this.grid[row][col];
	}
	
	//Count the number of living neighbors that the cell at [row][col] has
	private int livingCount(int row, int col) {
		int count = 0;
		for (int i = row-1;i<=row+1;i++) {
			for(int j = col-1;j<=col+1;j++) {
				//Do not count the cell this.grid[row][col], as a cell is not its own neighbor
				if(this.isAlive(i,j) && (i!=row || j!=col)) {
					count++;
				}
			}
		}
		return count;
	}
	
	//Convert the grid to a string
	public String toString() {
		StringBuffer s = new StringBuffer();
		for(int row=0;row<this.worldHeight;row++) {
			for(int col=0;col<this.worldWidth;col++) {
				if(this.grid[row][col]) {
					s.append('0');
				} else {
					s.append('.');
				}
			}
			s.append('\n');
		}
		return s.toString();
	}
	
	
	//Update this.grid to the next generation according to the rules of Conway's Game of Life
	public void update() {
		boolean[][] tempGrid = new boolean[worldHeight][worldWidth];
		for(int row=0; row < this.worldHeight;row++) {
			for(int col=0;col<this.worldWidth;col++) {
				if(this.livingCount(row, col) < 2 || this.livingCount(row, col)>3) {
					tempGrid[row][col]=false;
				}else if(this.livingCount(row, col)==3) {
					tempGrid[row][col]=true;
				}else {
					tempGrid[row][col]=this.grid[row][col];
				}
			}
		}
		this.grid = tempGrid;
	}
}









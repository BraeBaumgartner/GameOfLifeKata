package GameOfLife;

import java.io.IOException;
import java.util.Scanner;

public class GameOfLife {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		World world;
		String filename;
		do {
			System.out.print("Please enter the path of a file containing a Game of Life grid: ");
			filename = keyboard.nextLine();
			world = new World(filename);
		}while (world.grid==null);
		
		//Only proceeds once a valid file has been passed
		System.out.println("World contained in file " + filename + ":");
		System.out.println(world.toString());
		System.out.println();
		System.out.println("World will now update. Press enter to exit.");
		try {
			Thread.sleep(1000);
			while (System.in.available() == 0) {
				   world.update();
				   System.out.println(world.toString());
				   System.out.println();
				   Thread.sleep(1000);
				}
		} catch (IOException e) {
			System.out.println("Error processing user input, terminating.");
		} catch (InterruptedException e) {
			System.out.println("Program interrupted, terminating.");
		}
		keyboard.close();
	}
	
}

package GameOfLife;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.Assert;

import GameOfLife.World;

class GameOfLifeTest {
	
	@Test
	void readWorldFromFile() {
		World w = new World("samples/test1");
		boolean[][] result = {{false,false,false,false,false,false,false,false},
		                      {false,false,false,false,false,false,false,true},
		                      {false,false,false,false,false,false,true,false},
		                      {false,false,false,false,false,true,false,false},
		                      {false,false,false,false,true,false,false,false},
		                      {false,false,false,true,false,false,false,false}};
		assertArrayEquals(result,w.grid);
	}

	@Test
	void failOnInvalidChars() {
		World w = new World("samples/invalidCharacter");
		boolean[][] result = new boolean[0][0];
		assertArrayEquals(result,w.grid);
	}
	
	@Test
	void failOnInconsistentRowLength() {
		World w = new World("samples/inconsistentRowLengths");
		boolean[][] result = new boolean[0][0];
		assertArrayEquals(result,w.grid);
	}
}

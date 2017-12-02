package GameOfLife;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import GameOfLife.World;

class GameOfLifeTest {
	
	//Test to ensure that World reads from input file correctly
	@Test
	void readWorldFromFile() {
		World w = new World("samples/test1");
		/*
		 * result matches the following grid:
		 * ........
		 * .......0
		 * ......0.
		 * .....0..
		 * ....0...
		 * ...0....
		 */
		boolean[][] result = {{false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,true},
				{false,false,false,false,false,false,true,false},
				{false,false,false,false,false,true,false,false},
				{false,false,false,false,true,false,false,false},
				{false,false,false,true,false,false,false,false}};
		assertArrayEquals(result,w.grid);
	}

	//Test to ensure that World properly handles invalid characters
	@Test
	void failOnInvalidChars() {
		World w = new World("samples/invalidCharacter");
		boolean[][] result = new boolean[0][0];
		assertArrayEquals(result,w.grid);
	}
	
	//Test to ensure that World does not attempt to build from a jagged array
	@Test
	void failOnInconsistentRowLength() {
		World w = new World("samples/inconsistentRowLengths");
		boolean[][] result = new boolean[0][0];
		assertArrayEquals(result,w.grid);
	}
	
	//Test to ensure that cells with too few living neighbors die
	@Test
	void liveCellWithFewerThanTwoNeighborsDies() {
		World w = new World("samples/tooFewNeighbors");
		w.update();
		/*
		 * result matches the following grid:
		 * ..
		 * ..
		 */
		boolean[][] result = {{false,false},
				{false,false}};
		assertArrayEquals(result,w.grid);
	}

	//Test to ensure that living cells continue living with two or three neighbors
	@Test
	void liveCellWithTwoOrThreeNeighborsLives() {
		World w = new World("samples/enoughNeighbors");
		w.update();
		/*
		 * result matches the following grid:
		 * 00....
		 * 00....
		 * ......
		 * ...00.
		 * ..0..0
		 * ...00.
		 */
		boolean[][] result = {{true,true, false,false,false,false},
				{true,true, false,false,false,false},
				{false,false, false,false,false,false},
				{false,false, false,true,true,false},
				{false,false,true,false,false,true},
				{false,false, false,true,true,false}};
		assertArrayEquals(result,w.grid);
	}

	//Test to ensure that cells with too many neighbors die
	@Test
	void liveCellWithFourOrMoreNeighborsDies() {
		World w = new World("samples/tooManyNeighbors");
		w.update();
		boolean[][] result = {{true,false,true},
				{false,false,false},
				{true,false,true}};
		assertArrayEquals(result,w.grid);
	}
	
	//Test to ensure that dead cells with exactly three neighbors become live cells
	@Test
	void deadCellWithThreeNeighborsLives() {
		World w = new World("samples/createLivingCell");
		w.update();
		boolean[][] result = {{true,true,false},
				{true,true,false},
				{false,false,false}};
		assertArrayEquals(result,w.grid);
	}
}

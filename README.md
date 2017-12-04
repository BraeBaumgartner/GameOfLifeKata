# GameOfLifeKata
Implementation of Conway's Game of Life for coding evaluation.

## Rules of the Game

The board (or playing field) for the Game of Life is a two dimensional grid of cells. Each cell is considered to be either "alive" or "dead". The next generation of the grid is calculated using these rules:
1. Any live cell with fewer than two live neighbors dies, as if caused by under population.
2. Any live cell with more than three live neighbors dies, as if by overcrowding.
3. Any live cell with two or three live neighbors lives on to the next generation.
4. Any dead cell with exactly three live neighbors becomes a live cell.
5. A cell's neighbors are those cells which are horizontally, vertically or diagonally adjacent. Most cells will have eight neighbors. Cells placed on the edge of the grid will have fewer.

More information on Conway's Game of Life can be found at https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

## Running the Program

This program is written in Java.  If you do not already have the latest version of Java installed on you machine, instructions for installing the Java Runtime Environment can be found at https://www.java.com/en/

Note: due to limitations in available hardware, I have only been able to test this program with Windows 7 and Java 8.

Jar files for both the test cases and the main program are included in this repository, so it should not be necessary to build the programs before running them.

GameOfLife can be run from a command prompt by running

```
$java -jar GameOfLife.jar
```
in the directory containing the .jar file.

The program will prompt the user to enter a text file containing a valid Game of Life world.  Several example files are included in the ```samples``` directory.  Once a valid file has been passed, the program will repeatedly update the world to the next generation, and print each new generation to the terminal.  The user can end the program by pressing enter.

#### "Valid Game of Life World"
A valid game of life world is a text file where each line contains the same number of characters, forming a rectangular grid.  The only valid characters are '0' (zero), representing a living cell, and '.' (period), representing a dead cell.

### Running the Test Cases
The test cases can be run by executing the following command in a command prompt:
```
$java -jar GameOfLifeTest.jar
```
The program will output the results of running the test file to the terminal

## Running from Source

Jar files for both the test cases and main program are included in this repository, so building them from source should not be required. However, instructions for running this project from source are still included below.

Both .jar files were built in the Eclipse IDE, which can be downloaded from https://www.eclipse.org/.  

### Importing the project into Eclipse
With Eclipse installed and open, import the project into eclipse by selecting ```File>Import>General>Existing Projects into Workspace```
Then press Next at the bottom, and set the GameOfLifeKata folder as the root for the project.  Finally, click Finish to import the project into Eclipse.

### Running the main executable
Now that the project has been imported, select ```src>GameOfLife>GameOfLife.java``` and click the green Run arrow to run the program within Eclipse

### Running the unit test file
Now that the project has been imported, select ```Test>GameOfLife>GameOfLifeTest.java``` and click the green Run arrow to run the test program within Eclipse


## Implemented Features

GameOfLife supports worlds with arbitrary numbers of rows and columns.  All five of the rules described in "Rules of the Game" have been implemented.  After outputting the next generation according to these rules, the program will use the output to produce subsequent generations.  Users can end this loop and terminate the program by pressing enter.

The program prompts users to enter the name of a text file containing a Game of Life world, and allows users to try again with another filename if the provided name does not lead to a valid world.

## Design Choices

The world was implemented as a two-dimensional array of booleans, with true representing a living cell and false representing a dead cell.  This decision made iterating over each cell in the world much easier, and made checking whether or not a cell was alive easier as well.

The update() method only attempts to update each cell once, achieving a run time that scales linearly with the size of the world.

Having the user pass in a text file, rather than directly entering the grid when prompted, makes using the program much easier since the user will not need to worry about having to reenter an entire grid by hand as a result of a typo.

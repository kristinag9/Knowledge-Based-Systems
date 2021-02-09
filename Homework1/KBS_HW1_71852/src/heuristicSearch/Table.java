package heuristicSearch;
// Before we start implementing the task we must note that the task NOT ALWAYS HAS A SOLUTION!
// This class represents the whole board.
// The board is made of tiles with numbers from 0 to 8.
// Each tile can be moved in one of four directions - up, down, right or left,
// but only the empty space can be moved in all four.

import java.util.Arrays;
import java.util.Random;

public class Table {
    private static final int SIZE_OF_BOARD = 9;  // I prefer to make a constant for the size of the board which is 9 (0 - 8)
    private int[] tiles;                         // tiles is an array made up of the numbers in the board
    private int indexOfEmptyTile;                // index of the empty tile

    // create constructors for the class
    public Table() {
        this(null, 0); // the arrays by default is empty and the index is zero
    }

    public Table(int[] tile, int indexOfEmptyTile) {
        setIndex(indexOfEmptyTile);
        setTiles(tile);
    }

    public Table(Table table) {
        this(table.tiles, table.indexOfEmptyTile);
    }

    // create getters and setters for the private data members
    public int[] getTiles() {
        return tiles;
    }

    // set method to change the value of the tile
    public void setTiles(int[] tiles) {
        Random rand = new Random();
        this.tiles = new int[SIZE_OF_BOARD];

        if (tiles == null) {

            do {
                for (int i = 0; i < SIZE_OF_BOARD; i++) {
                    boolean duplicate = true;            // flag which is true if there is a duplicate element in the board
                    int temp;                 // generate a random start position of the tiles
                    do {
                        temp = rand.nextInt(9);
                        duplicate = false;
                        for (int j = 0; j < i; j++) {
                            if (this.tiles[j] == temp) { // if there are duplicates we just break
                                duplicate = true;
                                break;
                            }
                        }
                    } while (duplicate);
                    this.tiles[i] = temp;

                    if (this.tiles[i] == 0) {
                        setIndex(i);
                    }
                }
            } while (!this.isSolvable());


        } else {
            for (int i = 0; i < SIZE_OF_BOARD; i++) {
                this.tiles[i] = tiles[i];

                if (this.tiles[i] == 0) {
                    indexOfEmptyTile = i;
                }
            }
        }
    }

    public int getIndex() {
        return indexOfEmptyTile;
    }

    public void setIndex(int indexOfEmptyTile) {
        this.indexOfEmptyTile = indexOfEmptyTile;
    }

    // method which returns true if the goal is found
    public boolean isGoal() {
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            // if we find an element which is not at the right pos
            if (tiles[i] != i) {
                return false;
            }
        }
        return true;
    }

    // method which returns true if there is a solution to the task
    public boolean isSolvable() {
        int inversions = 0; // the number of swap between digits where the second digit is smaller than the first one

        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            for (int j = i + 1; j < SIZE_OF_BOARD; j++) {
                if (tiles[j] > tiles[i] && tiles[i] > 0) {
                    inversions += 1;
                }
            }
        }

        return inversions % 2 == 0; // if the inversions are even, there is a solution
    }

    // Create methods for moving the tiles - up, down, right, left

    // method for moving the tile up
    public Table slideUp() {
        // it cannot be moved up at positions 0, 1 and 2
        if (indexOfEmptyTile == 0 || indexOfEmptyTile == 1 || indexOfEmptyTile == 2) {
            return null;
        }

        Table newTable = new Table(this);
        // move up the empty tile with -3 positions
        newTable.tiles[newTable.indexOfEmptyTile] = newTable.tiles[newTable.indexOfEmptyTile - 3];
        newTable.tiles[newTable.indexOfEmptyTile - 3] = 0;
        newTable.indexOfEmptyTile -= 3;
        return newTable;
    }

    // method for moving the empty tile down
    public Table slideDown() {
        // it cannot be moved up at positions 6, 7 and 8
        if (indexOfEmptyTile == 6 || indexOfEmptyTile == 7 || indexOfEmptyTile == 8) {
            return null;
        }

        Table newTable = new Table(this);
        // move down the empty tile with +3 positions
        newTable.tiles[newTable.indexOfEmptyTile] = newTable.tiles[newTable.indexOfEmptyTile + 3];
        newTable.tiles[newTable.indexOfEmptyTile + 3] = 0;
        newTable.indexOfEmptyTile += 3;
        return newTable;
    }

    // method for moving the tile on left
    public Table slideLeft() {
        // it cannot be moved up at positions 0, 3 and 6
        if (indexOfEmptyTile == 0 || indexOfEmptyTile == 3 || indexOfEmptyTile == 6) {
            return null;
        }

        Table newTable = new Table(this);
        // move left the empty tile with -1 position
        newTable.tiles[newTable.indexOfEmptyTile] = newTable.tiles[newTable.indexOfEmptyTile - 1];
        newTable.tiles[newTable.indexOfEmptyTile - 1] = 0;
        newTable.indexOfEmptyTile -= 1;

        return newTable;
    }

    // method for moving the tile on right
    public Table slideRight() {
        // it cannot be moved up at positions 2, 5 and 8
        if (indexOfEmptyTile == 2 || indexOfEmptyTile == 5 || indexOfEmptyTile == 8) {
            return null;
        }

        Table newTable = new Table(this);
        // move right the empty tile with +1 position
        newTable.tiles[newTable.indexOfEmptyTile] = newTable.tiles[newTable.indexOfEmptyTile + 1];
        newTable.tiles[newTable.indexOfEmptyTile + 1] = 0;
        newTable.indexOfEmptyTile += 1; // 6
        return newTable;
    }

    // method for displaying the tiles and their indexes
    @Override
    public String toString() {
        return "Board {" + "Tiles = " + Arrays.toString(tiles) + ", Index of zero = " + indexOfEmptyTile + '}';
    }

} // end of class

package heuristicSearch;
// class for testing the methods in class Board

public class TestTable {
    public static void main(String[] args) {
        // Create a board
        Table myTable = new Table();

        // Set values to the board - the ones from the homework BUT
        // the position of the zero is different every time because we generate it randomly in the set method
        myTable.setTiles(new int[]{8,0,6,5,4,7,2,3,1});
        System.out.println(myTable);        // display the result

        // Check if the empty tile of the board can be moved up and the print the board
        Table moveUpTile = myTable.slideUp();
        if(moveUpTile != null) {
            System.out.println(myTable);
        } else {
            System.out.println("The tile cannot be moved up!");
        }

        // Check if the empty tile of the board can be moved down and the print the board
        Table moveDownTile = myTable.slideDown();
        if(moveUpTile != null) {
            System.out.println(myTable);
        } else {
            System.out.println("The tile cannot be moved down!");
        }

        // Check if the empty tile of the board can be moved left and the print the board
        Table moveLeftTile = myTable.slideLeft();
        if(moveLeftTile != null) {
            System.out.println(myTable);
        } else {
            System.out.println("The tile cannot be moved left!");
        }

        // Check if the empty tile of the board can be moved right and the print the board
        Table moveRightTile = myTable.slideRight();
        if(moveRightTile != null) {
            System.out.println(myTable);
        } else {
            System.out.println("The tile cannot be moved right!");
        }
    }
}

package heuristicSearch;
// class TestState for testing the methods of class TestState

public class TestState {
    public static void main(String[] args) {
        // Create a board
        Table myTable = new Table();

        // Set values to the board - the ones from the homework BUT
        // the position of the zero is different every time because we generate it randomly in the set method
        myTable.setTiles(new int[]{8,0,6,5,4,7,2,3,1});

        // create a state made of myTable and default values for the other arguments
        State myState = new State(myTable, null, "Start", 0, 0);
        System.out.println(myState);
    }
}

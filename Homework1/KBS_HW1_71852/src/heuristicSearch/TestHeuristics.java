package heuristicSearch;
// This class is for testing the result of the heuristic functions

public class TestHeuristics {
    public static void main(String[] args) {
        Table myTable = new Table();
        myTable.setTiles(new int[]{8,0,6,5,4,7,2,3,1});
        System.out.printf("My table with randomly generated position of the empty tile: %s\n", myTable);
        State myState = new State(myTable, null, "Start", 0, 0);

        int manhattanDistance = HeuristicFunctions.manhattanDistance(myState.getTable());
        int orderedBlocks = HeuristicFunctions.numberOfUnorderedBlocks(myState.getTable());

        System.out.printf("Manhattan distance: %d\n", manhattanDistance);
        System.out.printf("Number of unordered tiles: %d\n", orderedBlocks);
    }
}

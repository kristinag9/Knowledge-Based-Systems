package heuristicSearch;

public class TestMethods {

    public static void main(String[] args) {
        Table myTable = new Table(null, 0); // worst case -> [8, 6, 7, 2, 5, 4, 3, 0, 1]
        State myState = new State(myTable, null, "Start", 0, 0);
        System.out.printf("My state with randomly generated position of the empty tile: %s\n", myState);

        //---------------------------------------- Testing AStar Search -----------------------------------------
        System.out.println("Testing A* algorithm");
        // 1) With Manhattan distance
        State aStarResult = MethodsForSearching.aStarMethod(myState, HeuristicFunctions::manhattanDistance);
        if (aStarResult != null) {
            System.out.printf("Result for AStar method with Manhattan distance: %s\n", aStarResult);
        }

        // 2) With number of unordered blocks
        aStarResult = MethodsForSearching.aStarMethod(myState, HeuristicFunctions::numberOfUnorderedBlocks);
        if (aStarResult != null) {
            System.out.printf("Result for AStar method with number of unordered blocks: %s\n", aStarResult);
        }

        //---------------------------------------- Testing Best-First Search -----------------------------------------
        System.out.println("Testing Best-First Search algorithm");
        // 1) With Manhattan distance
        State bfsResult = MethodsForSearching.bestFirstSearch(myState, HeuristicFunctions::manhattanDistance);
        if (bfsResult != null) {
            System.out.printf("Result for BFS with Manhattan distance: %s\n", bfsResult);
        }

        // 2) With number of unordered blocks
        bfsResult = MethodsForSearching.bestFirstSearch(myState, HeuristicFunctions::numberOfUnorderedBlocks);
        if (bfsResult != null) {
            System.out.printf("Result for BFS with number of unordered blocks: %s\n", bfsResult);
        }
    }
}

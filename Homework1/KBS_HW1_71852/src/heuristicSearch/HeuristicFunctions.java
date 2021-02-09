package heuristicSearch;
// class HeuristicFunctions contains two functions - manhattanDistance and numberOfOrderedBlocks

public class HeuristicFunctions {
    // This function represents the first heuristic function from the homework - the manhattan distance
    public static int manhattanDistance(Table table) {
        int[] temp = table.getTiles();
        int resultForMD = 0; // this variable saves the result of the calculation
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] != 0 && temp[i] != i) { // temp[i] != right position
                resultForMD += Math.abs(temp[i]  % 3 - i % 3) + Math.abs(temp[i] / 3 - i / 3);
            }
        }
        return resultForMD;
    }

    // This function represents the second given heuristic function from the homework -
    // the number of tiles which are not at the right position
    public static int numberOfUnorderedBlocks(Table table) {
        int[] temp = table.getTiles();
        int resultForNOB = 0; // it saves the result of the calculation
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] != 0 && temp[i] != i) { // temp[i] != right position
                resultForNOB += 1;
            }
        }
        return resultForNOB;
    }
} // end of class HeuristicFunctions

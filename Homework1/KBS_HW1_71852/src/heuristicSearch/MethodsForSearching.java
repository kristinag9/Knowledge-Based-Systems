package heuristicSearch;

import java.util.PriorityQueue;
import java.util.function.Function;

public class MethodsForSearching {

    public static State bestFirstSearch(State origState, Function<Table, Integer> hFunc){
        PriorityQueue<State> frontier = new PriorityQueue<>();      // frontier with the current nodes which are sorted
        frontier.add(origState);

        // while there are nodes in teh frontier
        while(!frontier.isEmpty()) {

            // a check if the number of element used in the algorithm is more than 1_000_000 => run out of memory
            if(frontier.size() >= 1_000_000) {
                System.out.println("The memory is running out...");
                return null;
            }

            State curState = frontier.poll(); // take the first most appropriate state
            if(curState.isGoal()) {
                System.out.println("Goal is found!");
                return curState;
            }

            Table curBoard = curState.getTable();
            // generate acyclic nodes
            String stateAction = curState.getAction();

            // these boards are used for moving in different directions
            Table fstBoard = null;   // up
            Table sndBoard = null;   // left
            Table thirdBoard = null; // right
            Table forthBoard = null; // down

            switch (stateAction) {
                // if the board is moved up, there is no sense to move it down again
                case "up": {
                    fstBoard = curBoard.slideUp();
                    sndBoard = curBoard.slideLeft();
                    thirdBoard = curBoard.slideRight();
                    break;
                }
                // if the board is moved down, there is no sense to move it up again
                case "down": {
                    forthBoard = curBoard.slideDown();
                    sndBoard = curBoard.slideLeft();
                    thirdBoard = curBoard.slideRight();
                    break;
                }
                // if the board is moved left, there is no sense to move it right again
                case "left": {
                    fstBoard = curBoard.slideUp();
                    forthBoard = curBoard.slideDown();
                    sndBoard = curBoard.slideLeft();
                    break;
                }
                // if the board is moved right, there is no sense to move it left again
                case "right": {
                    fstBoard = curBoard.slideUp();
                    forthBoard = curBoard.slideDown();
                    thirdBoard = curBoard.slideRight();
                    break;
                }
                default: {
                    // if it is the first state, we can move the board in each direction
                    fstBoard = curBoard.slideUp();
                    sndBoard = curBoard.slideLeft();
                    thirdBoard = curBoard.slideRight();
                    forthBoard = curBoard.slideDown();
                }
            } // end switch

            if(fstBoard != null) {
                frontier.add(new State(fstBoard, curState, "up", 0,
                        hFunc.apply(fstBoard)));
            }
            if(sndBoard != null) {
                frontier.add(new State(sndBoard, curState, "left", 0,
                            hFunc.apply(sndBoard)));
            }
            if(thirdBoard != null) {
                frontier.add(new State(thirdBoard, curState,"right", 0,
                        hFunc.apply(thirdBoard)));
            }
            if(forthBoard != null) {
                frontier.add(new State(forthBoard, curState, "down", 0,
                        hFunc.apply(forthBoard)));
            }
        }

        // if there aren't any nodes in the frontier, the queue is empty and return an error
        System.out.println("The queue is empty! Cannot continue!");
        return null;
    }

    // implement the AStar method for searching - it is almost the same as the BFS but the pathCost is different
    public static State aStarMethod(State origState, Function<Table, Integer> hFunc){
        PriorityQueue<State> frontier = new PriorityQueue<>();      // frontier with the current nodes which are sorted
        frontier.add(origState);

        while(!frontier.isEmpty()) {

            State curState = frontier.poll();

            if(curState.isGoal()) {
                System.out.println("Goal is found!\n");
                return curState;
            }

            Table curBoard = curState.getTable();
            String stateAction = curState.getAction();

            Table fstBoard = null; // up
            Table sndBoard = null; // left
            Table thirdBoard = null; // right
            Table forthBoard = null; // down

            switch (stateAction) {
                // if the board is moved up, there is no sense to move it down again
                case "up": {
                    fstBoard = curBoard.slideUp();
                    sndBoard = curBoard.slideLeft();
                    thirdBoard = curBoard.slideRight();
                    break;
                }
                // if the board is moved down, there is no sense to move it up again
                case "down": {
                    forthBoard = curBoard.slideDown();
                    sndBoard = curBoard.slideLeft();
                    thirdBoard = curBoard.slideRight();
                    break;
                }
                // if the board is moved left, there is no sense to move it right again
                case "left": {
                    fstBoard = curBoard.slideUp();
                    forthBoard = curBoard.slideDown();
                    sndBoard = curBoard.slideLeft();
                    break;
                }
                // if the board is moved right, there is no sense to move it left again
                case "right": {
                    fstBoard = curBoard.slideUp();
                    forthBoard = curBoard.slideDown();
                    thirdBoard = curBoard.slideRight();
                    break;
                }
                default: {
                    // if it is the first state, we can move the board in each direction
                    fstBoard = curBoard.slideUp();
                    sndBoard = curBoard.slideLeft();
                    thirdBoard = curBoard.slideRight();
                    forthBoard = curBoard.slideDown();
                }
            }

            if(fstBoard != null) {
                frontier.add(new State(fstBoard, curState, "up", curState.getPathCost() + 1,
                        hFunc.apply(fstBoard)));
            }
            if(sndBoard != null) {
                frontier.add(new State(sndBoard, curState, "left", curState.getPathCost() + 1,
                        hFunc.apply(sndBoard)));
            }
            if(thirdBoard != null) {
                frontier.add(new State(thirdBoard, curState,"right", curState.getPathCost() + 1,
                        hFunc.apply(thirdBoard)));
            }
            if(forthBoard != null) {
                frontier.add(new State(forthBoard, curState, "down", curState.getPathCost() + 1,
                        hFunc.apply(forthBoard)));
            }
        }

        System.out.println("The queue is empty! Cannot continue!");
        return null;
    }
} // end of class MethodsForSearching

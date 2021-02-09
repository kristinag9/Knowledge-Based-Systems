package heuristicSearch;
// class State represents the representation of the nodes in informed search algorithms
// which includes 5 components: state of the space; parent; action; path-cost and heuristic

public class State implements Comparable<State> {
    private Table table;         // state of the space
    private State parentOfState; // parent (the previous state of the table)
    private String action;       // action
    private int pathCost;        // path-cost - the cost from the beginning till the current state - g(n)
    private int heuristic;       // heuristic

    // Create constructor
    public State() {
        this(null, null, null, 0, 0);
    }

    public State(Table table, State parentOfState, String action, int pathCost, int heuristic) {
        this.table = table;
        this.parentOfState = parentOfState;
        this.action = action;
        this.pathCost = pathCost;
        this.heuristic = heuristic;
    }

    public State(State state) {
        this(state.getTable(), state.parentOfState, state.action, state.pathCost, state.heuristic);
    }

    // Create getters and setters for the private data members that I need for testing the resul
    public Table getTable() {
        return table;
    }

    public State getParentOfState() {
        return parentOfState;
    }

    public String getAction() {
        return action;
    }

    public int getPathCost() {
        return pathCost;
    }

    public boolean isGoal() {
        return table.isGoal();
    }

    // method which compares two states and returns the one with minimum path-cost
    // the method returns -1 if the first state is with cheaper path-cost than the second one;
    // 0 - if the states are equal; and 1 - of the second one is cheaper
    @Override
    public int compareTo(State state) {
        if (pathCost + heuristic < state.pathCost + state.heuristic) {
            return -1;
        }

        return 1;
    }

    // method toString for displaying the information of a state
    @Override
    public String toString() {
        return "State {" + "Table = " + table + ", parentOfState = \n" + parentOfState + ", action = '" + action + '\'' +
                ", pathCost = " + pathCost + ", heuristic = " + heuristic + '}' + '\n';
    }
} // end of class State

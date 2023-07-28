package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**DFS algorithm work with LinkedHashSet and Queue. DFS algorithm starts with the initial state and explores its neighbors,
 *  adding them to a stack. It then continues to explore each neighbor's neighbors in turn until it reaches the goal
 *  state or exhausts all possible paths. The algorithm uses a HashSet to keep track of visited states and avoid
 *  revisiting them. When a goal state is found, it traces back through each state's "former" field to construct
 *  the solution path.*/

public class DepthFirstSearch extends ASearchingAlgorithm {

    public DepthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable searchable) {

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        HashSet<String> visited = new HashSet<>();
        Stack<AState> stateStack = new Stack<>();
        stateStack.push(startState);
        visited.add(startState.toString());

        while (!stateStack.isEmpty()) {
            AState currentState = stateStack.pop();
            if (currentState.equals(goalState)) {
                backward(currentState, sol);
                return sol;
            }

            List<AState> neighbors = searchable.getAllPossibleStates(currentState);
            numberOfNodesEvaluated++;
            for (AState neighbor : neighbors) {
                if (!visited.contains(neighbor.toString())) {
                    neighbor.setFormer(currentState);
                    visited.add(neighbor.toString());
                    stateStack.push(neighbor);
                }
            }
        }
        return null;
    }


    @Override
    public String getName() {
        return "Depth First Search";
    }


}


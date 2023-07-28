package algorithms.search;

import java.util.Stack;

/**Abstract class that implements the interface ISearchingAlgorithm
 * The class is extended in every searching algorithm and contain override methods that common to all the searching
 * algorithm such as:
 * search - function that find the solution of the given maze and return it
 * getNumberOfNodesEvaluated - function that return the number of the nodes that evaluated in that searching for the path
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected Solution sol;
    protected int numberOfNodesEvaluated;

    //constructor
    public ASearchingAlgorithm() {
        this.sol = new Solution();
        this.numberOfNodesEvaluated = 0;
    }


    @Override
    public Solution solve(ISearchable s) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    /**function that get back in the end of the algorithm and return the solution of the path and the steps*/
    public void backward(AState s, Solution sol) {
        Stack<AState> stackSol=new Stack<>();
        AState curr=s;
        if(s != null && s.getFormer() != null) {
            stackSol.push(curr);
            while (curr.getFormer() != null){
                curr=curr.getFormer();
                stackSol.push(curr);
            }

            while (!stackSol.empty()) {
                sol.AddState(stackSol.pop());
            }
        }
    }
}

package algorithms.search;

public interface ISearchingAlgorithm {

    Solution solve(ISearchable searchable);
    int getNumberOfNodesEvaluated();
    String getName();
}

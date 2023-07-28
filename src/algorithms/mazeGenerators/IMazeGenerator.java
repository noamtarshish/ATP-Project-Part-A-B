package algorithms.mazeGenerators;

/**Interface for the functions that common in all types of maze: empty, simple and myMaze*/
public interface IMazeGenerator {
    Maze generate(int rows, int columns);
    long measureAlgorithmTimeMillis(int rows, int columns);

}

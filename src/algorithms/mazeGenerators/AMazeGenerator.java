package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    protected Maze maze;
    /**function that generate maze*/
    @Override
    public abstract Maze generate(int rows, int columns);

    /**function that measure the time of the algorithm*/
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {

        long beforeTime = System.currentTimeMillis();
        generate(rows,columns);
        long afterTime = System.currentTimeMillis();

        return afterTime-beforeTime;
    }



}

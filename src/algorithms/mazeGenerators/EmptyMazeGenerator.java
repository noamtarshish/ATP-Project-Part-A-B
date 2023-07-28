package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    /**override to the generate function that generate an empty maze*/
    @Override
    public Maze generate(int rows, int columns) {

        if (rows > 1 && columns > 1)
            return new Maze(rows,columns);
        return null;
    }
}

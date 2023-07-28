package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    /**
     * Function that create simple maze with random. the function create another maze each time.
     * The function start with fill the maze with random walls and paths (until line 77).
     * after it, the funciton trying to find sequences paths in the maze, that can be solution to the maze
     * @param rows - the number of rows in the maze.
     * @param columns - the number of columns in the maze.
     * @return maze.
     */    @Override
    public Maze generate(int rows,int columns) {//int columns, int rows
        Maze maze = new Maze(rows,columns); //create maze
        Random rand = new Random(); //create random variable
        for (int i = 0; i < rows; i++) { //run on the maze to fill paths and wall in random order
            for (int j = 0; j < columns; j++) {
                if((i != maze.getStartPosition().getColumnIndex() || j > maze.getGoalPosition().getRowIndex()) &&
                        (i != maze.getGoalPosition().getRowIndex() || j < maze.getStartPosition().getColumnIndex()) && maze.inBound(new Position(i,j))){

                    if (rand.nextInt(2)==0)
                        maze.path(new Position(i,j));
                    else
                        maze.wall(new Position(i,j));
                }
            }
        }
        //two loops to create path with possible solution
        for(int col = maze.getStartPosition().getColumnIndex(); col<columns; col++){
            if (maze.inBound(new Position(0,col))) {
                maze.path(new Position(0,col));
            }
        }
        for(int row = 0; row< maze.getGoalPosition().getRowIndex(); row++){
            if (maze.inBound(new Position(row,columns-1))) {
                maze.path(new Position(row,columns-1));
            }
        }
        return maze;
    }
}


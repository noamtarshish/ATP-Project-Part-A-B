package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**Class that implement the interface ISearchable and return data about the states in the maze, there are 3 main functions in this class:
 * GetStartState, GetGoalState and getAllPossibleStates(important function that return all the possible states to move to from
 * the current state in the maze, the function also take care about the cost of every step to another state in the maze)*/

public class SearchableMaze implements ISearchable{
    private Maze maze;
    private MazeState startState;
    private MazeState endState;

    public SearchableMaze(Maze maze) {
        this.maze=maze;
        this.startState=new MazeState(null, maze.getStartPosition(),0);
        this.endState=new MazeState(null, maze.getGoalPosition(),0);
    }

    public Maze getMaze() {
        return maze;
    }

    @Override
    public AState getGoalState() {
        return this.endState;
    }

    @Override
    public AState getStartState() {
        return this.startState;
    }

    @Override

    /**Function that find all the possible states of the solution of the maze.
     * This function take care about straight steps in the maze either diagonal steps and their different cost*/
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> allPossibleStates=new ArrayList<>();
        if (s==null)
            return allPossibleStates;
        Position currentPosition=(Position) s.getPosition();
        Position up=new Position(currentPosition.getRowIndex()-1, currentPosition.getColumnIndex());
        Position down=new Position(currentPosition.getRowIndex()+1,currentPosition.getColumnIndex());
        Position right=new Position(currentPosition.getRowIndex(),currentPosition.getColumnIndex()+1);
        Position left=new Position(currentPosition.getRowIndex(),currentPosition.getColumnIndex()-1);
        Position upRight=new Position(currentPosition.getRowIndex()-1,currentPosition.getColumnIndex()+1);
        Position downRight=new Position(currentPosition.getRowIndex()+1,currentPosition.getColumnIndex()+1);
        Position upLeft=new Position(currentPosition.getRowIndex()-1,currentPosition.getColumnIndex()-1);
        Position downLeft=new Position(currentPosition.getRowIndex()+1,currentPosition.getColumnIndex()-1);

        //straight steps

        //up
        if (maze.inBound(up) && maze.getValue(up)==0)
            allPossibleStates.add(new MazeState(s,up, s.getCost()+10));
        //down
        if (maze.inBound(down) && maze.getValue(down)==0)
            allPossibleStates.add(new MazeState(s,down, s.getCost()+10));
        //left
        if (maze.inBound(left) && maze.getValue(left)==0)
            allPossibleStates.add(new MazeState(s,left, s.getCost()+10));
        //right
        if (maze.inBound(right) && maze.getValue(right)==0)
            allPossibleStates.add(new MazeState(s,right, s.getCost()+10));

        //diagonal steps

        //upRight
        if (maze.inBound(upRight) && maze.inBound(up) && maze.inBound(right) && maze.getValue(upRight)==0 && (maze.getValue(up)==0 || maze.getValue(right)==0))
            allPossibleStates.add(new MazeState(s, upRight, s.getCost()+15));
        //upLeft
        if (maze.inBound(upLeft) && maze.inBound(up) && maze.inBound(left) && maze.getValue(upLeft)==0 && (maze.getValue(up)==0 || maze.getValue(left)==0))
            allPossibleStates.add(new MazeState(s, upLeft, s.getCost()+15));
        //downRight
        if (maze.inBound(downRight) && maze.inBound(down) && maze.inBound(right) && maze.getValue(downRight)==0 && (maze.getValue(down)==0 || maze.getValue(right)==0))
            allPossibleStates.add(new MazeState(s, downRight, s.getCost()+15));
        //downLeft
        if (maze.inBound(downLeft) && maze.inBound(down) && maze.inBound(left) && maze.getValue(downLeft)==0 && (maze.getValue(down)==0 || maze.getValue(left)==0))
            allPossibleStates.add(new MazeState(s, upRight, s.getCost()+15));

        return allPossibleStates;
    }
}

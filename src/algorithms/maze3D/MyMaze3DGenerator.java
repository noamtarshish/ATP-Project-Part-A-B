package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**Function that create maze to work with specific algorithm and use it in all the project.
 * We chose to work with dfs iterative that work similarity to the implementation that we used in MyMazeGenerator,
 * but with some difference to adjust it the 3DMaze*/
public class MyMaze3DGenerator extends AMaze3DGenerator {

    @Override
    public Maze3D generate(int depth, int rows, int columns) {
        Maze3D maze = new Maze3D(depth, rows, columns);

        Position3D start = new Position3D(0, 0, 0);
        Random rand=new Random();
        int rowEndPOs =rows-2;
        while (rowEndPOs ==rows-2){
            rowEndPOs =rand.nextInt(rows);
        }
        Position3D end = new Position3D(depth - 1, rowEndPOs, columns - 1);

        maze.fillMazeWithWalls();
        maze.setStartPos(start);
        maze.setGoalPos(end);
        maze.path(end);

        Stack<Position3D> stack = new Stack<>();
        ArrayList<Position3D> neighbors;

        Random random = new Random();
        Position3D current = new Position3D(0,0,0);
        Position3D next = new Position3D(0,0,0);
        int posNum;

        stack.push(start);
        while (!stack.empty()) {
            current = stack.pop();
            maze.path(current);
            neighbors = findNeighbors(maze, current);
            if (neighbors.contains(end))
                break;
            if (neighbors.size() != 0) {
                stack.push(current);
                posNum = random.nextInt(neighbors.size());
                next = neighbors.get(posNum);
                moveInMaze(maze, current,next);
                stack.push(next);
            }

        }

        for (int i = 0; i < ((int) Math.sqrt(depth * rows * columns)); i++) {
            int randDepth = random.nextInt(depth);
            int randRow = random.nextInt(rows);
            int randColumn = random.nextInt(columns);
            if (maze.getValue(new Position3D(randDepth, randRow, randColumn))==1) {
                maze.getMaze()[randDepth][randRow][randColumn] = random.nextInt(2);
            }
        }

        // ensure that the neighbors of the end position is path
        int endDepth = end.getDepthIndex(), endRow = end.getRowIndex(), endColumn = end.getColumnIndex();
        Position3D pos = new Position3D(endDepth - 1, endRow, endColumn);
        if (isValidPosition(maze, pos)) {
            if (maze.getValue(pos) == 1) {
                maze.path(pos);
            }
        }
        pos = new Position3D(endDepth, endRow - 1, endColumn);
        if (isValidPosition(maze, pos)) {
            if (maze.getValue(pos) == 1) {
                maze.path(pos);
            }
        }
        pos = new Position3D(endDepth, endRow, endColumn - 1);
        if (isValidPosition(maze, pos)) {
            if (maze.getValue(pos) == 1) {
                maze.path(pos);
            }
        }

        return maze;
    }


    /**Function that find all the neighbors of the current cell in the maze in the 6 sides, the function return it in ArrayList that
     * represent the neighbors of the cell*/
    private ArrayList<Position3D> findNeighbors(Maze3D maze, Position3D current) {
        ArrayList<Position3D> neighbors = new ArrayList<>();

        // Check right neighbor
        Position3D right = new Position3D(current.getDepthIndex(), current.getRowIndex(), current.getColumnIndex() + 2);
        if (isValidPosition(maze, right) && maze.getValue(right)==1) {
            neighbors.add(right);
        }

        // Check left neighbor
        Position3D left = new Position3D(current.getDepthIndex(), current.getRowIndex(), current.getColumnIndex() - 2);
        if (isValidPosition(maze, left) && maze.getValue(left)==1) {
            neighbors.add(left);
        }

        // Check top neighbor
        Position3D top = new Position3D(current.getDepthIndex(), current.getRowIndex() - 2, current.getColumnIndex());
        if (isValidPosition(maze, top) && maze.getValue(top)==1) {
            neighbors.add(top);
        }

        // Check bottom neighbor
        Position3D bottom = new Position3D(current.getDepthIndex(), current.getRowIndex() + 2, current.getColumnIndex());
        if (isValidPosition(maze, bottom) && maze.getValue(bottom)==1) {
            neighbors.add(bottom);
        }

        // Check up neighbor
        Position3D up = new Position3D(current.getDepthIndex() - 2, current.getRowIndex(), current.getColumnIndex());
        if (isValidPosition(maze, up) && maze.getValue(up)==1) {
            neighbors.add(up);
        }

        // Check down neighbor
        Position3D down = new Position3D(current.getDepthIndex() + 2, current.getRowIndex(), current.getColumnIndex());
        if (isValidPosition(maze, down) && maze.getValue(down)==1) {
            neighbors.add(down);
        }

        return neighbors;
    }

    /**Function that check if a given Position is a valid position in the maze*/
    private boolean isValidPosition(Maze3D maze, Position3D position) {
        int depth = position.getDepthIndex();
        int row = position.getRowIndex();
        int col = position.getColumnIndex();
        return depth >= 0 && depth < maze.getMaze().length && row >= 0 && row < maze.getMaze()[0].length && col >= 0 && col < maze.getMaze()[0][0].length;
    }

    /**Function that determine where to advance in the maze according to current and next possible position,
     * there are 6 options by the 6 sides to advance in the maze: right,left,up,down,top,bottom*/
    private void moveInMaze(Maze3D maze, Position3D current, Position3D next)  {
        if(!isValidPosition(maze, next)) {
            return;
        }

        if(next.getRowIndex() == current.getRowIndex() && next.getDepthIndex() == current.getDepthIndex())
        {
            //go right
            if(next.getColumnIndex() > current.getColumnIndex()){
                maze.getMaze()[current.getDepthIndex()][current.getRowIndex()][current.getColumnIndex()+1] = 0;
            }
            //go left
            else{
                maze.getMaze()[current.getDepthIndex()][current.getRowIndex()][current.getColumnIndex()-1] = 0;
            }
        }
        else if(next.getColumnIndex() == current.getColumnIndex() && next.getDepthIndex() == current.getDepthIndex())
        {
            //go down
            if(next.getRowIndex() > current.getRowIndex()){
                maze.getMaze()[current.getDepthIndex()][current.getRowIndex()+1][current.getColumnIndex()] = 0;
            }
            //go up
            else {
                maze.getMaze()[current.getDepthIndex()][current.getRowIndex()-1][current.getColumnIndex()] = 0;
            }
        }
        else if(next.getColumnIndex() == current.getColumnIndex() && next.getRowIndex() == current.getRowIndex())
        {
            //go up
            if(next.getDepthIndex() < current.getDepthIndex()){
                maze.getMaze()[current.getDepthIndex()-1][current.getRowIndex()][current.getColumnIndex()] = 0;
            }
            //go down
            else {
                maze.getMaze()[current.getDepthIndex()+1][current.getRowIndex()][current.getColumnIndex()] = 0;
            }
        }
    }



}
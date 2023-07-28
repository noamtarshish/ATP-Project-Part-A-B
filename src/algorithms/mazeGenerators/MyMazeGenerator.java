package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    /**Function that maze by iterative dfs*/
    public Maze generate(int rows, int columns) {//int columns, int rows
        Position start = new Position(0,0);
        Random rand=new Random();
        int rowEndPOs =rows-2;
        while (rowEndPOs ==rows-2){
            rowEndPOs =rand.nextInt(rows);
        }
        Position end = new Position(rowEndPOs,columns-1);
        Maze maze=new Maze(rows,columns);
        maze.fillMazeWithWalls(); //to work with maze that full of walls and find a path in the function
        maze.setEndPos(end);
        maze.path(end);
        Stack<Position> stack =new Stack<>();
        Position next =new Position(0,0);
        Position current =new Position(0,0);
        stack.push(current);
        ArrayList<Position> neighbors;
        Random random = new Random();
        int posNum;
        while(!stack.empty())
        {
            current = stack.pop();
            maze.path(current); //convert the wall into path
            neighbors = findNeighbors(maze,rows,columns, current); //calling for helper function to find neighbors
            if(neighbors.size()!=0) {
                stack.push(current);
                posNum = random.nextInt(neighbors.size());
                next = neighbors.get(posNum);
                moveInMaze(maze,current,next); //calling to helper function to fill path in the maze
                current = next;
                stack.push(current);
            }
        }

        //fill random paths and walls in the maze to create complex maze
        for (int i = 0; i < ((int)Math.sqrt(rows*columns)); i++) {
            int randRow = random.nextInt(rows);
            int randColumn = random.nextInt(columns);
            if(maze.getMaze()[randRow][randColumn] == 1) {
                maze.getMaze()[randRow][randColumn] = random.nextInt(2);
            }
        }

//        ensure that the neighbors of the end position is path
        int endRow = end.getRowIndex(), goalColumn = end.getColumnIndex();
        if(isValidPosition(maze,end)){
            Position pos = new Position(endRow+1,goalColumn);
            if (isValidPosition(maze,pos)) {
                maze.getMaze()[endRow + 1][goalColumn] = 0;
            }
            Position pos1 = new Position(endRow,goalColumn-1);
            if (isValidPosition(maze,pos1))
                maze.getMaze()[endRow][goalColumn-1] = 0;
        }

        return maze;
    }

    /**Function that find all the neighbors of the current cell in the maze, the function return it in ArrayList that
     * represent the neighbors of the cell*/
    private ArrayList<Position> findNeighbors(Maze maze, int rows, int columns, Position current) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (columns > current.getColumnIndex()+2 && maze.getMaze()[current.getRowIndex()][current.getColumnIndex()+2] == 1) {
            Position pos = new Position(current.getRowIndex(),current.getColumnIndex()+2);
            neighbors.add(pos);
        }
        if (current.getColumnIndex()-2 >= 0 && maze.getMaze()[current.getRowIndex()][current.getColumnIndex()-2] == 1) {
            Position pos = new Position(current.getRowIndex(),current.getColumnIndex()-2);
            neighbors.add(pos);
        }
        if (rows > current.getRowIndex()+2 && maze.getMaze()[current.getRowIndex()+2][current.getColumnIndex()] == 1) {
            Position pos = new Position(current.getRowIndex()+2,current.getColumnIndex());
            neighbors.add(pos);
        }
        if (current.getRowIndex()-2 >= 0 && maze.getMaze()[current.getRowIndex()-2][current.getColumnIndex()] == 1) {
            Position pos = new Position(current.getRowIndex()-2,current.getColumnIndex());
            neighbors.add(pos);
        }
        return neighbors;
    }

    /**Function that check if a given Position is a valid position in the maze*/
    public boolean isValidPosition(Maze maze, Position position) {
        int row = position.getRowIndex();
        int col = position.getColumnIndex();
        return row >= 0 && row < maze.getMaze().length && col >= 0 && col < maze.getMaze()[0].length;
    }

    /**Function that determine where to advance in the maze according to current and next possible position,
     * there are 6 options by the 6 sides to advance in the maze: right,left,up,down,top,bottom*/
    public void moveInMaze(Maze maze, Position current, Position next)  {
        if(!isValidPosition(maze, next)) {
            return;
        }

        if(next.getRowIndex() == current.getRowIndex())
        {
            //go right
            if(next.getColumnIndex() > current.getColumnIndex()){
                maze.getMaze()[current.getRowIndex()][current.getColumnIndex()+1] = 0;
            }
            //go left
            else{
                maze.getMaze()[current.getRowIndex()][current.getColumnIndex()-1] = 0;
            }
        }
        else if(next.getColumnIndex() == current.getColumnIndex())
        {
            //go down
            if(next.getRowIndex() > current.getRowIndex()){
                maze.getMaze()[current.getRowIndex()+1][current.getColumnIndex()] = 0;
            }
            //go up
            else {
                maze.getMaze()[current.getRowIndex()-1][current.getColumnIndex()] = 0;
            }
        }
    }



}
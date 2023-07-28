package algorithms.mazeGenerators;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Maze implements Serializable {


    private int[][] maze;
    private int rowIndex;
    private int colIndex;
    private Position startPos;
    private Position endPos;

    /**Constructors for the class*/
    public Maze(int rowIndex, int colIndex) {
        if (rowIndex > 0 && colIndex > 0) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            startPos = new Position(0,0);
            endPos = new Position(rowIndex-1,colIndex-1);
            maze = new int[rowIndex][colIndex];
        }
    }

    /**Function that create wall in a specific position in the maze*/
    public void wall(Position p){
        if (!p.equals(startPos) || !p.equals(endPos))
            maze[p.getRowIndex()][p.getColumnIndex()]=1;
    }

    /**Function that create path in a specific position in the maze*/
    public void path(Position p){
            maze[p.getRowIndex()][p.getColumnIndex()]=0;
    }


    public Position getStartPosition() {
        return startPos;
    }

    public void setStartPos(Position startPos) {
        this.startPos = startPos;
    }

    public Position getGoalPosition() {
        return endPos;
    }

    public void setEndPos(Position endPos) {
        this.endPos = endPos;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int getRow() {
        return rowIndex;
    }

    public void setRow(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getCol() {
        return colIndex;
    }

    public void setCol(int colIndex) {
        this.colIndex = colIndex;
    }

    public int getValue(Position pos){
        return maze[pos.getRowIndex()][pos.getColumnIndex()];
    }

    public boolean inBound(Position position) {
        return (position != null &&
                0 <= position.getRowIndex() && position.getRowIndex() < this.rowIndex &&
                0 <= position.getColumnIndex() && position.getColumnIndex() < this.colIndex);
    }

    /**Function that print the maze, the function search the start position and mark it as S and the goal position and mark it with E*/
    public void print() {
        for (int i = 0; i < getRow(); i++) {
            System.out.print("{");
            for (int j = 0; j < getCol(); j++) {
                if (0 == i && 0 == j)
                    System.out.print("S,");
                else if (getGoalPosition().getRowIndex() == i && getGoalPosition().getColumnIndex() == j)
                    System.out.println("E}");
                else if (j < colIndex - 1) {
                    System.out.print(maze[i][j] + ",");
                } else
                    System.out.println(maze[i][j] + "}");
            }
        }
    }

    /**Function that fill all the maze with walls*/
    public void fillMazeWithWalls() {
        for(int i = 0; i<rowIndex; i++){
            for (int j = 0; j<colIndex; j++){
                wall(new Position(i,j));
            }
        }
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(outputStream);

        try {
            // Write basic info
            dataStream.writeShort(maze.length);
            dataStream.writeShort(maze[0].length);
            dataStream.writeShort(startPos.getRowIndex());
            dataStream.writeShort(startPos.getColumnIndex());
            dataStream.writeShort(endPos.getRowIndex());
            dataStream.writeShort(endPos.getColumnIndex());

            // Write maze data
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    dataStream.writeByte(maze[i][j]);
                }
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Maze(byte[] byteArr) {
        int rows = ((byteArr[0] & 0xFF) << 8) | (byteArr[1] & 0xFF);
        int columns = ((byteArr[2] & 0xFF) << 8) | (byteArr[3] & 0xFF);
        maze = new int[rows][columns];

        int startRow = ((byteArr[4] & 0xFF) << 8) | (byteArr[5] & 0xFF);
        int startCol = ((byteArr[6] & 0xFF) << 8) | (byteArr[7] & 0xFF);
        startPos = new Position(startRow,startCol);

        int endRow = ((byteArr[8] & 0xFF) << 8) | (byteArr[9] & 0xFF);
        int endCol = ((byteArr[10] & 0xFF) << 8) | (byteArr[11] & 0xFF);
        endPos = new Position(endRow,endCol);

        setRow(rows);
        setCol(columns);
        int c = 12;
        for (int i = 0; i <= maze.length - 1; i++) {
            for (int j = 0; j <= maze[0].length - 1; j++) {
                maze[i][j] = byteArr[c++];
            }
        }

    }
}



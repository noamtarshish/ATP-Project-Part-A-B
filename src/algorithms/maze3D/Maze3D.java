package algorithms.maze3D;

public class Maze3D {

    private int[][][] maze3d;
    private int depthIndex;
    private int rowIndex;
    private int colIndex;
    private Position3D startPos;
    private Position3D endPos;

    /**
     * Constructor for the class
     */
    public Maze3D(int depthIndex, int rowIndex, int colIndex) {
        if (depthIndex > 0 && rowIndex > 0 && colIndex > 0) {
            this.depthIndex = depthIndex;
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            startPos = new Position3D(0, 0, 0);
            endPos = new Position3D(depthIndex - 1, rowIndex - 1, colIndex - 1);
            maze3d = new int[depthIndex][rowIndex][colIndex];
        }
    }

    /**
     * Function that create wall in a specific position in the maze
     */
    public void wall(Position3D p) {
        if (!p.equals(startPos) || !p.equals(endPos))
            maze3d[p.getDepthIndex()][p.getRowIndex()][p.getColumnIndex()] = 1;
    }

    /**
     * Function that create path in a specific position in the maze
     */
    public void path(Position3D p) {
        maze3d[p.getDepthIndex()][p.getRowIndex()][p.getColumnIndex()] = 0;
    }

    /**Getters and Setters to the class*/

    public Position3D getStartPosition() {
        return startPos;
    }

    public void setStartPos(Position3D startPos) {
        this.startPos = startPos;
    }

    public Position3D getGoalPosition() {
        return endPos;
    }

    public void setGoalPos(Position3D endPos) {
        this.endPos = endPos;
    }

    public int[][][] getMaze() {
        return maze3d;
    }

    public void setMaze(int[][][] maze3d) {
        this.maze3d = maze3d;
    }

    public int getDepth() {
        return depthIndex;
    }

    public void setDepthIndex(int depthIndex) {
        this.depthIndex = depthIndex;
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

    public int getValue(Position3D pos) {
        return maze3d[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()];
    }

    /**Funciton that check if the Position3D is in the limits of the maze to avoid index error */
    public boolean inBound(Position3D pos) {
        return (pos != null &&
                0 <= pos.getDepthIndex() && pos.getDepthIndex() < this.depthIndex &&
                0 <= pos.getRowIndex() && pos.getRowIndex() < this.rowIndex &&
                0 <= pos.getColumnIndex() && pos.getColumnIndex() < this.colIndex);
    }

    /**
     * Function that print the maze, the function search the start position and mark it as S and the goal position and mark it with E
     */
    public void print() {
        System.out.println("{");
        for (int k = 0; k < getDepth(); k++) {
            System.out.println("  {");
            for (int i = 0; i < getRow(); i++) {
                System.out.print("    {");
                for (int j = 0; j < getCol(); j++) {
                    if (0 == i && 0 == j && 0 == k)
                        System.out.print("S,");
                    else if (getGoalPosition().getDepthIndex() == k && getGoalPosition().getRowIndex() == i && getGoalPosition().getColumnIndex() == j)
                        System.out.print("E");
                    else if (j < getCol() - 1) {
                        System.out.print(maze3d[k][i][j] + ",");
                    } else
                        System.out.print(maze3d[k][i][j]);
                }
                if (i == getRow()-1)
                    System.out.println("}");
                else
                    System.out.println("},");
            }
            if (k == getDepth()-1)
                System.out.println("  }");
            else
                System.out.println("  },");
        }
        System.out.println("}");
    }

    /**
     * Function that fill all the maze with walls
     */
    public void fillMazeWithWalls() {
        for (int k = 0; k < depthIndex; k++) {
            for (int i = 0; i < rowIndex; i++) {
                for (int j = 0; j < colIndex; j++) {
                    wall(new Position3D(k, i, j));
                }
            }
        }
    }
}

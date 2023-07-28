package algorithms.maze3D;

/**Class that similar to Position but with another parameter as depth that unique to Position3D*/
public class Position3D {

    private int depthIndex;
    private int rowIndex;
    private int colIndex;


    /**Constructor to create position*/
    public Position3D(int depthIndex, int rowIndex, int colIndex) {

        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;

    }

    /**Function that change position*/
    public Position3D(Position3D pos) {
        if (pos != null) {
            this.rowIndex = pos.getRowIndex();
            this.colIndex = pos.getColumnIndex();
            this.depthIndex = pos.getDepthIndex();
        }
    }


    @Override
    public String toString() {
        return "{" +depthIndex+","+rowIndex+","+colIndex+"}";
    }

    /**Getters and Setters to the class*/
    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return colIndex;
    }

    public int getDepthIndex() {
        return depthIndex;
    }


    public void setRow(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumn(int columnIndex) {
        this.colIndex = columnIndex;
    }

    public void setDepth(int depthIndex) {
        this.depthIndex = depthIndex;
    }


    /**Function that print the position by pattern {depth,row,column}*/
    public void printPosition(){
        System.out.println("{"+this.getDepthIndex()+","+this.getRowIndex()+","+this.getColumnIndex()+"}");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position3D position3d = (Position3D) obj; //casting to position3D
        return (depthIndex==position3d.depthIndex && rowIndex == position3d.rowIndex && colIndex == position3d.colIndex);
    }

}

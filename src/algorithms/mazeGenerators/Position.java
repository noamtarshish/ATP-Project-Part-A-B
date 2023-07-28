package algorithms.mazeGenerators;


import java.io.Serializable;

public class Position implements Serializable {

    private int rowIndex;
    private int colIndex;


    /**Constructor to create position*/
    public Position(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
    }

    /**Function that change position*/
    public Position(Position pos) {
        if (pos != null) {
            this.rowIndex = pos.getRowIndex();
            this.colIndex = pos.getColumnIndex();
        }
    }


    @Override
    public String toString() {
        return "{"+rowIndex+","+colIndex+"}";
    }

    /**Getters and Setters to the class*/
    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return colIndex;
    }

    public void setRow(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumn(int columnIndex) {
        this.colIndex = columnIndex;
    }

    /**Function that print the position by pattern {row,column}*/
    public void printPosition(){
        System.out.println("{"+this.getRowIndex()+","+this.getColumnIndex()+"}");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position position = (Position) obj; //casting to position
        return rowIndex == position.rowIndex && colIndex == position.colIndex;
    }







}

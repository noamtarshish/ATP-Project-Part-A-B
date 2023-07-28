package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.Objects;

/**Abstract class that implements the interface Comparable
 * The class is extended in class MazeState and represent a place in the maze.
 * in this class you can calculate the cost to move from one spot to another spot in the maze, on the way to the end goal
 */
public abstract class AState implements Serializable,Comparable {

    private AState former;
    private Object current;
    private int cost;

    //constructor with values
    public AState(AState prevState, Object position, int cost) {
        this.former=prevState;
        this.current=position;
        this.cost=cost;
    }

    //getters and setters
    public Object getPosition(){
        return this.current;
    }

    public AState getFormer() {
        return former;
    }

    public void setFormer(AState former) {
        this.former = former;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState state = (AState) o;
        return current != null ? current.equals(state.current) : state.current != null;
    }

    @Override
    public String toString(){
        return this.current.toString();
    }


    @Override
    public int compareTo(Object o) {
        AState other = ((AState)o);
        if(cost < other.cost)
            return 1;
        else if (cost == other.cost)
            return 0;
        else
            return -1;
    }
}

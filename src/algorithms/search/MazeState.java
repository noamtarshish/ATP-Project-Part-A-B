package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;

public class MazeState extends AState implements Serializable {
    public MazeState(AState state, Position position, int cost){
        super(state, position, cost);
    }

}

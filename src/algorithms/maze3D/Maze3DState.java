package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {

    public Maze3DState(AState prevState, Position3D pos, int cost) {
        super(prevState, pos, cost);
    }
}

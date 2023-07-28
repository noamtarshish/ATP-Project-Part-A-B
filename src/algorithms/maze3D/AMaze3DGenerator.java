package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {

    /**function that measure the time of the algorithm*/
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long beforeTime = System.currentTimeMillis();
        generate(depth,row,column);
        long afterTime = System.currentTimeMillis();

        return afterTime-beforeTime;
    }
}

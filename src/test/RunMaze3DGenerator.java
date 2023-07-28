package test;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.*;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMaze3DGenerator maze3DGenerator) {
// prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s",
                maze3DGenerator.measureAlgorithmTimeMillis(500/*depth*/,500/*rows*/,500/*columns*/)));
// generate another maze
        Maze3D maze3d = maze3DGenerator.generate(500/*depth*/,500/*rows*/,500/*columns*/);
// prints the maze
        maze3d.print();
// get the maze entrance
        Position3D startPosition = maze3d.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze3d.getGoalPosition()));
    }
}
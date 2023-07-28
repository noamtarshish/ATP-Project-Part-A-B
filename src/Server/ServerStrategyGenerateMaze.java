package Server;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(inFromClient);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);

            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            MyCompressorOutputStream compressorMaze = new MyCompressorOutputStream(byteOutput);
            int[] arrayFromClient = (int[]) in.readObject();
            if (arrayFromClient.length != 2) {
                throw new IOException("The array needs to contain two arguments!");
            }

            Configurations instance = Configurations.getInstance();
            AMazeGenerator mazeGenerator = instance.getMazeGeneratingName();

            Maze maze = mazeGenerator.generate(arrayFromClient[0], arrayFromClient[1]);

            compressorMaze.write(maze.toByteArray());
            out.writeObject(byteOutput.toByteArray());
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
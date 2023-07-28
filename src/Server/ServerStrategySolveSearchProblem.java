package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import static Server.Configurations.getSearchingAlgorithm;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {

        Lock locker = new ReentrantLock(true);

        try {
            Solution solution;
            ObjectInputStream in = new ObjectInputStream(inFromClient);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) in.readObject();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            String mazePath = tempDirectoryPath + "maze" + maze.toString();
            locker.lock();
            File file = new File(mazePath);

            if (file.exists()){
                FileInputStream fileIn = new FileInputStream(mazePath);
                ObjectInputStream objIn = new ObjectInputStream(fileIn);
                solution = (Solution) objIn.readObject();
                fileIn.close();
                objIn.close();
            }

            else{
                locker.unlock();
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                Configurations instance = Configurations.getInstance();
                solution = instance.getMazeSearchingName().solve(searchableMaze);
                locker.lock();
                FileOutputStream fOut = new FileOutputStream(mazePath);
                fOut.flush();
                ObjectOutputStream objOut = new ObjectOutputStream(fOut);
                objOut.flush();
                objOut.writeObject(solution);
                objOut.flush();
            }

            locker.unlock();
            out.writeObject(solution);
            out.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

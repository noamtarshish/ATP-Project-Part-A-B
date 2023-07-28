package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    private static Properties prop;
    private static Configurations instance = null;

    private Configurations() throws IOException {
        prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(input);

        }
        catch (IOException IOe){
            IOe.printStackTrace();
        }

    }

    public AMazeGenerator getMazeGeneratingName() throws IOException {

        String propAlgorithm = prop.getProperty("mazeGeneratingAlgorithm");
        if (propAlgorithm.compareTo("EmptyMazeGenerator")==0)
            return new EmptyMazeGenerator();
        else if (propAlgorithm.compareTo("SimpleMazeGenerator")==0)
            return new SimpleMazeGenerator();
        else
            return new MyMazeGenerator();

    }

    public ASearchingAlgorithm getMazeSearchingName(){

        String propAlgorithm = prop.getProperty("mazeSearchingAlgorithm");
        if (propAlgorithm.compareTo("BreadthFirstSearch")==0)
            return new BreadthFirstSearch();
        else if (propAlgorithm.compareTo("DepthFirstSearch")==0)
            return new DepthFirstSearch();
        else
            return new BestFirstSearch();

    }

    public int getThreadPoolSize(){
        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }

    public static Configurations getInstance() throws IOException {
        if (instance == null)
            instance = new Configurations();
        return instance;
    }
}


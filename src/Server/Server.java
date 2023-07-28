package Server;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService threadPool; // Thread pool


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) throws IOException {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop = false;
        this.threadPool = Executors.newFixedThreadPool(Configurations.getInstance().getThreadPoolSize());
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            int threadPoolSize = Integer.parseInt(prop.getProperty("threadPoolSize"));
            this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
        } catch (IOException ex) {
            ;
        }
    }

    public void start(){
        new Thread(this::startThreadPool).start();
    }

    public void startThreadPool(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    // Insert the new task into the thread pool:
                    threadPool.submit(() -> {
                        handleClient(clientSocket);
                    });
                    ;

                } catch (SocketTimeoutException e){

                }
            }
            //threadPool.shutdown(); // do not allow any new tasks into the thread pool (not doing anything to the current tasks and running threads)
            threadPool.shutdownNow(); // do not allow any new tasks into the thread pool, and also interrupts all running threads (do not terminate the threads, so if they do not handle interrupts properly, they could never stop...)
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            ;
        }
    }

    public void stop(){
        stop = true;
    }
}

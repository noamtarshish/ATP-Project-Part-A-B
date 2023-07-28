package Client;

import java.io.*;

public interface IClientStrategy {
    void clientStrategy(InputStream inFromClient, OutputStream outToClient);
}

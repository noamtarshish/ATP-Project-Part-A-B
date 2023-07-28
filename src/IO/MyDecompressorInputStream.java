package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a custom input stream that decompresses maze data.
 * It reads compressed maze data from an underlying input stream, and decompresses it
 * into a byte array that can be used to create a maze object.
 */
public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return decompress(b);
    }

    /**Decompresses the input byte array into a maze data representation.
    The function reads the compressed maze data from the input byte array,
    and extracts the maze dimensions and cell data. The decompressed data is stored
    in the output byte array. */
    public int decompress(byte[] decompressedMaze) throws IOException {

        //create a new list to store the decompressed data
        ArrayList<Byte> list = new ArrayList<>();

        //read the compressed maze data into a byte array
        byte[] mazeData = new byte[decompressedMaze.length];
        in.read(mazeData);

        //add the first 12 bytes of the maze data to the list
        for (int i = 0; i < 12; i++)
            list.add(mazeData[i]);

        //extract the number of rows and columns from the first 4 bytes of the list
        int rows = ((list.get(0) & 0xFF) << 8) | (list.get(1) & 0xFF);
        int cols = ((list.get(2) & 0xFF) << 8) | (list.get(3) & 0xFF);
        int totalCells = rows * cols;
        int cellsAdded = 0;

        //iterate through the compressed maze data, starting from the 13th byte
        //extract each bit and add it to the list until all cells have been added
        for (int i = 12; i < mazeData.length && cellsAdded < totalCells; i++) {
            for (int j = 0; j < 8 && cellsAdded < totalCells; j++, cellsAdded++) {
                list.add((byte) ((mazeData[i] >> j) & 1));
            }
        }
        //copy the decompressed data from the list into the output array
        //truncate the output array if necessary
        int size = Math.min(list.size(), decompressedMaze.length);
        for (int i = 0; i < size; i++) {
            decompressedMaze[i] = list.get(i);
        }
        return 0;
    }

    @Override
    public void close() throws IOException {
        in.close();
        super.close();
    }

    public InputStream getIn() {
        return in;
    }
}

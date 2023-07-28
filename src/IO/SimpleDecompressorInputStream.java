package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;

    public SimpleDecompressorInputStream(InputStream inputStream) {
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

    /**Function that Decompresses a compressed maze and writes the decompressed data to the provided byte array.*/
    public int decompress(byte[] decompressedMaze) throws IOException {

    //initialize an ArrayList to hold the decompressed bytes
    ArrayList<Byte> list = new ArrayList<>();

    //read the compressed maze data from the input stream
    byte[] mazeData = new byte[decompressedMaze.length];
    in.read(mazeData);

    //copy the first 12 bytes of the maze data to the output
    for (int i = 0; i < 12; i++)
        list.add(mazeData[i]);

    //iterate over the rest of the maze data, interpreting each byte as a repetition count
    //and the corresponding value as the color to repeat
    for (int i = 12; i < mazeData.length; i++)
        for (int j=0; j < mazeData[i]; j++)
            list.add((byte) (i % 2));

    //copy the decompressed bytes from the ArrayList to the output array
//    int size = Math.min(list.size(), decompressedMaze.length);
    for (int i = 0; i < list.size(); i++) {
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

//    private void writeToArrXTimes(byte x,byte num,ArrayList<Byte> targetArr) {
//        for (int i=0 ; i<x ; i++)
//            targetArr.add(num);
//    }
}

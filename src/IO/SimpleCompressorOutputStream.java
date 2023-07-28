package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/** A class that compresses a given maze data and writes it to an output stream.*/
public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream outputStream) {
        this.out=outputStream;
    }

    @Override
    public void write(int i) throws IOException {}

    @Override
    public void write(byte[] b) throws IOException {
        out.write(compress(b));
    }

    /**Function that Compress the given byte array using a simple compression algorithm and returns the compressed data.
    The compression algorithm works by replacing a sequence of repeated bytes with a pair of bytes: the first byte is
    the negative count of the repeated bytes, and the second byte is the value of the repeated byte.*/
    private byte[] compress(byte[] mazeData) throws IOException {
        ArrayList<Byte> list = new ArrayList<>();

        //copy the first 12 bytes of the maze data to the output
        for(int i = 0; i < 12; i++) {
            list.add(mazeData[i]);
        }

        //add a flag byte to indicate if the rest of the data is compressed or not
        byte currentByte = 0;
        if (mazeData[12] != 0) {
            list.add((byte) 0);
        }
        currentByte++;

        //compress the remaining bytes
        for(int i = 13; i < mazeData.length; i++) {
            if (mazeData[i - 1] == mazeData[i]) {
                //if the current byte is the same as the previous byte, increase the count
                if (currentByte == -1) {
                    //if the count reaches the maximum value, add a pair of bytes to the output
                    list.add(currentByte);
                    list.add((byte) 0);
                    currentByte = 1;
                }
                currentByte++;
            }
            else {
                //if the current byte is different from the previous byte, add a pair of bytes to the output
                list.add(currentByte);
                currentByte = 1;
            }
        }
        //add the last pair of bytes to the output
        list.add(currentByte);

        //convert the ArrayList to a byte array
        byte[] compressedMaze = new byte[list.size()];
        for(int i = 0; i < compressedMaze.length; i++) {
            compressedMaze[i] = list.get(i);
        }

        return compressedMaze;
    }



}

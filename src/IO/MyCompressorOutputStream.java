package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**Class that extends OutputStream that compress content by an algorithm*/
public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out=outputStream;
    }


    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(compress(b));
    }

    /**Function that do the compress action to this class
     * The function compress the content of maze to arrayList that convert to byte array
     * by save first the 12 places as the info of the maze:
     * rows,columns,startPos and endPos
     * after, the function compress all the data of the maze itself in the other cell of the byte array*/
    private byte[] compress(byte[] mazeData) {
        ArrayList<Byte> list = new ArrayList<>(); //initialize an ArrayList to store compressed maze data

        //add the basic information of the maze (12 bytes) to the compressed maze
        for (int i = 0; i < 12; i++) {
            list.add(mazeData[i]);
        }

        byte currentByte = 0; //initialize a byte to store current bit sequence
        int bitCount = 0; //initialize an integer to keep track of the number of bits in currentByte

        //loop through the maze data starting from the 13th byte (after the basic info)
        for (int i = 12; i < mazeData.length; i++) {
            if (bitCount == 8) { //if currentByte is full (contains 8 bits), add it to the compressed maze and reset the variables
                list.add(currentByte);
                currentByte = 0;
                bitCount = 0;
            }
            currentByte |= (mazeData[i] << bitCount); //add the next bit to the currentByte using bitwise OR and left shift
            bitCount++; //increment bitCount
        }

        if (bitCount > 0) { //if there are remaining bits in currentByte, add it to the compressed maze
            list.add(currentByte);
        }

        byte[] compressedMaze = new byte[list.size()]; //initialize a new byte array to store compressed maze data
        int size = Math.min(list.size(), compressedMaze.length);
        for (int i = 0; i < size; i++) { //copy the data from the ArrayList to the byte array
            compressedMaze[i] = list.get(i);
        }
        return compressedMaze; //return the compressed maze data as a byte array
    }

    @Override
    public void flush() throws IOException {
        out.flush();
        super.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
        super.close();
    }

    public OutputStream getOut() {return out;}
}

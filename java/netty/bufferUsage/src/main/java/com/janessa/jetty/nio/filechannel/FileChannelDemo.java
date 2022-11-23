package com.janessa.jetty.nio.filechannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileChannelDemo {
    private static final int SIZE = 1024;
    private static Charset charset = Charset.forName("UTF-8");
    private static String fileName = "data.txt";
    public static void main(String[] args) throws IOException {

        File oldFile = new File(fileName);
        if (oldFile.exists()) {
            oldFile.delete();
        }

        FileChannel writer = new FileOutputStream(fileName).getChannel();
        System.out.println("Before write. position = " + writer.position());
        writer.write(ByteBuffer.wrap("hello world!".getBytes()));
        System.out.println("After write. position = " + writer.position());
        writer.close();

        FileChannel readerWriter = new RandomAccessFile(fileName, "rw").getChannel();
        System.out.println("Before readerWriter.position():"+ readerWriter.position());
        readerWriter.position(readerWriter.size());
        System.out.println("After readerWriter.position():"+ readerWriter.position());
        readerWriter.write(ByteBuffer.wrap(" some more".getBytes()));
        System.out.println("After readerWriter.write(ByteBuffer.wrap(\" some more\".getBytes())). position = "+ readerWriter.position());
        readerWriter.close();

        FileChannel reader = new FileInputStream(fileName).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        System.out.println("FileChannel. before read. position = " + reader.position());
        System.out.println("Before read(buffer). position = " + buffer.position() + ", limit = " + buffer.limit());
        if (reader.read(buffer) > 0) {
            System.out.println("After read(buffer), before flip(). position = " + buffer.position() + ", limit = " + buffer.limit());
            buffer.flip();
            System.out.println("After flip(). position = " + buffer.position() + ", limit = " + buffer.limit());
            String content = String.valueOf(charset.decode(buffer));
            System.out.println("Final content:" + content);
        }

    }
}

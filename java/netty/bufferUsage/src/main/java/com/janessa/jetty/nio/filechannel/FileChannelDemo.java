package com.janessa.jetty.nio.filechannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileChannelDemo {
    private static final int SIZE = 1024;
    private static Charset charset = Charset.forName("UTF-8");
    public static void main(String[] args) throws IOException {
        FileChannel writer = new FileOutputStream("data.txt").getChannel();
        writer.write(ByteBuffer.wrap("hello world!".getBytes()));
        writer.close();

        FileChannel readerWriter = new RandomAccessFile("data.txt", "rw").getChannel();
        System.out.println("Before readerWriter.position():"+ readerWriter.position());
        readerWriter.position(readerWriter.size());
        System.out.println("After readerWriter.position():"+ readerWriter.position());
        readerWriter.write(ByteBuffer.wrap(" some more".getBytes()));
        System.out.println("After readerWriter.write(ByteBuffer.wrap(\" some more\".getBytes())). position = "+ readerWriter.position());
        readerWriter.close();

        FileChannel reader = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
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

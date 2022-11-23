package com.janessa.jetty.nio.filechannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ChannelWriteReadDemo {
    private static final String FILE_NAME = "data2.txt";
    private static final Charset charset = Charset.forName("UTF-8");
    private static final ByteBuffer readBuffer = ByteBuffer.allocate(50);
    private static final ByteBuffer writeBuffer = ByteBuffer.allocate(50);

    public static void main(String[] args) throws IOException {
        File oldFile = new File(FILE_NAME);
        if (oldFile.exists()) {
            oldFile.delete();
        }

        FileChannel writer = new FileOutputStream(FILE_NAME).getChannel();
        System.out.println("[Before write] writer channel's position = " + writer.position());
        writer.write(ByteBuffer.wrap("hello".getBytes()));
        System.out.println("[After first write] writer channel's position = " + writer.position());

        System.out.println("------------------------------");

        FileChannel reader = new FileInputStream(FILE_NAME).getChannel();
        System.out.println("[Before read] reader channel's position = " + reader.position());
        System.out.println("[Before read] readBuffer's info: position = " + reader.position() + ", limit = " + readBuffer.limit());
        int n = reader.read(readBuffer);
        System.out.println(n + " chars are read");
        System.out.println("[After first read] readBuffer's info: position = " + reader.position() + ", limit = " + readBuffer.limit());
        System.out.println("[After first read] reader channel's position = " + reader.position());

        System.out.println("------------------------------");
        writer.write(ByteBuffer.wrap(" jane".getBytes()));
        System.out.println("[After second write] writer channel's position = " + writer.position());

        System.out.println("------------------------------");
        n = reader.read(readBuffer);
        System.out.println(n + " chars are read");
        System.out.println("[After second read] readBuffer's info: position = " + reader.position() + ", limit = " + readBuffer.limit());
        System.out.println("[After second read] reader channel's position = " + reader.position());

        System.out.println("------------------------------");
        readBuffer.flip();
        String res = String.valueOf(charset.decode(readBuffer));
        System.out.println("result : " + res);

    }
}

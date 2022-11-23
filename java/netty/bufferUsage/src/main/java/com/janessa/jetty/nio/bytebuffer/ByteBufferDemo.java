package com.janessa.jetty.nio.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ByteBufferDemo {
    private final static int SIZE = 1024;
    private final static Charset charset = Charset.forName("UTF-8");
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(SIZE);
        System.out.println("[Init]   position = " + bb.position() + ", limit = " + bb.limit());
        bb.put(charset.encode("hello"));
        System.out.println("[Put]    position = " + bb.position() + ", limit = " + bb.limit());
        bb.flip();
        System.out.println("[flip]   position = " + bb.position() + ", limit = " + bb.limit());
        System.out.println("The content in ByteBuffer is: " + String.valueOf(charset.decode(bb)));
        bb.clear();
        System.out.println("[clear]  position = " + bb.position() + ", limit = " + bb.limit());
    }
}

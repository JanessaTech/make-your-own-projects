package com.janessa.java.netty.starter;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        //IP and port Server is listening on
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 12345;
        //Create a Socket
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            //reader is used to read message from socket(Server)
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            //writer is used to write message to socket(Server)
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            //userReader is used to get inout from console
            BufferedReader userReader = new BufferedReader(
                    new InputStreamReader(System.in)
            );
            String msg = null;
            //
            while (true) {
                String input = userReader.readLine(); // Program will be stuck until there is an input from console
                //Send message to Server. NOTICE: append \n at the end of each line since Server is using readLine to get message sent by Client
                writer.write(input + "\n");// Send the input message to Server. Notice the message is not actually sent out. It is written into the buffer
                writer.flush(); // The input message is sent out finally
                msg = reader.readLine(); // Read message from socket. Program will be stuck until there is a message sent from Server
                System.out.println(msg);
                // break while loop if the user inputs  "bye"
                if(input.equals("bye")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

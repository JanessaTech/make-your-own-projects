package com.janessa.java.netty.starter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int DEFAULT_PORT = 12345;
        //Create a ServerSocket listening on the port 12345
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            System.out.println("ServerSocket is created on the port " + DEFAULT_PORT);
            while (true) {//serverSocket is listening on 12345 in a loop
                //Listening on the port 12345 in a blocking way. Program will be stuck here until Client connects to Server successfully
                System.out.println("Waiting for Client to connect...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected to Server successfully on the port [" + socket.getPort() + "]");
                //reader is used to receive message from socket(Client)
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                //writer is used to send message to socket(Client)
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                );

                String msg = null;
                while ((msg = reader.readLine()) != null) { // Program will be stuck here until there is a message sent by Client
                    System.out.println("Client said:" + msg);
                    //Send message to Client. NOTICE: append \n at the end of each line since Client is using readLine to get message sent by Server
                    writer.write("Server echo:" + msg + "\n"); // The message is written into buffer
                    writer.flush();// The message is actually sent out to Client
                    //break while loop if Server receives "bye" from Client. Server will be listening on the port 12345 waiting for a new Client to connect to
                    if (msg.equals("bye")) {
                        System.out.println("Client is going to shutdown...");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package sample;

import sample.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static ServerSocket server;
    public static CopyOnWriteArrayList<Connection> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        final int PORT = 1234;
        server = new ServerSocket(PORT);
        while (true) {
            Socket client = server.accept();
            Connection connection = new Connection(client);
            clients.add(connection);
            connection.start();
            System.out.println("connected");
        }


    }

    public void start() throws IOException{
    }

    public void finish() throws IOException{
        server.close();
    }

}

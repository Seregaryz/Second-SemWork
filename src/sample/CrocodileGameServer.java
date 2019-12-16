package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class CrocodileGameServer extends Application {

    private static ServerSocket server;
    public static CopyOnWriteArrayList<sample.Connection> clients = null;
    private final int PORT = 1234;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Crocodile client");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
        Thread.sleep(3000);
        startServer();
        handle();
        finish();
    }

    public void handle() throws IOException {
        while (true){
            Socket client = server.accept();
            Connection connection = new sample.Connection(client);
            clients.add(connection);
            connection.start();
        }
    }

    public void startServer() throws IOException {
        server = new ServerSocket(PORT);
    }

    public void finish() throws IOException{
        server.close();
    }


}

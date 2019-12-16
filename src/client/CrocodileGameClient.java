package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CrocodileGameClient extends Application {

    private Button nicknameBtn, sendBtn;
    private TextField nicknameTf, addMessageTf;
    private TextArea dialogTa;
    private final String HOST = "localhost";
    private final int PORT = 1234;
    private Socket s;
    BufferedReader br;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("client_chat.fxml"));
        primaryStage.setTitle("Crocodile client");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();

        nicknameBtn = (Button) root.lookup("#btn_send_nickname");
        nicknameTf = (TextField) root.lookup("#tf_nickname");
        //nicknameTf.requestFocus();
        addMessageTf = (TextField) root.lookup("#msg_text");
        addMessageTf.requestFocus();
        sendBtn = (Button) root.lookup("#btn_send");
        dialogTa = (TextArea) root.lookup("#dialog");
        startClient();
        handle();


    }

    public void startClient() throws IOException {
        s = new Socket(HOST, PORT);
    }

    public void handle() throws IOException {
        Listener listener = new Listener(s.getOutputStream(), sendBtn, addMessageTf);
        listener.start();
        Reciever reciever = new Reciever(dialogTa, s.getInputStream());
        reciever.start();
    }



}

package client;

import controller.CanvasController;
import controller.GamePanelController;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import packet.PicturePacket;
import sample.Main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
    private BorderPane rootLayout;
    private Stage primaryStage;
    private GraphicsContext gc;

    private Canvas canvas;
    Button btn_draw, btn_clear, btn_type;
    TextField choose_tf;
    ColorPicker color_picker;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("client_chat.fxml"));
//        primaryStage.setTitle("Crocodile client");
//        primaryStage.setScene(new Scene(root, 800, 700));
//        primaryStage.show();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Crocodile");
        this.primaryStage.setFullScreen(false);
        this.primaryStage.setMaxWidth(900.0);
        this.primaryStage.setMaxHeight(500.0);
        this.primaryStage.setMinWidth(900.0);
        this.primaryStage.setMinHeight(500.0);
        this.primaryStage.setResizable(true);
        initRootLayout(primaryStage);
        showPersonOverview();
        //CanvasController canvasController = new CanvasController();
        //gc = canvasController.getGc();
        startClient();
        handle();
    }

    public void initRootLayout(Stage primaryStage) {
        try {
            // Load root layout from fxml file.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("/clientWindow.fxml"));
            rootLayout = FXMLLoader.load(getClass().getResource("client_chat.fxml"));

            nicknameBtn = (Button) rootLayout.lookup("#btn_send_nickname");
            nicknameTf = (TextField) rootLayout.lookup("#tf_nickname");
            //nicknameTf.requestFocus();
            addMessageTf = (TextField) rootLayout.lookup("#msg_text");
            addMessageTf.requestFocus();
            sendBtn = (Button) rootLayout.lookup("#btn_send");
            dialogTa = (TextArea) rootLayout.lookup("#dialog");


            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            AnchorPane personOverview = FXMLLoader.load(getClass().getResource("canvas.fxml"));
            rootLayout.setCenter(personOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
        btn_draw = (Button) rootLayout.lookup("#btn_draw");
        btn_clear = (Button) rootLayout.lookup("#btn_clear");
        canvas = (Canvas) rootLayout.lookup("#drawing_canvas");
        color_picker = (ColorPicker) rootLayout.lookup("#color_picker");
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 600, 400);


        btn_draw.setOnAction((event) -> {
            gc.setFill(color_picker.getValue());
            gc.setStroke(color_picker.getValue());
        });

        btn_clear.setOnAction((event) -> {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 600, 400);
            gc.save();
        });

        canvas.setOnMouseDragged((MouseDragEvent) -> {
            if(GamePanelController.pencil){
                gc.beginPath();
                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
                gc.lineTo(MouseDragEvent.getX(), MouseDragEvent.getY());
                PicturePacket pp = new PicturePacket(MouseDragEvent.getX(), MouseDragEvent.getY());
                try {
                    pp.write(new DataOutputStream(s.getOutputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gc.stroke();
                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
                gc.closePath();
            }
        });
    }


    public void startClient() throws IOException {
        s = new Socket(HOST, PORT);
    }

    public void handle() throws IOException {
        Listener listener = new Listener(s.getOutputStream(), sendBtn, addMessageTf);
        listener.start();
        Reciever reciever = new Reciever(dialogTa, canvas, s.getInputStream());
        reciever.start();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import packet.EndGamePacket;
import packet.EnterGamePacket;
import packet.PicturePacket;
import packet.StartGamePacket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CrocodileGameClient extends Application {

    private Button nicknameBtn, sendBtn, btn_create_room, btn_start_game, btn_cancel, btn_join, btn_joinWindow;
    private TextField nicknameTf, ipTf, addMessageTf, waiting_status_val_label, tfWord;
    private TextArea dialogTa;
    private String host = "";
    private final int PORT = 1234;
    public static Socket s;
    private String nickname = "";
    private BorderPane rootLayout;
    private GraphicsContext gc;
    private boolean isCreator;

    private Canvas canvas;
    Button btn_draw, btn_clear;
    ColorPicker color_picker;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initGameMenu(primaryStage);
    }

    public void initGameMenu(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameMenuWindow.fxml"));
        nicknameBtn = (Button) root.lookup("#btn_send_nickname");
        nicknameTf = (TextField) root.lookup("#tf_nickname");
        ipTf = (TextField) root.lookup("#tf_ip");
        btn_join = (Button) root.lookup("#btn_join");
        btn_create_room = (Button) root.lookup("#btn_create_room");
        if (!nickname.isEmpty()) {
            nicknameTf.setText(nickname);
            ipTf.setText(host);
        } else {
            btn_create_room.setDisable(true);
            btn_join.setDisable(true);
        }
        primaryStage.setTitle("Crocodile client");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        nicknameBtn.setOnAction(event -> {
            if (!nicknameTf.getText().isEmpty()) {
                nickname = nicknameTf.getText();
                host = ipTf.getText();
                btn_create_room.setDisable(false);
                btn_join.setDisable(false);
                nicknameTf.setDisable(true);
                ipTf.setDisable(true);
            }
        });
        btn_create_room.setOnAction(event -> {
            isCreator = true;
            initWaitingWindow(primaryStage, isCreator);
            String nickname = nicknameTf.getText();
            try {
                startCreatorClient(nickname);
                waitingWindowHandle(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn_join.setOnAction(event -> initJoiningWindow(primaryStage));
    }

    public void initJoiningWindow(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("joinRoomWindow.fxml"));
            btn_joinWindow = (Button) root.lookup("#btn_joinWindow");
            Button btnJoinCancel = (Button) root.lookup("#btn_cancelJoining");
            TextField tfCreatorNickname = (TextField) root.lookup("#tf_creator_nickname");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            btn_joinWindow.setOnAction(event -> {
                String nickname = nicknameTf.getText();
                String creatorNickname = tfCreatorNickname.getText();
                System.out.println(nickname + " " + creatorNickname);
                isCreator = false;
                try {
                    startDefaultClient(nickname, creatorNickname);
                    EnterGamePacket enterGamePacket = new EnterGamePacket();
                    enterGamePacket.write(new DataOutputStream(s.getOutputStream()));
                    initRootLayout(primaryStage, isCreator);
                    showPersonOverview(isCreator);
                    try {
                        handle(isCreator);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            btnJoinCancel.setOnAction(event -> {
                try {
                    initGameMenu(primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initWaitingWindow(Stage primaryStage, boolean isCreator) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("waitingWindow.fxml"));
            btn_start_game = (Button) root.lookup("#btn_start_game");
            btn_cancel = (Button) root.lookup("#btn_cancel");
            waiting_status_val_label = (TextField) root.lookup("#waiting_status_val_label");
            waiting_status_val_label.setEditable(false);
            tfWord = (TextField) root.lookup("#tf_word");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("Show show");
            btn_start_game.setOnAction(event -> {
                if (!tfWord.getText().isEmpty()) {
                    StartGamePacket startGamePacket = new StartGamePacket(tfWord.getText());
                    try {
                        startGamePacket.write(new DataOutputStream(s.getOutputStream()));
                        System.out.println("Send chetverka");
                        System.out.println(tfWord.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    initRootLayout(primaryStage, isCreator);
                    showPersonOverview(isCreator);
                    try {
                        handle(isCreator);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        btn_cancel.setOnAction(event -> {
            try {
                initGameMenu(primaryStage);
                EndGamePacket endGamePacket = new EndGamePacket();
                endGamePacket.write(new DataOutputStream(s.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void initRootLayout(Stage primaryStage, boolean isCreator) {
        try {
            rootLayout = FXMLLoader.load(getClass().getResource("client_chat.fxml"));
            addMessageTf = (TextField) rootLayout.lookup("#msg_text");
            addMessageTf.requestFocus();
            sendBtn = (Button) rootLayout.lookup("#btn_send");
            dialogTa = (TextArea) rootLayout.lookup("#dialog");
            dialogTa.setEditable(false);
            if (!isCreator) {
                addMessageTf.setDisable(true);
                sendBtn.setDisable(true);
            }
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview(boolean isCreator) {
        try {
            AnchorPane personOverview = FXMLLoader.load(getClass().getResource("canvas.fxml"));
            rootLayout.setCenter(personOverview);
            canvas = (Canvas) rootLayout.lookup("#drawing_canvas");
            color_picker = (ColorPicker) rootLayout.lookup("#color_picker");
            btn_draw = (Button) rootLayout.lookup("#btn_draw");
            btn_clear = (Button) rootLayout.lookup("#btn_clear");
            if (!isCreator) {
                color_picker.setDisable(true);
                btn_clear.setDisable(true);
                btn_draw.setDisable(true);
            }
            gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 800, 520);
            gc.setStroke(Color.PURPLE);
            gc.setLineWidth(5.0);
            btn_draw.setOnAction((event) -> {
                gc.setFill(color_picker.getValue());
                gc.setStroke(color_picker.getValue());
            });

            btn_clear.setOnAction((event) -> {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 800, 520);
                gc.save();
            });
            canvas.setOnMouseDragged((MouseDragEvent) -> {
                gc.beginPath();
                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
                gc.lineTo(MouseDragEvent.getX(), MouseDragEvent.getY());
                PicturePacket pp = new PicturePacket(MouseDragEvent.getX(), MouseDragEvent.getY());
                try {
                    pp.write(new DataOutputStream(CrocodileGameClient.s.getOutputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gc.stroke();
                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
                gc.closePath();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startCreatorClient(String nickname) throws IOException {
        s = new Socket(host, PORT);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.writeBoolean(true);
        dos.writeUTF(nickname);
    }

    public void startDefaultClient(String nickname, String roomCreator) throws IOException {
        s = new Socket(host, PORT);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.writeBoolean(false);
        dos.writeUTF(nickname);
        dos.writeUTF(roomCreator);
    }

    public void handle(boolean isCreator) throws IOException {
        Listener listener = new Listener(s.getOutputStream(), sendBtn, addMessageTf);
        listener.start();
        Reciever reciever = new Reciever(dialogTa, canvas, sendBtn, addMessageTf, s.getInputStream(), isCreator, listener);
        reciever.start();
    }

    public void waitingWindowHandle(Stage primaryStage) throws IOException {
        WaitingWindowReciever waitingWindowReciever = new WaitingWindowReciever(waiting_status_val_label, s.getInputStream(), primaryStage, isCreator);
        waitingWindowReciever.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package client;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Reciever extends Thread {

    private TextArea dialogTa;
    private DataInputStream dis;
    private Canvas canvas;
    private Button sendButton;
    private TextField messageTf;
    private boolean isCreator;
    private Listener listener;

    public Reciever(TextArea dialogTa, Canvas canvas, Button sendButton, TextField messageTf, InputStream is, boolean isCreator, Listener listener){
        this.dialogTa = dialogTa;
        this.canvas = canvas;
        this.sendButton = sendButton;
        this.messageTf = messageTf;
        this.dis = new DataInputStream(is);
        this.isCreator = isCreator;
        this.listener = listener;
    }

    @Override
    public void run() {
        while (true) {
            short id = -2;
            try {
                id = dis.readShort();
                System.out.println(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(id == 6){
                try {
                    dialogTa.appendText(dis.readUTF() + ": " + dis.readUTF() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!isCreator) {
                switch (id) {
                    case 2: {
                        try {
                            GraphicsContext gc = canvas.getGraphicsContext2D();
                            gc.setFill(Color.PURPLE);
                            gc.setStroke(Color.PURPLE);
                            double x = dis.readDouble();
                            double y = dis.readDouble();
                            canvas.setOnMouseDragged((MouseDragEvent) -> {
                                gc.beginPath();
                                gc.moveTo(x, y);
                                gc.lineTo(x, y);
                                gc.stroke();
                                gc.moveTo(x, y);
                                gc.closePath();
                            });
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    case 4: {
                        sendButton.setDisable(false);
                        messageTf.setDisable(false);
                        break;
                    }
                    case 5: {
                        sendButton.setDisable(true);
                        messageTf.setDisable(true);
                        this.interrupt();
                        listener.interrupt();
                    }
                }
            } else {
                if(id == 5){
                    sendButton.setDisable(true);
                    messageTf.setDisable(true);
                    canvas.setDisable(true);
                    this.interrupt();
                    listener.interrupt();
                }
            }
        }
    }
}

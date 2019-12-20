package client;

import controller.CanvasController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import packet.PicturePacket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Listener extends Thread {

    DataOutputStream dos;
    Button sendBtn;
    TextField messageTf;
    CanvasController canvasController;
    public Listener(OutputStream os, Button sendBtn, TextField messageTf) throws IOException {
        this.dos = new DataOutputStream(os);
        this.messageTf = messageTf;
        this.sendBtn = sendBtn;
        this.canvasController = null;
    }

    @Override
    public void run() {
        while (true){
            sendBtn.setOnAction(event -> {
                String message = messageTf.getText();
                try {
                    dos.writeShort(1);
                    dos.writeUTF(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

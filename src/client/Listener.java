package client;

import controller.CanvasController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import packet.MessagePacket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
                    MessagePacket messagePacket = new MessagePacket(message);
                    messagePacket.write(dos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

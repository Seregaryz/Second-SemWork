package client;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Listener extends Thread {

    PrintWriter printWriter;
    Button sendBtn;
    TextField messageTf;
    public Listener(OutputStream outputStream, Button sendBtn, TextField messageTf) {
        this.printWriter = new PrintWriter(outputStream,true);
        this.messageTf = messageTf;
        this.sendBtn = sendBtn;
    }

    @Override
    public void run() {
        while (true){
            sendBtn.setOnAction(event -> {
                String message = messageTf.getText();
                printWriter.println(message);
            });
        }
    }
}

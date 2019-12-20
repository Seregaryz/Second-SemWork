package client;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;

import java.io.*;

public class Reciever extends Thread {

    private TextArea dialogTa;
    private DataInputStream dis;
    private Canvas canvas;

    public Reciever(TextArea dialogTa, Canvas canvas, InputStream is){
        this.dialogTa = dialogTa;
        this.canvas = canvas;
        this.dis = new DataInputStream(is);
    }

    @Override
    public void run() {
        while (true) {
            short id = -2;
            try {
                id = dis.readShort();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(id != -1){
                switch (id){
                    case 1: {
                        try {
                            dialogTa.appendText(dis.readUTF() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    case 2: {
                        try {
                            canvas.getGraphicsContext2D().lineTo(dis.readDouble(), dis.readDouble());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

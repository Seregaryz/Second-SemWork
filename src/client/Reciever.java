package client;

import javafx.scene.control.TextArea;

import java.io.*;

public class Reciever extends Thread {

    private TextArea dialogTa;
    private BufferedReader br;

    public Reciever(TextArea dialogTa, InputStream out){
        this.dialogTa = dialogTa;
        this.br = new BufferedReader(new InputStreamReader(out));
    }

    @Override
    public void run() {
        while (true) {
            String input = null;
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(input != null){
                dialogTa.appendText(input);
            }
        }
    }
}

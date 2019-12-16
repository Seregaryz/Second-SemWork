import client.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String HOST = "localhost";
        final int PORT = 1234;
        Socket s = new Socket(HOST, PORT);
        System.out.println("connected");
        //Listener listener = new Listener(s.getOutputStream());
        //listener.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while (true) {
            String input = bufferedReader.readLine();
            if(input != null){
                System.out.println(input);
            }
        }
    }
}

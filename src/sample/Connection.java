package sample;

import java.io.*;
import java.net.Socket;

public class Connection extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket client;
    public Connection(Socket client) {
        this.client = client;
        try {
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void run() {
        while (true){
            try {
                String input = in.readLine();
                if(input != null){
                    System.out.println(input);
                    for(Connection connection : Server.clients){
                        connection.getOut().println(input);
                    }
                }
                else{
                    Server.clients.remove(this);
                    Thread.currentThread().interrupt();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

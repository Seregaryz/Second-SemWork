package sample;

import java.io.*;
import java.net.Socket;

public class Connection extends Thread {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket client;
    public Connection(Socket client) {
        this.client = client;
        try {
            this.dis = new DataInputStream(client.getInputStream());
            this.dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataInputStream getIn() {
        return dis;
    }

    public void setIn(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getOut() {
        return dos;
    }

    public void setOut(DataOutputStream dos) {
        this.dos = dos;
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
                short id = dis.readShort();
                switch (id){
                    case 1: {
                        String text = dis.readUTF();
                        System.out.println(text);
                        for(Connection connection : Server.clients){
                            connection.getOut().writeShort(id);
                            connection.getOut().writeUTF(text);
                        }
                    }
                    case 2: {
                        double x = dis.readDouble();
                        double y = dis.readDouble();
                        System.out.println(x + "" + y);
                        for(Connection connection : Server.clients){
                            connection.getOut().writeShort(id);
                            connection.getOut().writeDouble(x);
                            connection.getOut().writeDouble(y);
                        }
                    }
                }
//                if(id != null){
//                    System.out.println(input);
//                    for(Connection connection : Server.clients){
//                        connection.getOut().println(input);
//                    }
//                }
//                else{
//                    Server.clients.remove(this);
//                    Thread.currentThread().interrupt();
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

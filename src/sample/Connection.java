package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket client;
    private String nickname;
    private Room room;
    public Connection(Socket client, String nickname) {
        this.client = client;
        this.nickname = nickname;
        try {
            this.dis = new DataInputStream(client.getInputStream());
            this.dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void run() {
        while (true){
            try {
                short id = dis.readShort();
                switch (id){
                    case 1:{
                        for(Connection connection : room.clients) {
                            connection.getOut().writeShort(id);
                        }
                        break;
                    }
                    case 4:{
                        for(Connection connection : room.clients) {
                            connection.getOut().writeShort(id);
                        }
                        room.setWord(dis.readUTF());
                        System.out.println(id);
                        break;
                    }
                    case 6: {
                        String text = dis.readUTF();
                        System.out.println(text);
                        System.out.println(room.clients.size());
                        for(Connection connection : room.clients){
                            connection.getOut().writeShort(id);
                            connection.getOut().writeUTF(nickname);
                            connection.getOut().writeUTF(text);
                        }
                        text.equals(room.getWord());
                        for(Connection connection : room.clients){
                            connection.getOut().writeShort(5);
                        }
                        break;
                    }
                    case 2: {
                        double x = dis.readDouble();
                        double y = dis.readDouble();
                        for(Connection connection : room.clients){
                            connection.getOut().writeShort(id);
                            connection.getOut().writeDouble(x);
                            connection.getOut().writeDouble(y);
                        }
                    }
                    case 5: {
                        for(Connection connection : room.clients) {
                            connection.getOut().writeShort(id);
                        }
                        for(Connection connection : room.clients) {
                            connection.interrupt();
                        }
                        System.out.println(id);
                        break;
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

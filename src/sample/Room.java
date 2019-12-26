package sample;

import java.util.concurrent.CopyOnWriteArrayList;

public class Room{

    public CopyOnWriteArrayList<Connection> clients = new CopyOnWriteArrayList<>();
    private Connection creator;
    private String word;

    public Room(Connection creator) {
        this.creator = creator;
    }

    public Connection getCreator() {
        return creator;
    }

    public void setCreator(Connection creator) {
        this.creator = creator;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NewRoomPacket extends OPacket {

    private Socket owner;
    private short id = 1;

    public NewRoomPacket() {
    }

    public NewRoomPacket(Socket owner) {
        this.owner = owner;
    }


    @Override
    public short getId() {
        return id;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    @Override
    public void handle() {

    }


}

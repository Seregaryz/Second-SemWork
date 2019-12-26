package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EnterGamePacket extends OPacket {

    private short id = 1;
    private int countOfClient = 1;

    public EnterGamePacket() {
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(id);
        //dos.writeBoolean(isCreating);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
    }

    public void setId(short id) {
        this.id = id;
    }

    public int getCountOfClient() {
        return countOfClient;
    }

    public void setCountOfClient(int countOfClient) {
        this.countOfClient = countOfClient;
    }
}

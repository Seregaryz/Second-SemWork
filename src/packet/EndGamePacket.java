package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EndGamePacket extends OPacket {

    private short id = 5;

    @Override
    public short getId() {
        return id;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(id);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }
}

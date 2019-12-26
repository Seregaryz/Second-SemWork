package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessagePacket extends OPacket {

    private short id = 6;
    private String message;

    public MessagePacket(String message) {
        this.message = message;
    }

    @Override
    public short getId() {
        return 6;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(id);
        dos.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }
}

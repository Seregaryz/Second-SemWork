package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StartGamePacket extends OPacket {

    private short id = 4;
    private String word;

    public StartGamePacket(String word) {
        this.word = word;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(id);
        dos.writeUTF(word);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
    }
}

package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NicknamePacket extends OPacket{

    private short id = 3;
    private String nickname;

    public NicknamePacket(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(id);
        dos.writeUTF(nickname);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
    }

}

package packet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PicturePacket extends OPacket {


    private double x, y;
    private short id = 2;

    public PicturePacket(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(id);
        dos.writeDouble(x);
        dos.writeDouble(y);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    @Override
    public void handle() {

    }

    public static <T> T fromJson(String json, Class<T> classOfT){
        Gson gson = new GsonBuilder().create();
        try{
            return gson.fromJson(json, classOfT);
        }catch(JsonSyntaxException jse){
            return null;
        }
    }
    public static String toJson(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            return gson.toJson(obj);
        }catch(JsonSyntaxException jse){
            return null;
        }
    }
}

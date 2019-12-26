package client;

import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WaitingWindowReciever extends Thread {

    private TextField waiting_status_val_label;
    private DataInputStream dis;
    private BorderPane rootLayout;
    private Stage primaryStage;
    private boolean isCreator;
    private GraphicsContext gc;

    public WaitingWindowReciever(TextField waiting_status_val_label, InputStream is, Stage primaryStage, boolean isCreator) {
        this.waiting_status_val_label = waiting_status_val_label;
        this.dis = new DataInputStream(is);
        this.primaryStage = primaryStage;
        this.isCreator = isCreator;
    }

    @Override
    public void run() {
        while (true) {
            short id = -2;
            try {
                id = dis.readShort();
                System.out.println(id);
                if (id != -1) {
                    switch (id) {
                        case 1: {
                            int currentCount = Integer.parseInt(waiting_status_val_label.getText()) + 1;
                            waiting_status_val_label.setText(currentCount + "");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showPersonOverview(boolean isCreator) {
        try {
            AnchorPane personOverview = FXMLLoader.load(getClass().getResource("canvas.fxml"));
            rootLayout.setCenter(personOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

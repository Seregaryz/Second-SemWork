package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GamePanelController {

    @FXML
    Button btn_send;
    @FXML
    TextField  msg_text;
    @FXML
    TextArea dialog;


    @FXML
    private void initialize() {
        msg_text.requestFocus();
    }
}

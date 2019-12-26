package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WaitingWindowController {

    @FXML
    Button btn_start_game, btn_cancel;
    @FXML
    Label waiting_status_desc_label, label_word;
    @FXML
    TextField waiting_status_val_label, tf_word;
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

public class Controller {

    @FXML
    Canvas drawing_canvas;
    @FXML
    Button btn_send_nickname, btn_create_room, btn_rooms_list, btn_send;
    Label nickname_label;
    @FXML
    TextField tf_nickname, msg_text;
    @FXML
    TextArea dialog;
}

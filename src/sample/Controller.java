package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

public class Controller {

    @FXML
    Canvas drawing_canvas;
    @FXML
    Button btn_send_nickname, btn_create_room, btn_rooms_list, btn_send, btn_draw, btn_clear, btn_type;
    Label nickname_label;
    @FXML
    TextField tf_nickname, msg_text, choose_tf;
    @FXML
    TextArea dialog;
    @FXML
    ColorPicker color_picker;
}

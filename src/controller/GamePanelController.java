package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

public class GamePanelController {

    @FXML
    private MenuItem myAbout;
    @FXML
    private MenuItem myVersion;
    @FXML
    private MenuItem myLineTool;
    @FXML
    private MenuItem myRectangleTool;
    @FXML
    private MenuItem myPencilTool;

    @FXML
    Canvas drawing_canvas;
    @FXML
    Button btn_send_nickname, btn_create_room, btn_rooms_list, btn_send;
    Label nickname_label;
    @FXML
    TextField tf_nickname, msg_text;
    @FXML
    TextArea dialog;

    public static boolean line = false;
    public static boolean rectangle = false;
    public static boolean pencil = false;

    @FXML
    private void initialize(){
        myAbout.setOnAction((event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.setContentText("Made by Chukhlantsev Peter, 07.01.2016");

            alert.showAndWait();

        });
        myVersion.setOnAction((event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Version");
            alert.setHeaderText(null);
            alert.setContentText("Current version is 1.0");

            alert.showAndWait();

        });

        myLineTool.setOnAction((event) -> {
            if(!line){
                rectangle = false;
                pencil = false;
                line = true;
            }else{
                line = false;
            }
        });

        myRectangleTool.setOnAction((event) -> {
            if(!rectangle){
                pencil = false;
                line = false;
                rectangle = true;
            }else{
                rectangle = false;

            }
        });

        myPencilTool.setOnAction((event) -> {
            if(!pencil){
                line = false;
                rectangle = false;
                pencil = true;
            }else{
                pencil = false;
            }
        });

    }
}

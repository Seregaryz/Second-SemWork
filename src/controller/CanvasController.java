package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;

public class CanvasController extends Canvas {

    @FXML
    ChoiceBox<Integer> line_width_choise;
    @FXML
    Canvas drawing_canvas;
    @FXML
    Button btn_draw, btn_clear;
    @FXML
    ColorPicker color_picker;
    private static GraphicsContext gc;

//    private CanvasController(){}
//
//    private static class CanvasControllerHolder{
//        private static final CanvasController instance = new CanvasController();
//    }
//
//    public static CanvasController getInstance(){
//        return CanvasControllerHolder.instance;
//    }

//    @FXML
//    private void initialize() {
//        gc = drawing_canvas.getGraphicsContext2D();
//        gc.setFill(Color.WHITE);
//        gc.fillRect(0, 0, 600, 400);
//        gc.setStroke(Color.PURPLE);
//
//            btn_draw.setOnAction((event) -> {
//                gc.setFill(color_picker.getValue());
//                gc.setStroke(color_picker.getValue());
//            });
//
//            btn_clear.setOnAction((event) -> {
//                gc.setFill(Color.WHITE);
//                gc.fillRect(0, 0, 600, 400);
//                gc.save();
//            });
//
//            drawing_canvas.setOnMouseDragged((MouseDragEvent) -> {
//                gc.beginPath();
//                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
//                gc.lineTo(MouseDragEvent.getX(), MouseDragEvent.getY());
//                PicturePacket pp = new PicturePacket(MouseDragEvent.getX(), MouseDragEvent.getY());
//                try {
//                    pp.write(new DataOutputStream(CrocodileGameClient.s.getOutputStream()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                gc.stroke();
//                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
//                gc.closePath();
//            });
//        }
//



//        PicturePacket picturePacket = new PicturePacket();
//        gc = drawing_canvas.getGraphicsContext2D();
//        gc.setFill(Color.WHITE);
//        gc.fillRect(0, 0, 600, 400);
//
//        ObservableList<Integer> widthItems = FXCollections.observableArrayList(10, 15, 20, 25, 30, 35, 40);
//        line_width_choise.setItems(widthItems);
//
//        line_width_choise.getSelectionModel().selectedIndexProperty().addListener(
//                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
//                    gc.setLineWidth(new_val.intValue());
//        });
//
//        btn_type.setOnAction((event) -> {
//            String str = choose_tf.getText();
//            gc.fillText(str, 0, 400 - 29, 600);
//        });
//        btn_draw.setOnAction((event) -> {
//            gc.setFill(color_picker.getValue());
//            gc.setStroke(color_picker.getValue());
//        });
//
//        btn_clear.setOnAction((event) -> {
//            gc.setFill(Color.WHITE);
//            gc.fillRect(0, 0, 600, 400);
//            gc.save();
//        });

//        drawing_canvas.setOnMousePressed((MouseEvent) -> {
//            if(GamePanelController.rectangle){
//                System.out.println((int) MouseEvent.getX());
//                x1 = MouseEvent.getX();
//                System.out.println((int) MouseEvent.getY());
//                y1 = MouseEvent.getY();
//            }
//            if(GamePanelController.line){
//                x1 = MouseEvent.getX();
//                y1 = MouseEvent.getY();
//            }
//        });

//        drawing_canvas.setOnMouseReleased((MouseEvent) -> {
//            String json = PicturePacket.toJson(gc);
//            System.out.println(json);
//            if(GamePanelController.rectangle){
//                x2 = MouseEvent.getX();
//                System.out.println((int) MouseEvent.getX());
//                y2 = MouseEvent.getY();
//                System.out.println((int) MouseEvent.getY());
//                gc.setFill(color_picker.getValue());
//                if((x1 < x2 ) & (y1 < y2)){
//                    gc.fillRect(x1, y1, x2-x1, y2-y1); //  из левого верхнего угла
//                }else if((x2 < x1) & (y2 < y1)){
//                    gc.fillRect(x2, y2, x1-x2, y1-y2); // из правого нижнего угла
//                }else if((x2 < x1) & (y1 < y2) ){
//                    gc.fillRect(x2, y1, x1-x2 , y2-y1); // из правого верхнего угла
//                }else if((x1 < x2) & ( y2 < y1)){
//                    gc.fillRect(x1, y2, x2-x1, y1-y2); // из левого нижнего угла
//                }
//            }
//            if(GamePanelController.line){
//                x2 = MouseEvent.getX();
//                y2 = MouseEvent.getY();
//                gc.strokeLine(x1, y1, x2, y2);
//                gc.setFill(color_picker.getValue());
//            }
//        });

//        drawing_canvas.setOnMouseDragged((MouseDragEvent) -> {
//            if(GamePanelController.pencil){
//                gc.beginPath();
//                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
//                gc.lineTo(MouseDragEvent.getX(), MouseDragEvent.getY());
//
//                gc.stroke();
//                gc.moveTo(MouseDragEvent.getX(), MouseDragEvent.getY());
//                gc.closePath();
//            }
//        });

    }
//
//    public GraphicsContext getGc(){
//        return gc;
//    }


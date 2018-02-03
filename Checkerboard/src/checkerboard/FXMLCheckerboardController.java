/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Lin
 */
public class FXMLCheckerboardController implements Initializable, Startable {
    
    private Stage stage;
    private int numRows = 8;
    private int numCols = 8;
    private Color[] colors = {Color.RED, Color.BLACK, Color.SKYBLUE, Color.DARKBLUE};
    private Color lightColor = colors[0];//default setting
    private Color darkColor  = colors[1]; 
    private grid.Checkerboard checkerboard;   
    private double offsetOfVBox = 49;
     
    @FXML
    private VBox vBox; 

    @FXML
    private void handleAction16(ActionEvent event) {
        System.out.println("16*16");
        this.numRows = 16;
        this.numCols = 16;
        refresh();
    }
    @FXML
    private void handleAction10(ActionEvent event) {
        System.out.println("10*10");
        this.numRows = 10;
        this.numCols = 10;
        refresh();
    }
    @FXML
    private void handleAction8(ActionEvent event) {
        System.out.println("8*8");
        this.numRows = 8;
        this.numCols = 8;
        refresh();
    } 
    @FXML
    private void handleAction3(ActionEvent event) {
        System.out.println("3*3");
        this.numRows = 3;
        this.numCols = 3;
        refresh();
    }
    @FXML
    private void handleActionDef(ActionEvent event) {
        System.out.println("Default");
        this.lightColor = colors[0];
        this.darkColor  = colors[1];
        refresh();
    }
    @FXML
    private void handleActionBl(ActionEvent event) {
        System.out.println("Blue");
        this.lightColor = colors[2];
        this.darkColor  = colors[3];
        refresh();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }   
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        checkerboard = new grid.Checkerboard(this.numRows, this.numCols, stage.getWidth(), stage.getHeight());
        checkerboard.build(stage.getWidth(), stage.getHeight()-this.offsetOfVBox, this.numRows, this.numCols, this.lightColor, this.darkColor);
        vBox.getChildren().add(checkerboard.getBoard());
        
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refresh();
        };
        
        this.stage.widthProperty().addListener(lambdaChangeListener);
        this.stage.heightProperty().addListener(lambdaChangeListener);
        
    }
    
    @FXML
    private void handleClear(ActionEvent event) {
        System.out.println("clear");
        clearGridPane();
    }
    
    @FXML
    private void handleAbout(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("RandomGrid");
        alert.setContentText("This application was developed by Chuyang Lin based on Dale Musser's class code for CS4330 at the University of Missouri.");
        alert.showAndWait();
    }
        
    private void refresh() {
        checkerboard.build(stage.getWidth(), stage.getHeight()-this.offsetOfVBox, this.numRows, this.numCols, this.lightColor, this.darkColor);
    }
    
    private void clearGridPane() {
        checkerboard.clear();
    }
}

package com.example.gestemp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class dashController {
    @FXML
    private Label bienvenuid;

    @FXML
    private Button empid;

    @FXML
    private Button congid;

    @FXML
    private Button paieid;

    @FXML
    void btncongeaction(ActionEvent event)throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("conges.fxml"));
        Scene scene;
        scene = new Scene(fxmlLoader.load(), 643, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnemployeaction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashemploye.fxml"));
        Scene scene;
        scene = new Scene(fxmlLoader.load(), 643, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnpaieaction(ActionEvent event)throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashfpaie.fxml"));
        Scene scene;
        scene = new Scene(fxmlLoader.load(), 643, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}

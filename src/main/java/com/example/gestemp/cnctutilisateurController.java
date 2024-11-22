package com.example.gestemp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class cnctutilisateurController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private Button loginutilid;

    @FXML
    private Button compteid;

    @FXML
    private Label loginmessagelabel;
    @FXML
    void creercompte(ActionEvent event) {

    }

    @FXML
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://yourdomn?useTimezone=true&serverTimezone=UTC", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
            return null;
        }
    }
    @FXML
     void loginutilid(ActionEvent event) throws IOException {
        if(username.getText().equals("Useruser")&& password.getText().equals("root")) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuutilisateur.fxml"));
        Scene scene;
        scene = new Scene(fxmlLoader.load(), 643, 400);
        stage.setTitle("espacee employee");
        stage.setScene(scene);
        stage.show();}
        else if (password.getText().isEmpty()) {
            if (username.getText().isEmpty()) {
                loginmessagelabel.setText("Echec!");
            } else {
                loginmessagelabel.setText("Wrong password or user name!");
            }
        } else {
            loginmessagelabel.setText("Wrong password or user name!");
        }
    }
}

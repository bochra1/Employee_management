package com.example.gestemp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private Button buttlo;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    @FXML
    private Button btnutils;
    @FXML
    private Button butca;
    @FXML
    private Label loginmessagelabel;


    @FXML
    void cancelbuttonaction(ActionEvent event) {
  Stage stage =(Stage) butca.getScene().getWindow();
  stage.close();

    }

    @FXML
    void loginbuttonaction(ActionEvent event)  throws IOException {
        Stage stage = new Stage();
        if(username.getText().equals("admin")&& password.getText().equals("123456")) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashbord.fxml"));
            Scene scene;
            scene = new Scene(fxmlLoader.load(), 643, 400);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
            }





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
    @FXML
    void cncteutilsateur(ActionEvent event) throws IOException {
        Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cnctutilisateur.fxml"));
            Scene scene;
            scene = new Scene(fxmlLoader.load(), 643, 400);
            stage.setTitle("espace employe!");
            stage.setScene(scene);
            stage.show();

    }
    }


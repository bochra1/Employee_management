package com.example.gestemp;

import Modele.conge;
import Modele.utilisateurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class menuutilisateurController implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private TextField password;
    @FXML
    private TableColumn<utilisateurs, Integer> id;
    @FXML
    private TableColumn<conge, Integer> idconge;
    @FXML
    private Label label;
    @FXML
    private TableColumn<conge, Integer> idemploye;

    @FXML
    private TableColumn<conge, String> typeid;

    @FXML
    private TableColumn<conge, Date> debut;

    @FXML
    private TableColumn<conge, Date> fin;
    @FXML
    private TableColumn<utilisateurs, String> nom;

    @FXML
    private TableColumn<utilisateurs, String> prenom;

    @FXML
    private TableColumn<utilisateurs, String> sexe;

    @FXML
    private TableColumn<utilisateurs, Date> datenaiss;

    @FXML
    private TableColumn<utilisateurs, String> poste;

    @FXML
    private TableColumn<utilisateurs, String> nomdep;

    @FXML
    private TableColumn<utilisateurs, String> adresse;

    @FXML
    private TextField yourid_id;
    @FXML
    private Button infoperso;

    @FXML
    private Button idcong;
    @FXML
    private Button returnid;
    @FXML
    private TableView<conge> tabviewconge;
    @FXML
    void conge(ActionEvent event) {
        tabviewconge.setVisible(true);
        tableview.setVisible(false);
showconge();
    }
    @FXML
    private TableView<utilisateurs> tableview;
    @FXML
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useTimezone=true&serverTimezone=UTC", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
            return null;
        }
    }
    public ObservableList<utilisateurs> getinfolist()  {
            ObservableList<utilisateurs> infolist = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM employe WHERE id= " + yourid_id.getText()+"";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            utilisateurs emp;
            while (rs.next()) {
                emp = new utilisateurs(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"), rs.getDate("date_Naiss"),rs.getString("poste"), rs.getString("nomDep"),rs.getString("adresse"));
                infolist.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return infolist;
    }
    @FXML
    void infoperson(ActionEvent event) throws IOException {
        if (yourid_id.getText().isEmpty()) { label.setText("Id n 'est pas valide! ");}
        else{
        tableview.setVisible(true);
        tabviewconge.setVisible(false);

        show();}
    }
    public void show(){
        ObservableList<utilisateurs> list = getinfolist();
        id.setCellValueFactory(new PropertyValueFactory<utilisateurs,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("sexe"));
        datenaiss.setCellValueFactory(new PropertyValueFactory<utilisateurs, Date>("date_Naiss"));
        poste.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("poste"));
        nomdep.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("nomDep"));
        adresse.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("adresse"));
        tableview.setItems(list);
    }
    public ObservableList<conge> getcongelist()  {
        ObservableList<conge> congelist = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM conges WHERE idemploye= " + yourid_id.getText()+"";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            conge cong;
            while (rs.next()) {
                cong = new conge(rs.getInt("idconge"), rs.getInt("idemploye"), rs.getString("type"),  rs.getDate("dateDebut"),rs.getDate("dateFin"));
                congelist.add(cong);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return congelist;
    }
    public void showconge() {
        ObservableList<conge> list = getcongelist();
        idconge.setCellValueFactory(new PropertyValueFactory<conge, Integer>("idconge"));
        idemploye.setCellValueFactory(new PropertyValueFactory<conge, Integer>("idemploye"));
        typeid.setCellValueFactory(new PropertyValueFactory<conge, String>("type"));
        debut.setCellValueFactory(new PropertyValueFactory<conge, Date>("dateDebut"));
        fin.setCellValueFactory(new PropertyValueFactory<conge, Date>("dateFin"));
        tabviewconge.setItems(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show();
        showconge();
    }

    @FXML
    void btnreturn(ActionEvent event) throws IOException {
        Stage stage = (Stage) returnid.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("cnctutilisateur.fxml"));
        primaryStage.setTitle("Profile");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }}
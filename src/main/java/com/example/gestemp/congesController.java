package com.example.gestemp;


import Modele.conge;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

public class congesController implements Initializable {   @FXML
private TextField conges_id;

    @FXML
    private TextField employe_id;

    @FXML
    private ComboBox<String> type_id;

    @FXML
    private DatePicker datedebut_id;

    @FXML
    private DatePicker datefin_id;

    @FXML
    private Button ajoutid;

    @FXML
    private Button modifierid;

    @FXML
    private Button supprimerid;

    @FXML
    private Button retourid;

    @FXML
    private TextField tfsearch;
    @FXML
    private TableView<conge> tableviewid;

    @FXML
    private TableColumn<conge, Integer> idconge;

    @FXML
    private TableColumn<conge, Integer> idemploye;

    @FXML
    private TableColumn<conge, String> idtype;

    @FXML
    private TableColumn<conge, Date> iddatedebut;

    @FXML
    private TableColumn<conge, Date> iddatefin;

    ObservableList<conge> congechoix;

    @FXML
    private Button rechercheid;
    @FXML
    void selecttype(ActionEvent event) {
        String s = type_id.getSelectionModel().getSelectedItem().toString();

    }
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("congé payé", "congé sans solde" ,"congé annuel","congé examen","congé de formation","congé de recherche","congé maladie","congé maternité");
        type_id.setItems(list);

        showconge();

    }
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


    public ObservableList<conge> getcongelist()  {
        ObservableList<conge> congelist = FXCollections.observableArrayList();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM conges";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            conge congee;
            while (rs.next()) {
                congee = new conge(rs.getInt("idconge"), rs.getInt("idemploye"), rs.getString("type"),  rs.getDate("dateDebut"),rs.getDate("dateFin"));
                congelist.add(congee);
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
        idtype.setCellValueFactory(new PropertyValueFactory<conge, String>("type"));
        iddatedebut.setCellValueFactory(new PropertyValueFactory<conge, Date>("dateDebut"));
        iddatefin.setCellValueFactory(new PropertyValueFactory<conge, Date>("dateFin"));
        tableviewid.setItems(list);
    }
    @FXML
    void btnajout(ActionEvent event) {
        String query = "INSERT INTO conges VALUES (" + conges_id.getText() + ",'" + employe_id.getText() + "','"+type_id.getValue().toString() + "','" + datedebut_id.getValue().toString() + "','" +datefin_id.getValue().toString() + "')";
        executeQuery(query);
        showconge();


    }

    private void executeQuery (String query) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Statement st;
        try {
            st= conn.createStatement();
            st.executeUpdate(query);
        }catch( Exception ex)
        {ex.printStackTrace();}
    }

    @FXML
    void btnmodifier(ActionEvent event) {
        String query = "UPDATE  conges SET idconge  = '" + conges_id.getText() + "',idemploye='"  +
                employe_id.getText() + "',type = '" + type_id.getValue().toString() + "', dateDebut= '" + datedebut_id.getValue().toString() +"' ,dateFin='"+ datefin_id.getValue().toString() + "' WHERE idconge = " + conges_id.getText() + "";
        executeQuery(query);
        showconge();
    }

    @FXML
    void btnrecherche(ActionEvent event) {
        idconge.setCellValueFactory(new PropertyValueFactory<conge,Integer>("idconge"));
        idemploye.setCellValueFactory(new PropertyValueFactory<conge,Integer>("idemploye"));
        idtype.setCellValueFactory(new PropertyValueFactory<conge,String>("type"));
        iddatedebut.setCellValueFactory(new PropertyValueFactory<conge,Date>("dateDebut"));
        iddatefin.setCellValueFactory(new PropertyValueFactory<conge, Date>("dateFin"));
        tableviewid.setItems(congechoix);

        congechoix = getcongelist();

        FilteredList<conge> filteredData = new FilteredList<>(congechoix, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(person.getIdconge()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else return false;

            } );
        } );
        SortedList<conge> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableviewid.comparatorProperty());
        tableviewid.setItems(sortedData);
    }

    @FXML
    void btnretour(ActionEvent event) throws IOException {
        Stage stage = (Stage) retourid.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dashbord.fxml"));
        primaryStage.setTitle("Congés");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @FXML
    void btnsupprimer(ActionEvent event) {

        String query = "DELETE FROM conges WHERE idconge = " + idconge.getText() + "";
        executeQuery(query);
        showconge();
    }
    }




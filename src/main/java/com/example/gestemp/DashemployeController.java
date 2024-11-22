package com.example.gestemp;

import Modele.utilisateurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class DashemployeController implements Initializable {
    @FXML
    private Button ajout_id;
    @FXML
    private Button modifier_id;
    @FXML
    private Button rechercheid;

    @FXML
    private Button supp_id;
    @FXML
    private Button returnid;


    @FXML
    private TextField id_id;

    @FXML
    private TextField nom_id;

    @FXML
    private TextField prenom_id;

    @FXML
    private TextField dep_id;
    @FXML
    private TextField adresse_id;
    @FXML
    private TextField contrat_id;

    @FXML
    private ComboBox comboBox_id;

    @FXML
    private DatePicker date_id;
    @FXML
    private TextField tfsearch;

    @FXML
    private TableView<utilisateurs> tableemploye;

    @FXML
    private TableColumn<utilisateurs, Integer> colid;

    @FXML
    private TableColumn<utilisateurs, String> colnom;

    @FXML
    private TableColumn<utilisateurs, String> colprenom;

    @FXML
    private TableColumn<utilisateurs, String> coldep;

    @FXML
    private TableColumn<utilisateurs, String> colsexe;

    @FXML
    private TableColumn<utilisateurs, Date> coldaten;

    @FXML
    private TableColumn<utilisateurs, String> colcontrat;
    @FXML
    private TableColumn<utilisateurs, String> coladr;
 ObservableList<utilisateurs> employechoix;
    @FXML
    public void selectsexe(ActionEvent event) {
        String s = comboBox_id.getSelectionModel().getSelectedItem().toString();

    }


    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Femme", "Homme");
        comboBox_id.setItems(list);

            showemploye();

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


    public ObservableList<utilisateurs> getemployelist()  {
        ObservableList<utilisateurs> employelist = FXCollections.observableArrayList();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM employe";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            utilisateurs employe;
            while (rs.next()) {
                employe = new utilisateurs(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"), rs.getDate("date_Naiss"),rs.getString("poste"), rs.getString("nomDep"),rs.getString("adresse"));
                employelist.add(employe);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employelist;
    }
   public void showemploye()  {
      ObservableList<utilisateurs> list = getemployelist();
      colid.setCellValueFactory(new PropertyValueFactory<utilisateurs,Integer>("id"));
      colnom.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("nom"));
      colprenom.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("prenom"));
       colsexe.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("sexe"));
      coldaten.setCellValueFactory(new PropertyValueFactory<utilisateurs,Date>("date_Naiss"));
      colcontrat.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("poste"));
       coldep.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("nomDep"));
       coladr.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("adresse"));
      tableemploye.setItems(list);
   }
    @FXML
    void btnajouter(ActionEvent event) {
        //if (event.getSource() == ajout_id){
        String query = "INSERT INTO employe VALUES (" + id_id.getText() + ",'" + nom_id.getText() + "','" +prenom_id.getText()+"','"+comboBox_id.getValue().toString() + "','" + date_id.getValue().toString() + "','" + contrat_id.getText() + "','" + dep_id.getText() + "','" + adresse_id.getText() + "')";
        executeQuery(query);
        showemploye();}
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
String query = "UPDATE  employe SET nom  = '" + nom_id.getText() + "',prenom='"  +
        prenom_id.getText() + "',sexe = '" + comboBox_id.getValue().toString() + "', date_Naiss= '" + date_id.getValue().toString() + "', poste = '" + contrat_id.getText() + "', nomDep = '" + dep_id.getText() +
        "', adresse = '" + adresse_id.getText()  + "' WHERE id = " + id_id.getText() + "";
        executeQuery(query);
        showemploye();
    }

    @FXML
    void btnsupprimer(ActionEvent event) {
        Alert alert =new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("supprimer");
        alert.setHeaderText(null);
        alert.setContentText(" Are you sure ?");
        alert.showAndWait();
        String query = "DELETE FROM employe WHERE id =" + id_id.getText() + "";
        executeQuery(query);
        showemploye();


    }

    @FXML
    void btnrecherche(ActionEvent event) {
        colid.setCellValueFactory(new PropertyValueFactory<utilisateurs,Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("prenom"));
        colsexe.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("sexe"));
        coldaten.setCellValueFactory(new PropertyValueFactory<utilisateurs,Date>("date_Naiss"));
        colcontrat.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("poste"));
        coldep.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("nomDep"));
        coladr.setCellValueFactory(new PropertyValueFactory<utilisateurs,String>("adresse"));

        employechoix = getemployelist();
        tableemploye.setItems(employechoix);



        FilteredList<utilisateurs> filteredData = new FilteredList<>(employechoix, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(person.getId()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else return false;

            } );
        } );
        SortedList<utilisateurs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableemploye.comparatorProperty());
        tableemploye.setItems(sortedData);


    }
    @FXML
    void btnreturn(ActionEvent event) throws IOException {
        Stage stage = (Stage) returnid.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dashbord.fxml"));
        primaryStage.setTitle("Profile");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

}









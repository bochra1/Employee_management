package com.example.gestemp;

import Modele.fichedepaie;
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

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.io.IOException;


public class DashfpaieController implements Initializable {
    @FXML
    private TextField idmat;

    @FXML
    private TextField idnomprenom;

    @FXML
    private TextField idnsecu;

    @FXML
    private TextField idnbenfants;

    @FXML
    private ComboBox idsituation;

    @FXML
    private Button btnajout;

    @FXML
    private TextField idposte;
    @FXML
    private DatePicker iddebut;

    @FXML
    private DatePicker idfin;
    @FXML
    private DatePicker iddateemb;

    @FXML
    private ComboBox idechellon;

    @FXML
    private TextField idcont;

    @FXML
    private TextField idsalaire;

    @FXML
    private TextField idnetfiscal;

    @FXML
    private TextField idnbheures;

    @FXML
    private TextField idcotisation;

    @FXML
    private TextField idassurance;

    @FXML
    private TextField idnetàpayer;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btngenerate;

    @FXML
    private TextField tfsearch;

    @FXML
    private Button rechercheid;
    @FXML
    private Button returnid;

    @FXML
    private TableView<fichedepaie> tablesalaire;

    @FXML
    private TableColumn<fichedepaie, Integer> colmat;

    @FXML
    private TableColumn<fichedepaie, String> colnomprenom;

    @FXML
    private TableColumn<fichedepaie, String> colposte;

    @FXML
    private TableColumn<fichedepaie, String> colechellon;

    @FXML
    private TableColumn<fichedepaie, String> colcontrat;

    @FXML
    private TableColumn<fichedepaie, Double> colsalairebrute;
    ObservableList<fichedepaie> fichchoix;

    @FXML
    private TableColumn<fichedepaie, Double> colnetàpayer;

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
    void ajout(ActionEvent event) throws IOException{
        String query = "INSERT INTO fichedepaie VALUES (" + idmat.getText() + ",'" + idnomprenom.getText() + "','" +
                iddebut.getValue().toString()+ "','" + idfin.getValue().toString() + "','" + iddateemb.getValue().toString() + "'," + idnsecu.getText() +
                ",'" + idcont.getText() + "','" + idechellon.getValue() + "', '" + idposte.getText() + "'," +
                idnbheures.getText() + ",'" + idsituation.getValue() + "'," + idnbenfants.getText() + "," + idsalaire.getText() + "," + idnetàpayer.getText() +
                ",'" + idassurance.getText() + "'," + idcotisation.getText() + "," + idnetfiscal.getText() + ")";
        //String query = "INSERT INTO fichedepaie VALUES (" + idmat.getId() + ",'" + idnomprenom.getText() + "','" +iddebut.getValue().toString()+"','"+idfin.getValue().toString() + "','" + iddateemb.getValue().toString() + "'," + idnsecu.getText() + "," + idcont.getText() + ",'" + idechellon.getValue() +"', '"+ idposte.getText() + "',"+ idnbheures.getText() +",'"+ idsituation.getValue().toString() + "',"+ idnbenfants.getText() + ","+ idsalaire.getText() + ","+ idnetàpayer.getText() + ",'"+ idassurance.getText() + "',"+ idcotisation.getText() + ","+ idnetfiscal.getText() + ")";
        executeQuery(query);
        showinfo();
    }

    @FXML
    void btnrecherche(ActionEvent event) {
        colmat.setCellValueFactory(new PropertyValueFactory<fichedepaie,Integer>("matricule"));
        colnomprenom.setCellValueFactory(new PropertyValueFactory<fichedepaie,String>("nomprenom"));
        colposte.setCellValueFactory(new PropertyValueFactory<fichedepaie,String>("poste"));
        colechellon.setCellValueFactory(new PropertyValueFactory<fichedepaie,String>("echelon"));
        colcontrat.setCellValueFactory(new PropertyValueFactory<fichedepaie, String>("contrat"));
        colsalairebrute.setCellValueFactory(new PropertyValueFactory<fichedepaie,Double>("salairebrute"));
        colnetàpayer.setCellValueFactory(new PropertyValueFactory<fichedepaie,Double>("netàpayer"));
        fichchoix = getfichlist();
        tablesalaire.setItems(fichchoix);



        FilteredList<fichedepaie> filteredData = new FilteredList<>(fichchoix, b -> true);
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(person.getMatricule()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else return false;

            } );
        } );
        SortedList<fichedepaie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablesalaire.comparatorProperty());
        tablesalaire.setItems(sortedData);
    }

    @FXML
    void generate(ActionEvent event) {

    }

    @FXML
    void modifier(ActionEvent event) {
        //String query = "UPDATE  fichedepaie SET matricule = " + idmat.getText() + ",nomprenom='"  +
              // idnomprenom.getText() + "',datedebut = '" + iddebut.getValue().toString() + "', datef= '" + idfin.getValue().toString() + "', dateembauche = '" + iddateemb.getValue().toString() + "', NoSecurite = " + idnsecu.getText() +", contrat = '" + idcont.getText() +"', echelon = '" + idechellon.getValue().toString() +"', poste = '" + idposte.getText() +"', h_menseuel = " + idnbheures.getText() +", situation = '" + idsituation.getValue().toString()+"', Nbenfants = " + idnbenfants.getText() +", salairebrute= " + idsalaire.getText() +", Netàpayer = " + idnetàpayer.getText() +", assurance = " + idassurance.getText() +
                //",cotisation = " + idcotisation.getText()+", Netfiscale = " + idnetfiscal.getText()   + " WHERE matricule = " + idmat.getText() + "";
        String query = "UPDATE  fichedepaie SET matricule = " + idmat.getText() + ", nomprenom='"  +
                idnomprenom.getText() + "', datedeb = '" + iddebut.getValue().toString() + "', datef= '" + idfin.getValue().toString() + "', dateembauche = '" + iddateemb.getValue().toString() + "', NoSecurite = " + idnsecu.getText() + ", contrat = '" + idcont.getText() + "', echelon = '" + idechellon.getValue().toString() +
                "', poste = '" + idposte.getText() + "', h_menseuel = " + idnbheures.getText() + ", situation = '" + idsituation.getValue().toString()+
                "', Nbenfants = " + idnbenfants.getText() + ", salairebrute= " + idsalaire.getText() + ", Netàpayer = " + idnetàpayer.getText() +
                ", assurance = '" + idassurance.getText() + "', cotisation = "
                + idcotisation.getText()+ ", Netfiscale = " + idnetfiscal.getText() + " WHERE matricule = " + idmat.getText() + "";
        executeQuery(query);
        showinfo();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> list = FXCollections.observableArrayList("célibataire", "marié","divorcé");
        idsituation.setItems(list);
        ObservableList<String> listechelon = FXCollections.observableArrayList("1", "2","3");
        idechellon.setItems(listechelon);
        showinfo();
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


    public ObservableList<fichedepaie> getfichlist()  {
        ObservableList<fichedepaie> fichlist = FXCollections.observableArrayList();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM fichedepaie";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            fichedepaie fich;
            while (rs.next()) {
                fich = new fichedepaie(rs.getInt("matricule"), rs.getString("nomprenom"), rs.getDate("datedeb"),rs.getDate("datef"),rs.getDate("dateembauche"),rs.getInt("NoSecurite"),rs.getString("contrat"),rs.getString("echelon"),rs.getString("poste"),rs.getDouble("h_menseuel"),rs.getString("situation"),rs.getInt("nbenfants"),rs.getDouble("salairebrute"),rs.getDouble("netàpayer"),rs.getString("assurance"),rs.getDouble("cotisation"),rs.getDouble("netfiscale"));
                fichlist.add(fich);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fichlist;
    }
    private void showinfo() {
        ObservableList<fichedepaie> list = getfichlist();
        colmat.setCellValueFactory(new PropertyValueFactory<fichedepaie,Integer>("matricule"));
        colnomprenom.setCellValueFactory(new PropertyValueFactory<fichedepaie,String>("nomprenom"));
        colposte.setCellValueFactory(new PropertyValueFactory<fichedepaie,String>("poste"));
        colechellon.setCellValueFactory(new PropertyValueFactory<fichedepaie,String>("echelon"));
        colcontrat.setCellValueFactory(new PropertyValueFactory<fichedepaie, String>("contrat"));
        colsalairebrute.setCellValueFactory(new PropertyValueFactory<fichedepaie,Double>("salairebrute"));
        colnetàpayer.setCellValueFactory(new PropertyValueFactory<fichedepaie,Double>("netàpayer"));
        tablesalaire.setItems(list);
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

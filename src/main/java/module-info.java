module com.example.gestemp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.gestemp to javafx.fxml;
    exports com.example.gestemp;
    exports Modele;
    opens Modele to javafx.fxml;
}
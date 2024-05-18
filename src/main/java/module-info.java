module com.example.scsbro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scsbro to javafx.fxml;
    exports com.example.scsbro;
}
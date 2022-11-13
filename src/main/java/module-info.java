module com.example.kyrsach2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.kyrsach2 to javafx.fxml;
    exports com.example.kyrsach2;
}
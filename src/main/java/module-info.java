module com.example.bruhcastingv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bruhcastingv2 to javafx.fxml;
    exports com.example.bruhcastingv2;
}
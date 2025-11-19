module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
}
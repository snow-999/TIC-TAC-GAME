module com.example.tic_tac {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tic_tac to javafx.fxml;
    exports com.example.tic_tac;
}
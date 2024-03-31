module com.example.tiktaktoeio {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.tiktaktoeio to javafx.fxml;
    exports com.example.tiktaktoeio;

}
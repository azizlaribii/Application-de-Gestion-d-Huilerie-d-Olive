module com.example.huilerie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires java.desktop;
    opens models to javafx.fxml;
    opens com.example.huilerie to javafx.fxml;
    exports com.example.huilerie;
    exports models;
}
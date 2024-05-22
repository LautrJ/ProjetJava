module ildeilc.gestion_ildeilc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ildeilc.gestion_ildeilc to javafx.fxml;
    exports app;
    exports app.model;
    exports app.controller;
}
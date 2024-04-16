module mwa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires java.sql;

    opens mwa to javafx.fxml;
    exports mwa;

    opens mwa.controllers to javafx.fxml;
    exports mwa.controllers;

    opens mwa.models to javafx.base;
    exports mwa.models;

    opens mwa.dao to javafx.base;
    exports mwa.dao;

}
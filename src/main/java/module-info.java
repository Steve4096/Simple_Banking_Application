module com.simple_banking_application.simple_banking_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires java.sql;
    requires mysql.connector.j;
    requires jbcrypt;

    //requires transitive de.jensd.fx.glyphs.fontawesome;








    opens com.simple_banking_application.simple_banking_application to javafx.fxml;
    exports com.simple_banking_application.simple_banking_application;
    exports com.simple_banking_application.simple_banking_application.Controllers;
    exports com.simple_banking_application.simple_banking_application.Controllers.Admin;
    exports com.simple_banking_application.simple_banking_application.Controllers.Client;
    exports com.simple_banking_application.simple_banking_application.Controllers.NewClient;
    exports com.simple_banking_application.simple_banking_application.Models;
    exports com.simple_banking_application.simple_banking_application.Views;


}
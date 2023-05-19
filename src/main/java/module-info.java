module com.example.emailclientcourse {
    requires java.activation;
    requires java.mail;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.emailclientcourse to javafx.fxml, javafx.base;
    exports com.example.emailclientcourse;
    exports com.example.emailclientcourse.controller;
    opens com.example.emailclientcourse.controller to javafx.fxml, javafx.base;
    opens com.example.emailclientcourse.model to javafx.fxml, javafx.base;
    opens com.example.emailclientcourse.view to javafx.fxml, javafx.base;
}

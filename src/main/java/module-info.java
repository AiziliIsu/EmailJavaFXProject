module com.example.emileclientcourse {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.emileclientcourse to javafx.fxml;
    exports com.example.emileclientcourse;
}
package com.example.emailclientcourse;

//So this is not the original working project, but all changes would be made here.

import com.example.emailclientcourse.view.ViewFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args){launch(args);}
    public void start(Stage stage) throws Exception{
        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();
    }
}

package com.example.emailclientcourse.view;

import com.example.emailclientcourse.EmailManager;
import com.example.emailclientcourse.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

import java.io.IOException;

public class ViewFactory {
    private EmailManager emailManager;
    private ArrayList<Stage> activeStages;
    private boolean mainViewInitialized = false;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStages = new ArrayList<Stage>();
    }
    public boolean isMainViewInitialized(){
        return mainViewInitialized;
    }

    //View options handling:
    private ColorTheme colorTheme = ColorTheme.Dark;
    private FontSize fontSize = FontSize.Medium;

    public ColorTheme getColorTheme() {return colorTheme;}
    public void setColorTheme(ColorTheme colorTheme) {this.colorTheme = colorTheme;}
    public FontSize getFontSize() {return fontSize;}
    public void setFontSize(FontSize fontSize) {this.fontSize = fontSize;}
    public void showLoginWindow(){
        System.out.println("login window called");
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initializeStage(controller);
    }
    public void showMainWindow(){
        System.out.println("main window called");
        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        initializeStage(controller);
        mainViewInitialized = true;
    }

    public void showOptionsWindow(){
        System.out.println("options window called");
        BaseController controller = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        initializeStage(controller);
    }

    public void showComposeMessageWindow(){
        System.out.println("compose message window called");
        BaseController controller = new ComposeMessageController(emailManager, this, "ComposeMessageWindow.fxml");
        initializeStage(controller);
    }

    public void showEmailDetailsWindow(){
        BaseController controller = new EmailDetailsController(emailManager, this, "EmailDetailsWindow.fxml");
        initializeStage(controller);
    }
    private void initializeStage(BaseController baseController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try{
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    public void updateStyles() {
        for (Stage stage: activeStages) {
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());

        }
    }
}
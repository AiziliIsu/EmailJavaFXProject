package com.example.emailclientcourse.controller;

import com.example.emailclientcourse.EmailManager;
import com.example.emailclientcourse.controller.services.LoginService;
import com.example.emailclientcourse.model.EmailAccount;
import com.example.emailclientcourse.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends BaseController implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField emailAddressField;
    @FXML
    private PasswordField passwordField;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        System.out.println("loginButtonAction!");
        if (fieldsAreValid()){
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            loginService.start();
            loginService.setOnSucceeded(event -> {
                EmailLoginResult emailLoginResult = loginService.getValue();
                switch (emailLoginResult){
                    case Success:
                        System.out.println("logged in successfully!" + emailAccount);
                        if (!viewFactory.isMainViewInitialized()){
                            viewFactory.showMainWindow();
                        }
                        Stage stage = (Stage) errorLabel.getScene().getWindow();
                        viewFactory.closeStage(stage);
                        return;
                    case Failed_by_credentials:
                        errorLabel.setText("Invalid credentials.");
                        return;
                    case Failed_by_unexpected_error:
                        errorLabel.setText("Unexpected error");
                        return;
                    default:
                        return;
                }
            });
        }
    }

    private boolean fieldsAreValid() {
        if (emailAddressField.getText().isEmpty()){
            errorLabel.setText("Please indicate your email address.");
            return false;
        }
        if (passwordField.getText().isEmpty()){
            errorLabel.setText("Please enter your password.");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailAddressField.setText("iskenderova.aizirek7@gmail.com");
        passwordField.setText("mdxglvbyzqpjzfwg");

    }
}

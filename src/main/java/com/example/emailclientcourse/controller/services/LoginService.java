package com.example.emailclientcourse.controller.services;

import com.example.emailclientcourse.EmailManager;
import com.example.emailclientcourse.controller.EmailLoginResult;
import com.example.emailclientcourse.model.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.*;

public class LoginService extends Service<EmailLoginResult> {
    EmailAccount emailAccount;
    EmailManager emailManager;

    public LoginService(EmailAccount emailAccount, EmailManager emailManager) {
        this.emailAccount = emailAccount;
        this.emailManager = emailManager;
    }
    private EmailLoginResult login(){
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount.getAddress(), emailAccount.getPassword());
            }
        };
        try {
            //Thread.sleep(30000);
            Session session = Session.getInstance(emailAccount.getProperties(), authenticator);
            emailAccount.setSession(session);
            Store store = session.getStore("imaps");
            store.connect(emailAccount.getProperties().getProperty("incomingHost"),
                    emailAccount.getAddress(),
                    emailAccount.getPassword());
            emailAccount.setStore(store);
            emailManager.addEmailAccount(emailAccount);


        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return EmailLoginResult.Failed_by_network;
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            return EmailLoginResult.Failed_by_credentials;
        } catch (MessagingException e) {
            e.printStackTrace();
            return EmailLoginResult.Failed_by_unexpected_error;
        } catch (Exception e) {
            e.printStackTrace();
            return EmailLoginResult.Failed_by_unexpected_error;
        }
        return EmailLoginResult.Success;
    }

    @Override
    protected Task<EmailLoginResult> createTask() {
        return new Task<EmailLoginResult>() {
            @Override
            protected EmailLoginResult call() throws Exception {
                return login();
            }
        };
    }
}
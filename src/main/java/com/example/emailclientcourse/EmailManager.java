package com.example.emailclientcourse;

import com.example.emailclientcourse.controller.services.FetchFoldersService;
import com.example.emailclientcourse.model.EmailAccount;
import com.example.emailclientcourse.model.EmailTreeItem;
import javafx.scene.control.TreeItem;

public class EmailManager{
    //Folder handling
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");
    public EmailTreeItem<String> getFoldersRoot(){
        return foldersRoot;
    }
    public void addEmailAccount(EmailAccount emailAccount){
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }
}
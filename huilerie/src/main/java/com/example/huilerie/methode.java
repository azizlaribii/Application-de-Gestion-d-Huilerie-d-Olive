package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class methode {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchScene1 (String fxmlfile, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlfile));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchScene2 (String fxmlfile, MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlfile));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
}
}
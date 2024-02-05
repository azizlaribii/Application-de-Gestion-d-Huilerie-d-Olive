package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homepage_agent implements Initializable {

    @FXML
    private Pane agriculteur;

    @FXML
    private Pane commenrcant;

    @FXML
    private Pane exportateur;

    @FXML
    private ImageView home;

    @FXML
    private Button liste_agri;

    @FXML
    private Button liste_client;

    @FXML
    private Pane particulier;
    public void initialize(URL location, ResourceBundle resources) {
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }
    methode m = new methode();
    @FXML
    void Client_particulier(MouseEvent event) throws IOException {
        m.SwitchScene2("carte_particulier.fxml",event);
    }

    @FXML
    void agriculteur(MouseEvent event) throws IOException {
        m.SwitchScene2("carte_agriculteur.fxml",event);
    }

    @FXML
    void agriculteurs(ActionEvent event) throws IOException {
        m.SwitchScene1("liste_agriculteur_agent.fxml",event);
    }

    @FXML
    void client_commerçant(MouseEvent event) throws IOException {
        m.SwitchScene2("carte_commerçant.fxml",event);
    }

    @FXML
    void client_exportateur(MouseEvent event) throws IOException {
        m.SwitchScene2("carte_exportateur.fxml",event);
    }

    @FXML
    void clients(ActionEvent event) throws IOException {
        m.SwitchScene1("liste_client_agent.fxml",event);
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_agent.fxml",event);
    }

}

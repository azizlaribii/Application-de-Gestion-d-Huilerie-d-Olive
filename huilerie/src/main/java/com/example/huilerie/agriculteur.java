package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class agriculteur implements Initializable {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private TextField adresse;

    @FXML
    private ImageView home;

    @FXML
    private Button liste_agri;

    @FXML
    private Button liste_client;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField telephone;
    public void initialize(URL location, ResourceBundle resources) {
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
        con = com.example.huilerie.DB.DBconnection.bdConnection();
    }
    methode m = new methode();
    @FXML
    void agriculteurs(ActionEvent event) throws IOException {
        m.SwitchScene1("liste_agriculteur_agent.fxml",event);
    }

    @FXML
    void clients(ActionEvent event) throws IOException {
        m.SwitchScene1("liste_client_agent.fxml",event);
    }
    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_agent.fxml",event);
    }
    @FXML
    void creer(ActionEvent event) {
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || adresse.getText().isEmpty() ||
                telephone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les informations manquantes.");
            alert.showAndWait();
            return;
        }
        if (!isValidNom(nom.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nom valide.");
            alert.showAndWait();
            return;
        }
        if (!isValidPrenom(prenom.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un prénom valide.");
            alert.showAndWait();
            return;
        }
            if (!isValidadresse(adresse.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer une adresse valide.");
                alert.showAndWait();
                return;
            }
        if (!isValidPhoneNumber(telephone.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un numéro de téléphone valide.");
            alert.showAndWait();
            return;
        }
        String query = "insert into agriculteur (nom, prenom, adresse, telephone)values (?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nom.getText());
            pst.setString(2, prenom.getText());
            pst.setString(3, adresse.getText());
            pst.setString(4, telephone.getText());
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.NONE,"Agriculteur ajouté avec succès !" , ButtonType.OK);
            alert.setTitle("Succès");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }

    private boolean isValidNom(String nom) {
        String regex = "^[A-Z][a-zA-Z]*$";
        return nom.matches(regex);
    }
    private boolean isValidPrenom(String prenom) {
        String regex = "^[A-Z][a-zA-Z]*$";
        return prenom.matches(regex);
    }
    private boolean isValidadresse(String adresse) {
        String regex = "^[A-Za-z0-9\\s.,'-@]+@[A-Za-z]+\\.[A-Za-z]+$";
        return adresse.matches(regex);
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+216|00\\s?216|0)?\\s?[2-9]\\d{7}$";
        return phoneNumber.matches(regex);
    }


}

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
import java.sql.*;
import java.util.ResourceBundle;

public class carte_commerçant implements Initializable {

    @FXML
    private Button creer;

    @FXML
    private ImageView home;

    @FXML
    private Button liste_agri;

    @FXML
    private Button liste_client;

    @FXML
    private TextField mail;

    @FXML
    private TextField matricule;

    @FXML
    private TextField nom;

    @FXML
    private TextField postal;

    @FXML
    private TextField telephone;
    Connection con;
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
        if (mail.getText().isEmpty() || matricule.getText().isEmpty() || nom.getText().isEmpty() ||
                postal.getText().isEmpty() || telephone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les informations manquantes.");
            alert.showAndWait();
            return;
        }
        if (!isValidmatricule(matricule.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une matricule valide.");
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
        if (!isValidnom(nom.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nom valide.");
            alert.showAndWait();
            return;
        }
        if (!isValidEmail(mail.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une adresse email valide.");
            alert.showAndWait();
            return;
        }
        if (!isValidpostal(postal.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une adresse postale valide.");
            alert.showAndWait();
            return;
        }


        // SQL queries to insert data into the tables
        String selectClientQuery = "SELECT code_client FROM client inner join client_commercant on client.id_client = client_commercant.id_client WHERE client.adresse_electronique = ? OR client_commercant.matricul_fiscal = ?";
        String insertClientQuery = "INSERT INTO client (type_client, adresse_postale, adresse_electronique, telephone) VALUES (?, ?, ?, ?)";
        String insertClientCommercantQuery = "INSERT INTO client_commercant (id_client,matricul_fiscal, nom_commercial) VALUES (?, ?, ?)";

        try (
                PreparedStatement selectStmt = con.prepareStatement(selectClientQuery);
                PreparedStatement insertClientStmt = con.prepareStatement(insertClientQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                PreparedStatement insertCommercantStmt = con.prepareStatement(insertClientCommercantQuery);
        ) {
            // Check if the client already exists
            selectStmt.setString(1, mail.getText());
            selectStmt.setString(2, matricule.getText());
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                // Client already exists, show an error message
                Alert alert = new Alert(Alert.AlertType.NONE, "Le client existe déjà dans la base de données.", ButtonType.OK);
                alert.setTitle("Erreur");
                alert.showAndWait();
                return;
            }

            // Set parameter values for the first SQL query
            insertClientStmt.setString(1, "Commercant");
            insertClientStmt.setString(2, postal.getText());
            insertClientStmt.setString(3, mail.getText());
            insertClientStmt.setString(4, telephone.getText());

            // Execute the first SQL query
            int numRowsAffected1 = insertClientStmt.executeUpdate();
            System.out.println(numRowsAffected1 + " row(s) inserted into client table successfully!");

            // Get the generated ID for the new row in the client table
            rs = insertClientStmt.getGeneratedKeys();
            int clientId = -1;
            if (rs.next()) {
                clientId = rs.getInt(1);
            }
            System.out.println("this is client id" + clientId);

            // Set parameter values for the second SQL query
            insertCommercantStmt.setInt(1, clientId);
            insertCommercantStmt.setString(2, matricule.getText());
            insertCommercantStmt.setString(3, nom.getText());

            // Execute the second SQL query
            int numRowsAffected2 = insertCommercantStmt.executeUpdate();
            System.out.println(numRowsAffected2 + " row(s) inserted successfully!");

            Alert alert = new Alert(Alert.AlertType.NONE,"Client commerçant ajouté avec succès !" , ButtonType.OK);
            alert.setTitle("Succès");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"L'insertion a échoué: " + e.getMessage(), ButtonType.OK);
            alert.setTitle("Erreur");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    private boolean isValidmatricule(String matricule) {
        String regex = "^[0-9]{8}$";
        return matricule.matches(regex);
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+216|00\\s?216|0)?\\s?[2-9]\\d{7}$";
        return phoneNumber.matches(regex);
    }
    private boolean isValidnom(String nom) {
        String regex = "^[A-Z][a-zA-Z]*$";
        return nom.matches(regex);
    }
    private boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
    private boolean isValidpostal(String adresse) {
        String regex = "^(\\d+ [A-Za-z\\s\\-'']+)\\s*([A-Za-z\\s\\-'']+\\s+\\d{4})$";
        return adresse.matches(regex);
    }
}

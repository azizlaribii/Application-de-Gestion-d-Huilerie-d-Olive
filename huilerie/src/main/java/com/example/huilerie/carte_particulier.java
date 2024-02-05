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
import java.util.regex.Pattern;

public class carte_particulier implements Initializable {

    @FXML
    private Button creer;

    @FXML
    private ImageView home;

    @FXML
    private TextField id;

    @FXML
    private Button liste_agri;

    @FXML
    private Button liste_client;

    @FXML
    private TextField mail;

    @FXML
    private TextField nom;

    @FXML
    private TextField postal;

    @FXML
    private TextField prenom;

    Connection con ;

    @FXML
    private TextField telephone;
    public void initialize(URL location, ResourceBundle resources) {
        con = com.example.huilerie.DB.DBconnection.bdConnection();
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
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

        if (mail.getText().isEmpty() || id.getText().isEmpty() || prenom.getText().isEmpty() || nom.getText().isEmpty() ||
                postal.getText().isEmpty() || telephone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les informations manquantes.");
            alert.showAndWait();
            return;
        }
        if (!isValidCIN(id.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un CIN valide.");
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
        if (!isValidEmail(mail.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une adresse email valide.");
            alert.showAndWait();
            return;
        }
        if (!isValidAdressePostale(postal.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une adresse postale valide.");
            alert.showAndWait();
            return;
        }


        // SQL queries to insert data into the tables
        String selectClientQuery = "SELECT code_client FROM client inner join client_particulier on client.id_client = client_particulier.id_client WHERE client.adresse_electronique = ? OR client_particulier.CIN = ?";
        String insertClientQuery = "INSERT INTO client (type_client, adresse_postale, adresse_electronique, telephone) VALUES (?, ?, ?, ?)";
        String insertClientparticulierQuery = "INSERT INTO client_particulier (id_client,CIN, nom,prenom) VALUES (?, ?, ?, ?)";

        try (

                PreparedStatement selectStmt = con.prepareStatement(selectClientQuery);
                PreparedStatement insertClientStmt = con.prepareStatement(insertClientQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                PreparedStatement insertparticulierStmt = con.prepareStatement(insertClientparticulierQuery);
        ) {
            // Check if the client already exists
            selectStmt.setString(1, mail.getText());
            selectStmt.setString(2, id.getText());
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                // Client already exists, show an error message
                Alert alert = new Alert(Alert.AlertType.NONE, "Le client existe déjà dans la base de données.", ButtonType.OK);
                alert.setTitle("Erreur");
                alert.showAndWait();
                return;
            }

            // Set parameter values for the first SQL query
            insertClientStmt.setString(1, "Particulier");
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
            insertparticulierStmt.setInt(1, clientId);
            insertparticulierStmt.setString(2, id.getText());
            insertparticulierStmt.setString(3, nom.getText());
            insertparticulierStmt.setString(4, prenom.getText());

            // Execute the second SQL query
            int numRowsAffected2 = insertparticulierStmt.executeUpdate();
            System.out.println(numRowsAffected2 + " row(s) inserted successfully!");
            Alert alert = new Alert(Alert.AlertType.NONE,"Client particulier ajouté avec succès !" , ButtonType.OK);
            alert.setTitle("Succès");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"L'insertion a échoué: " + e.getMessage(), ButtonType.OK);
            alert.setTitle("Erreur");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    private boolean isValidCIN(String cin) {
        // Regular expression to validate CIN numbers
        String cinRegex = "\\d{8}";
        Pattern pattern = Pattern.compile(cinRegex);
        return pattern.matcher(cin).matches();
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to validate phone numbers
        String phoneRegex = "^(\\+216|00\\s?216|0)?\\s?[2-9]\\d{7}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }
    private boolean isValidNom(String nom) {
        String regex = "^[A-Z][a-zA-Z]*$";
        return nom.matches(regex);
    }
    private boolean isValidPrenom(String prenom) {
        String regex = "^[A-Z][a-zA-Z]*$";
        return prenom.matches(regex);
    }
    private boolean isValidEmail(String email) {
        // Regular expression to validate email addresses
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    private boolean isValidAdressePostale(String adresse) {
        String regex = "^(\\d+ [A-Za-z\\s\\-'']+), ([A-Za-z\\s\\-'']+), ([A-Za-z\\s\\-'']+) (\\d{5})$";
        return adresse.matches(regex);
    }

}

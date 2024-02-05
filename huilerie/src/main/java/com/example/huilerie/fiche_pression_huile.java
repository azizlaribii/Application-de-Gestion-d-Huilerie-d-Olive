package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
public class fiche_pression_huile implements Initializable {

    @FXML
    private TextField ID;

    @FXML
    private Button creer;

    @FXML
    private ImageView home;

    @FXML
    private TextField quantité;
    Connection con ;
    public void initialize(URL location, ResourceBundle resources) {
        con = com.example.huilerie.DB.DBconnection.bdConnection();
         home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }

    methode m = new methode();
    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_resp_huilerie.fxml", event);
    }
    @FXML
    void creer(ActionEvent event) {
        // Get the ID and quantity values from the input fields
        String idString = ID.getText();
        String quantiteString = quantité.getText();

        // Check if the input values are valid integers
        if (!idString.matches("\\d+") || !quantiteString.matches("\\d+")) {
            // Display an error alert if the input values are not valid integers
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Valeur invalide");
            alert.setContentText("Les valeurs de l'ID et de la quantité doivent être des entiers.");
            alert.showAndWait();
            return;
        }

        int id = Integer.parseInt(idString);
        int quantiteValue = Integer.parseInt(quantiteString);

        try {
            // Create a PreparedStatement to update the "quantite" value for the record with the given ID
            String updateQuery = "UPDATE demande_pression SET quantite = ? WHERE code_demande = ?";
            PreparedStatement pstmt = con.prepareStatement(updateQuery);
            pstmt.setInt(1, quantiteValue);
            pstmt.setInt(2, id);

            // Execute the update query
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Display a success message if the update was successful
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Quantité est ajoutée avec succès");
                //alert.setContentText("La quantité a été modifiée avec succès pour l'enregistrement avec l'ID " + id + ".");
                alert.showAndWait();
            } else {
                // Display an error message if the update failed
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Impossible d'ajouter' la quantité");
                //alert.setHeaderText("Impossible d'ajouter' la quantité");
                //alert.setContentText("Impossible de modifier la quantité pour l'enregistrement avec l'ID " + id + ".");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions that may occur
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de base de données");
            alert.setContentText("Une erreur s'est produite lors de la mise à jour de la quantité. Veuillez réessayer plus tard.");
            alert.showAndWait();
        }
    }

}
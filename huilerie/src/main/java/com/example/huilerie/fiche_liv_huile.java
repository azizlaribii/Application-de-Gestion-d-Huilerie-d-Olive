package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class fiche_liv_huile implements Initializable {


    Connection con;
    @FXML
    private TextField demande;

    @FXML
    private ImageView home;


    @FXML
    private DatePicker livraison;

    @FXML
    private TextField quantité;


    public void initialize(URL location, ResourceBundle resources) {
        con = com.example.huilerie.DB.DBconnection.bdConnection();
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }
    @FXML
    void creer(ActionEvent event) {
        if (!(demande.getText().isEmpty()) && !(quantité.getText().isEmpty())  && (livraison.getValue() != null)) {
            String query = "insert into fiche_livraison(id_demande_pression,date_livraison,quantite) values(?,?,?);";
            PreparedStatement pst;
            try {
                String q = "select demande_pression.code_demande from demande_pression where code_demande = ?;";
                pst = con.prepareStatement(q);
                pst.setString(1,demande.getText());
                ResultSet rs = pst.executeQuery();
                String id_a = rs.next() ? rs.getString(1) : null;

                if (id_a == null) {
                    throw new IllegalArgumentException("Aucune demande pression pour l'identifiant donné");
                }
                pst = con.prepareStatement(query);
                pst.setString(1, demande.getText());
                pst.setString(2, livraison.getValue().toString());
                pst.setString(3, quantité.getText());


                pst.executeUpdate();
                // Show a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Fiche livraison créée avec succès");
                alert.showAndWait();
            }
            catch (SQLException | IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez Remplir Tous Les Champs");
            alert.showAndWait();
        }
    }
    methode m = new methode();
    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_resp_huilerie.fxml", event);
    }
}
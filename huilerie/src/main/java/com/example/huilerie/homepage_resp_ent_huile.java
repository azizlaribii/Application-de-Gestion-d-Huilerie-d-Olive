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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class homepage_resp_ent_huile implements Initializable {

    @FXML
    private TextField idc;

    @FXML
    private DatePicker achat;

    @FXML
    private Button creer;

    @FXML
    private ImageView home;

    @FXML
    private TextField prix;

    @FXML
    private TextField quantite;

    private Connection con;

    @FXML
    private TextField quantite1;

    methode m = new methode();
    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_agent.fxml", event);
    }

    public void initialize(URL location, ResourceBundle resources) {
        con = com.example.huilerie.DB.DBconnection.bdConnection();
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }

    @FXML
    void creer(ActionEvent event) {
        //!(ID.getText().isEmpty()) &&
        if (!(quantite.getText().isEmpty()) && !(idc.getText().isEmpty()) && !(prix.getText().isEmpty()) && (achat.getValue() != null)) {
            String query = "insert into vente(id_client,date_vente,quantite_litre,prix_unitaire,provenance) values(?,?,?,?,'olive acheté');";
            PreparedStatement pst;
            try {
                String q = "\n" +
                        "select client.id_client from client where id_client=?;";
                pst = con.prepareStatement(q);
                pst.setString(1,idc.getText());
                ResultSet rs = pst.executeQuery();
                String id_a = rs.next() ? rs.getString(1) : null;

                if (id_a == null) {
                    throw new IllegalArgumentException("Aucun Client Trouver pour l'Identifiant de Demande Donné");
                }
                pst = con.prepareStatement(query);
                pst.setString(1, idc.getText());
                //pst.setString(3, frais.getText());
                pst.setString(2, achat.getValue().toString());
                pst.setString(3, quantite.getText());
                pst.setString(4, prix.getText());
                pst.executeUpdate();
                // Show a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Fiche de d'Achat créée avec succès");
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
}

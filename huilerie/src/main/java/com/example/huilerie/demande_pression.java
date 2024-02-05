package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class demande_pression implements Initializable {
    private Connection con;
    @FXML
    private TextField ID;

    @FXML
    private Button creer;

    @FXML
    private Button entrepot;


    @FXML
    private ImageView home;

    @FXML
    private DatePicker pression;

    @FXML
    private TextField quantité;

    @FXML
    private DatePicker reception;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = com.example.huilerie.DB.DBconnection.bdConnection();
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }
    methode m = new methode();
    @FXML
    void creer(ActionEvent event) {


        if (!(ID.getText().isEmpty()) && !(quantité.getText().isEmpty())  && (reception.getValue() != null) && (pression.getValue() != null)) {
            String query = "insert into demande_pression(id_agriculteur,date_reception,date_pression,quantite_en_kg,quantite) values(?,?,?,?,0);";
            PreparedStatement pst;
            try {
                String q = "select agriculteur.code_agriculteur from agriculteur where code_agriculteur = ?;";
                pst = con.prepareStatement(q);
                pst.setString(1,ID.getText());
                ResultSet rs = pst.executeQuery();
                String id_a = rs.next() ? rs.getString(1) : null;

                if (id_a == null) {
                    throw new IllegalArgumentException("Aucun agriculteur trouvé pour l'identifiant de demande donné");
                }
                pst = con.prepareStatement(query);
                pst.setString(1, ID.getText());
                //pst.setString(3, frais.getText());
                pst.setString(2, reception.getValue().toString());
                pst.setString(3, pression.getValue().toString());
                pst.setString(4, quantité.getText());
                pst.executeUpdate();
                // Show a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Demande Pression créée avec succès");
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
    @FXML
    void entrepot(ActionEvent event) throws IOException {
        m.SwitchScene1("entrepot_olive.fxml", event);
    }
    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_respOLIVE.fxml", event);
    }
}
package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
import java.util.EventObject;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class achat_olive implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField ID;

    @FXML
    private DatePicker achat;

    @FXML
    private Button creer;

    @FXML
    private Button entrepot;

    @FXML
    private ImageView home;

    @FXML
    private TextField prix;

    private Connection con;

    @FXML
    private TextField quantite;
    public void initialize(URL location, ResourceBundle resources) {
        con = com.example.huilerie.DB.DBconnection.bdConnection();
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }
    methode m = new methode();

    @FXML
    void entrepot(ActionEvent event) throws IOException {
        m.SwitchScene1("entrepot_olive.fxml", event);
    }
    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_respOLIVE.fxml", event);
    }
    @FXML
    void creer(ActionEvent event) {
        if (!(ID.getText().isEmpty()) && !(quantite.getText().isEmpty()) && !(prix.getText().isEmpty()) && (achat.getValue() != null)) {
            String query = "insert into fiche_achat_olive(id_agriculteur,date_achat,prix_unitaire_kg,quantite_achetee_en_kg) values(?,?,?,?);";
            PreparedStatement pst;
            try {
                String q = "select agriculteur.code_agriculteur from agriculteur where code_agriculteur = ?;";
                pst = con.prepareStatement(q);
                pst.setString(1,ID.getText());
                ResultSet rs = pst.executeQuery();
                String id_a = rs.next() ? rs.getString(1) : null;

                if (id_a == null) {
                    throw new IllegalArgumentException("Aucun Agriculteur Trouvé Pour l'Identifiant de Demande Donné");
                }
                if (!isValidID(ID.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez entrer un id valide.");
                    alert.showAndWait();
                    return;
                }

                pst = con.prepareStatement(query);
                pst.setString(1, ID.getText());
                //pst.setString(3, frais.getText());
                pst.setString(2, achat.getValue().toString());
                pst.setString(3, prix.getText());
                pst.setString(4, quantite.getText());
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
    private boolean isValidID(String ID) {
        String IDRegex = "\\d{8}";
        Pattern pattern = Pattern.compile(IDRegex);
        return pattern.matcher(ID).matches();
    }

}

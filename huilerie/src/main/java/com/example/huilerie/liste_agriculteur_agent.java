package com.example.huilerie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.agriculteurs;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class liste_agriculteur_agent implements Initializable {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private TableColumn<agriculteurs, String> Adresse;

    @FXML
    private TableColumn<agriculteurs, String> Code;

    @FXML
    private TableColumn<agriculteurs, String> Nom;

    @FXML
    private TableColumn<agriculteurs, String> Prénom;

    @FXML
    private TableColumn<agriculteurs, String> Teléphone;

    @FXML
    private TableView<agriculteurs> agriculteurs;
    private ObservableList<agriculteurs> data;

    @FXML
    private ImageView home;

    @FXML
    private Button liste_agri;

    @FXML
    private Button liste_client;
    public void initialize(URL location, ResourceBundle resources) {
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
        data = FXCollections.observableArrayList();
        con = com.example.huilerie.DB.DBconnection.bdConnection();
        load();

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
    void load ()
    {
        Code.setCellValueFactory(new PropertyValueFactory<>("Code"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Prénom.setCellValueFactory(new PropertyValueFactory<>("Prénom"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        Teléphone.setCellValueFactory(new PropertyValueFactory<>("Teléphone"));

        String query ="select * from agriculteur";

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new agriculteurs(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)

                ));

            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        agriculteurs.setItems(data);
    }


}

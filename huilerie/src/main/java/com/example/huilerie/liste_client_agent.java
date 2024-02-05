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
import javafx.scene.text.Text;
import models.clients;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class liste_client_agent implements Initializable {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private Text clients;
    @FXML
    private TableColumn<clients, String> Adresse;

    @FXML
    private TableColumn<clients, String> Code;

    @FXML
    private TableColumn<clients, String> Email;

    @FXML
    private TableColumn<clients, String> Teléphone;

    @FXML
    private TableColumn<clients, String> Type;
    @FXML
    private TableView<clients> clientss;
    private ObservableList<clients> data;

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
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Teléphone.setCellValueFactory(new PropertyValueFactory<>("Teléphone"));


        String query ="select * from client";

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new clients(
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
        clientss.setItems(data);
    }

}

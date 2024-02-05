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
import models.entrepot;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class entrepot_olive implements Initializable {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private TableColumn<entrepot, String> ID;

    @FXML
    private TableColumn<entrepot, String> Source;

    @FXML
    private TableView<entrepot> entrepot;

    @FXML
    private TableColumn<entrepot, String> fiche_achat;
    private ObservableList<entrepot> data;

    @FXML
    private ImageView home;

    @FXML
    private TableColumn<entrepot, String> quantité1;

    @FXML
    private TableColumn<entrepot, String>quantité2;

    public void initialize(URL location, ResourceBundle resources) {
        home.setImage(new Image ("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images  \\logo.png"));
        data = FXCollections.observableArrayList();
        con = com.example.huilerie.DB.DBconnection.bdConnection();
        load();
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
    void load(){
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        fiche_achat.setCellValueFactory(new PropertyValueFactory<>("fiche_achat"));
        quantité1.setCellValueFactory(new PropertyValueFactory<>("quantité1"));
        quantité2.setCellValueFactory(new PropertyValueFactory<>("quantité2"));
        Source.setCellValueFactory(new PropertyValueFactory<>("Source"));
        String query ="select * from entrepot_olive";

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new entrepot(
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
        entrepot.setItems(data);
    }


}

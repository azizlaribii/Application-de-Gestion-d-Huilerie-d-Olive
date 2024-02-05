package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LOGIN implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane LOG;
    Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private Pane box;




    @FXML
    private PasswordField psw;
    @FXML
    private ImageView home;

    @FXML
    private TextField username;
    public void initialize(URL location, ResourceBundle resources) {
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
        con = com.example.huilerie.DB.DBconnection.bdConnection();
    }
    @FXML
    void connexion(ActionEvent event) throws SQLException {
        if(!(username.getText().isEmpty()) && !(psw.getText().isEmpty()))
        {
            String query = "SELECT * FROM workers WHERE code_agent = ? AND mot_de_pass = ? ";
            String destination ="";
            try
            {
                pst = con.prepareStatement(query);
                pst.setString(1, username.getText());
                pst.setString(2, psw.getText());
                rs = pst.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(rs.next()) {
                int type = rs.getInt("type");
                //System.out.println(type);
                if (type == 1)
                {
                    destination ="homepage_agent.fxml" ;
                }
                if (type == 2)
                {
                    destination = "homepage_respOLIVE.fxml";
                }
                if (type == 3)
                {
                    destination = "homepage_resp_huilerie.fxml";
                }
                if (type == 4)
                {
                    destination = "homepage_resp_ent_huile.fxml";
                }
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                stage.close();
                try{
                    root = FXMLLoader.load(getClass().getResource(destination));
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.NONE, "REMPLIR TOUS LES CHAMPS!", ButtonType.OK);
            alert.setTitle("ALERT!");
            alert.showAndWait();
        }
    }

}

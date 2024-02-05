package com.example.huilerie;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homepage_resp_huilerie implements Initializable {

    @FXML
    private Pane achat_huile;

    @FXML
    private Pane demande_pression;

    @FXML
    private Pane fiche_livraison;

    @FXML
    private ImageView home;
    public void initialize(URL location, ResourceBundle resources) {
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }
    methode m = new methode();
    @FXML
    void demande_pression(MouseEvent event) throws IOException {
        m.SwitchScene2("fiche_pression_huile.fxml", event);
    }

    @FXML
    void achat_huile(MouseEvent event) throws IOException{
        m.SwitchScene2("achat_huile.fxml", event);
    }

    @FXML
    void fiche_livraison(MouseEvent event) throws IOException{
        m.SwitchScene2("fiche_liv_huile.fxml", event);
    }

}

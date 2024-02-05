package com.example.huilerie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homepage_respOLIVE implements Initializable {

    @FXML
    private Button entrepot;

    @FXML
    private ImageView home;

    @FXML
    private Pane olive;

    @FXML
    private Pane pression;
    public void initialize(URL location, ResourceBundle resources) {
        home.setImage(new Image("C:\\Users\\aziz\\Downloads\\huilerie-20230810T185746Z-001\\huilerie\\src\\main\\java\\com\\example\\huilerie\\images\\logo.png"));
    }
    methode m = new methode();
    @FXML
    void demande_pression(MouseEvent event) throws IOException {
        m.SwitchScene2("demande_pression.fxml",event);
    }

    @FXML
    void entrepot(ActionEvent event) throws IOException {
        m.SwitchScene1("entrepot_olive.fxml", event);
    }

    @FXML
    void fiche_achat(MouseEvent event) throws IOException {
        m.SwitchScene2("achat_olive.fxml", event);
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        m.SwitchScene2("homepage_respOLIVE.fxml", event);
    }

}

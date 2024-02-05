package models;

public class entrepot {
    String ID;
    String fiche_achat;
    String quantité1;
    String quantité2;
    String Source;

    public entrepot(String ID, String fiche_achat, String quantité1, String quantité2, String source) {
        this.ID = ID;
        this.fiche_achat = fiche_achat;
        this.quantité1 = quantité1;
        this.quantité2 = quantité2;
        Source = source;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFiche_achat() {
        return fiche_achat;
    }

    public void setFiche_achat(String fiche_achat) {
        this.fiche_achat = fiche_achat;
    }

    public String getQuantité1() {
        return quantité1;
    }

    public void setQuantité1(String quantité1) {
        this.quantité1 = quantité1;
    }

    public String getQuantité2() {
        return quantité2;
    }

    public void setQuantité2(String quantité2) {
        this.quantité2 = quantité2;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }
}

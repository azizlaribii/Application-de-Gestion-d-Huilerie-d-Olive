package models;

public class agriculteurs {

    private String Code;

    private String Nom;

    private String Prénom;
    private String Adresse;

    private String Teléphone;

    public agriculteurs(String code, String nom, String prénom, String adresse, String teléphone) {
        Code = code;
        Nom = nom;
        Prénom = prénom;
        Adresse = adresse;
        Teléphone = teléphone;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrénom() {
        return Prénom;
    }

    public void setPrénom(String prénom) {
        Prénom = prénom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getTeléphone() {
        return Teléphone;
    }

    public void setTeléphone(String teléphone) {
        Teléphone = teléphone;
    }
}

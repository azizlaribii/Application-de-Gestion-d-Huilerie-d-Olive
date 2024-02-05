package models;

public class clients {
    private String Code;
    private String Type;
    private String Adresse;
    private String Email;
    private String Teléphone;

    public clients(String code, String type, String adresse, String email, String teléphone) {
        Code = code;
        Type = type;
        Adresse = adresse;
        Email = email;
        Teléphone = teléphone;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTeléphone() {
        return Teléphone;
    }

    public void setTeléphone(String teléphone) {
        Teléphone = teléphone;
    }
}

package edu.upc.dsa.models;

public class Purchase {
    String idUser;
    String idGadget;

    public Purchase(){}

    public Purchase(String idUser, String idGadget) {
        this.idUser = idUser;
        this.idGadget = idGadget;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdGadget() {
        return idGadget;
    }

    public void setIdGadget(String idGadget) {
        this.idGadget = idGadget;
    }
}

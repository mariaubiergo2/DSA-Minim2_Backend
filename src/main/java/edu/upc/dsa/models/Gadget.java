package edu.upc.dsa.models;

public class Gadget {
    String idGadget;
    int cost;
    String description;
    String unityShape;

    public Gadget() {}

    public Gadget(String id, int cost, String description, String unityShape) {
        this.setIdGadget(id);
        this.setCost(cost);
        this.setDescription(description);
        this.setUnityShape(unityShape);
    }

    public String getIdGadget() {
        return idGadget;
    }

    public void setIdGadget(String idGadget) {
        this.idGadget = idGadget;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnityShape() {
        return unityShape;
    }

    public void setUnityShape(String unityShape) {
        this.unityShape = unityShape;
    }
}

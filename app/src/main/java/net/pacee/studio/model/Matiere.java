package net.pacee.studio.model;

import java.io.Serializable;


public class Matiere implements Serializable {

    private String id;
    private String nom;

    public Matiere() {
    }

    public Matiere(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Matiere{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}

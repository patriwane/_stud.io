package net.pacee.studio.model;

import java.io.Serializable;

public class Interro implements Serializable {

    private String id;
    private String nom;
    private Object note;

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

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Interro{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", note=" + note +
                '}';
    }
}
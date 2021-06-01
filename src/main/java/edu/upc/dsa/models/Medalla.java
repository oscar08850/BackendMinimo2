package edu.upc.dsa.models;

import javax.swing.*;

public class Medalla {

    String medalla;
    String imagen;

    public Medalla(String medalla, String imagen) {
        this.medalla = medalla;
        this.imagen = imagen;
    }

    public Medalla() {
    }
    public String getMedalla() {
        return medalla;
    }

    public void setMedalla(String medalla) {
        this.medalla = medalla;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }



}
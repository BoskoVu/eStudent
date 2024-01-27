/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Bosko
 */
public class Predmet implements Serializable{
    private int predmetID;
    private int ESPB;
    private String naziv;
    private Katedra katedra;

    public Predmet() {
    }

    public Predmet(int predmetID, int ESPB, String naziv, Katedra katedra) {
        this.predmetID = predmetID;
        this.ESPB = ESPB;
        this.naziv = naziv;
        this.katedra = katedra;
    }

    public Katedra getKatedra() {
        return katedra;
    }

    public void setKatedra(Katedra katedra) {
        this.katedra = katedra;
    }

    public int getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(int predmetID) {
        this.predmetID = predmetID;
    }

    public int getESPB() {
        return ESPB;
    }

    public void setESPB(int ESPB) {
        this.ESPB = ESPB;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    
    
    
    
}

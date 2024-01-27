/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bosko
 */
public class Katedra implements Serializable{
    private int katedraID;
    private String naziv;

    public Katedra() {
    }

    public Katedra(int katedraID, String naziv) {
        this.katedraID = katedraID;
        this.naziv = naziv;
    }
    
    

    public int getKatedraID() {
        return katedraID;
    }

    public void setKatedraID(int katedraID) {
        this.katedraID = katedraID;
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

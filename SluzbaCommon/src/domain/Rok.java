/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Bosko
 */
public class Rok implements Serializable{
    private int rokID;
    private String naziv;
    private Date pocetak;
    private Date zavrsetak;

    public Rok() {
    }

    public Rok(int rokID, String naziv, Date pocetak, Date zavrsetak) {
        this.rokID = rokID;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.zavrsetak = zavrsetak;
    }

    public Date getZavrsetak() {
        return zavrsetak;
    }

    public void setZavrsetak(Date zavrsetak) {
        this.zavrsetak = zavrsetak;
    }

    public int getRokID() {
        return rokID;
    }

    public void setRokID(int rokID) {
        this.rokID = rokID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    
}

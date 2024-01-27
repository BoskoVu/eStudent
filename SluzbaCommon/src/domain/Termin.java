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
public class Termin implements Serializable{
    private int terminID;
    private Predmet predmet;
    private Rok rok;
    private Date datum;

    public Termin() {
    }

    public Termin(int terminID, Predmet predmet, Rok rok, Date datum) {
        this.terminID = terminID;
        this.predmet = predmet;
        this.rok = rok;
        this.datum = datum;
    }

    public int getTerminID() {
        return terminID;
    }

    public void setTerminID(int terminID) {
        this.terminID = terminID;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Rok getRok() {
        return rok;
    }

    public void setRok(Rok rok) {
        this.rok = rok;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    
    
    
}

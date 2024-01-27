/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Bosko
 */
public class Polaganje implements Serializable{
    private Student student;
    private Termin termin;
    private int ocena;
    private int bodovi;

    public Polaganje() {
    }

    public Polaganje(Student student, Termin termin, int ocena, int bodovi) {
        this.student = student;
        this.termin = termin;
        this.ocena = ocena;
        this.bodovi = bodovi;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    
    
    
    
}

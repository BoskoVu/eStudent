/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tranfer;

import domain.Polaganje;
import domain.Student;
import domain.Zaposleni;
import domain.Rok;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bosko
 */
public class Response implements Serializable{
    private boolean uspesno;
    private Student student;
    private Zaposleni zaposleni;
    private int operation;
    private ArrayList<Polaganje> listaPolaganja;
    private ArrayList<Rok> listaRokova;
    private Object parametar;
    private ArrayList<Student> listaStudenata;
    private ArrayList<Zaposleni> listaZaposlenih;
    private String poruka;

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public ArrayList<Polaganje> getListaPolaganja() {
        return listaPolaganja;
    }

    public void setListaPolaganja(ArrayList<Polaganje> listaPolaganja) {
        this.listaPolaganja = listaPolaganja;
    }

    public ArrayList<Rok> getListaRokova() {
        return listaRokova;
    }

    public void setListaRokova(ArrayList<Rok> listaRokova) {
        this.listaRokova = listaRokova;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public ArrayList<Student> getListaStudenata() {
        return listaStudenata;
    }

    public void setListaStudenata(ArrayList<Student> listaStudenata) {
        this.listaStudenata = listaStudenata;
    }

    public ArrayList<Zaposleni> getListaZaposlenih() {
        return listaZaposlenih;
    }

    public void setListaZaposlenih(ArrayList<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    

    
    
}

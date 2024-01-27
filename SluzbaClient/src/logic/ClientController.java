/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import dialogs.DlgIzmeniPredmet;
import dialogs.DlgIzmeniStudenta;
import dialogs.DlgIzmeniZaposlenog;
import domain.Katedra;
import domain.Polaganje;
import domain.Predmet;
import domain.Rok;
import domain.Student;
import domain.Zaposleni;
import forms.DlgDodajPredmet;
import forms.DlgDodajStudenta;
import forms.DlgDodajZaposlenog;
import forms.FrmAdministrator;
import forms.FrmLogin;
import forms.FrmRok;
import forms.FrmStudent;
import forms.FrmZaposleni;
import java.util.ArrayList;
import tranfer.Response;

/**
 *
 * @author Bosko
 */
public class ClientController {
    private static ClientController instance;
    private FrmLogin fl;
    private FrmStudent fs;
    private Rok izabraniRok;
    private FrmRok fr;
    private FrmZaposleni fz;
    private Student prijavljeniStudent;
    private Zaposleni prijavljeniZaposleni;
    private DlgDodajZaposlenog ddz;
    private DlgDodajStudenta dds;
    private DlgDodajPredmet ddp;
    private FrmAdministrator fa;
    private Student izmeniStudent;
    private Zaposleni izmeniZaposleni;
    private Predmet izmeniPredmet;
    private DlgIzmeniZaposlenog diz;
    private DlgIzmeniStudenta dis;
    private DlgIzmeniPredmet dip;
    private ArrayList<Katedra> listaKatedra;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if(instance==null){
            instance=new ClientController();
        }
        return instance;
    }

    public void logStudent(Student student) {
        fl.logStudent(student);
    }

    public FrmLogin getFl() {
        return fl;
    }

    public void setFl(FrmLogin fl) {
        this.fl = fl;
    }

    public void logZaposleni(Zaposleni zaposleni) {
        fl.logZaposleni(zaposleni);
    }

    public void postaviTabelePolaganja(ArrayList<Polaganje> listaPolaganja) {
        if(fs!=null){
            fs.postaviTabelePolaganja(listaPolaganja);
        }
        if(fz!=null){
            fz.postaviTabelePolaganja(listaPolaganja);
        }
    }

    public FrmStudent getFs() {
        return fs;
    }

    public void setFs(FrmStudent fs) {
        this.fs = fs;
    }

    public void postaviCBRokova(ArrayList<Rok> listaRokova, Student s) {
        if(fz==null){
            fs.postaviCBRokova(listaRokova);
        }else{
            fz.postaviCBRokova(listaRokova);
        }
    }

    public Rok getIzabraniRok() {
        return izabraniRok;
    }

    public void setIzabraniRok(Rok izabraniRok) {
        this.izabraniRok = izabraniRok;
    }

    public void postaviTabeluTermina(Object parametar) {
        fr.postaviTabeluTermina(parametar);
    }

    public FrmRok getFr() {
        return fr;
    }

    public void setFr(FrmRok fr) {
        this.fr = fr;
    }

    public Student getPrijavljeniStudent() {
        return prijavljeniStudent;
    }

    public void setPrijavljeniStudent(Student prijavljeniStudent) {
        this.prijavljeniStudent = prijavljeniStudent;
    }

    public void prijaviPolaganje(boolean uspesno) {
        fr.prijaviPolaganje(uspesno);
    }

    public Zaposleni getPrijavljeniZaposleni() {
        return prijavljeniZaposleni;
    }

    public void setPrijavljeniZaposleni(Zaposleni prijavljeniZaposleni) {
        this.prijavljeniZaposleni = prijavljeniZaposleni;
    }

    public FrmZaposleni getFz() {
        return fz;
    }

    public void setFz(FrmZaposleni fz) {
        this.fz = fz;
    }

    public void uneteOcene(boolean uspesno) {
        fz.uneteOcene(uspesno);
    }

    public void unesiCB(ArrayList<Predmet> parametar) {
        if(fz==null){
            fa.unesiPredmete(parametar);
        }else{
            fz.unesiCB(parametar);
        }
    }

    public void odjaviSe(String parametar) {
        if(parametar.equals("Student")){
            fs.odjaviSe();
        }else if(parametar.equals("Administrator")){
            fa.odjaviSe();
        }else{
            fz.odjaviSe();
        }
    }

    public void vratiKatedre(ArrayList<Katedra> parametar) {
        if(ddz==null && diz==null){
            ddp.popuniCB(parametar);
        }else if(diz==null){
            ddz.popuniCB(parametar);
        }else{
            diz.popuniCB(parametar);
        }
    }

    public DlgDodajZaposlenog getDdz() {
        return ddz;
    }

    public void setDdz(DlgDodajZaposlenog ddz) {
        this.ddz = ddz;
    }

    public void dodajZaposlenog(boolean uspesno) {
        ddz.dodajZaposlenog(uspesno);
    }

    public void dodajStudenta(boolean uspesno) {
        if(dds==null){
            dis.izmeniStudenta(uspesno);
        }else{
            dds.dodajStudenta(uspesno);
        }
    }

    public DlgDodajStudenta getDds() {
        return dds;
    }

    public void setDds(DlgDodajStudenta dds) {
        this.dds = dds;
    }

    public DlgDodajPredmet getDdp() {
        return ddp;
    }

    public void setDdp(DlgDodajPredmet ddp) {
        this.ddp = ddp;
    }

    public void dodajPredmet(boolean uspesno) {
        ddp.dodajPredmet(uspesno);
    }

    public void srediSveCB(Response res) {
        fa.srediSveCB(res);
    }

    public FrmAdministrator getFa() {
        return fa;
    }

    public void setFa(FrmAdministrator fa) {
        this.fa = fa;
    }

    public Student getIzmeniStudent() {
        return izmeniStudent;
    }

    public void setIzmeniStudent(Student izmeniStudent) {
        this.izmeniStudent = izmeniStudent;
    }

    public Zaposleni getIzmeniZaposleni() {
        return izmeniZaposleni;
    }

    public void setIzmeniZaposleni(Zaposleni izmeniZaposleni) {
        this.izmeniZaposleni = izmeniZaposleni;
    }

    public DlgIzmeniZaposlenog getDiz() {
        return diz;
    }

    public void setDiz(DlgIzmeniZaposlenog diz) {
        this.diz = diz;
    }

    public void izmeniZaposlenog(boolean uspesno) {
        diz.izmeniZaposlenog(uspesno);
    }

    public DlgIzmeniStudenta getDis() {
        return dis;
    }

    public void setDis(DlgIzmeniStudenta dis) {
        this.dis = dis;
    }

    public void izmeniStudenta(boolean uspesno) {
        dis.izmeniStudenta(uspesno);
    }

    public Predmet getIzmeniPredmet() {
        return izmeniPredmet;
    }

    public void setIzmeniPredmet(Predmet izmeniPredmet) {
        this.izmeniPredmet = izmeniPredmet;
    }

    public DlgIzmeniPredmet getDip() {
        return dip;
    }

    public void setDip(DlgIzmeniPredmet dip) {
        this.dip = dip;
    }

    public ArrayList<Katedra> getListaKatedra() {
        return listaKatedra;
    }

    public void setListaKatedra(ArrayList<Katedra> listaKatedra) {
        this.listaKatedra = listaKatedra;
    }

    public void izmeniPredmet(boolean uspesno) {
        dip.izmeniPredmet(uspesno);
    }

    public void obrisano(boolean uspesno) {
        fa.obrisano(uspesno);
    }
}

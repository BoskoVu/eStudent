/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import consts.Operations;
import database.DBBroker;
import domain.Katedra;
import domain.Polaganje;
import domain.Predmet;
import domain.Rok;
import domain.Student;
import domain.Termin;
import domain.Zaposleni;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tranfer.Response;

/**
 *
 * @author Bosko
 */
public class Controller {
    private static Controller instance;
    private ArrayList<Socket> listaSoket;
    private ArrayList<Student> ulogovaniStudenti;
    private ArrayList<Zaposleni> ulogovaniZaposleni;
    

    private Controller() {
        listaSoket=new ArrayList<>();
        ulogovaniStudenti=new ArrayList<>();
        ulogovaniZaposleni=new ArrayList<>();
    }

    public static Controller getInstance() {
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }

    public ArrayList<Socket> getListaSoket() {
        return listaSoket;
    }

    public void setListaSoket(ArrayList<Socket> listaSoket) {
        this.listaSoket = listaSoket;
    }

    public Response logging(Student student) {
        ArrayList<Student> lista=new ArrayList<>();
        DBBroker broker=new DBBroker();
        Response res=new Response();
        boolean b=false;
        broker.connectToDatabase();
        try {
            lista=broker.getAllStudents();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        broker.closeConnectio();
        
        for(Student s:lista){
            if(student.getUsername().equals(s.getUsername())&&student.getPassword().equals(s.getPassword())){
                ulogovaniStudenti.add(s);
                res.setStudent(s);
                b=true;
            }
        }
        res.setUspesno(b);
        return res;
    }
    
    public Response logging(Zaposleni zaposleni) {
        ArrayList<Zaposleni> lista=new ArrayList<>();
        DBBroker broker=new DBBroker();
        Response res=new Response();
        boolean b=false;
        broker.connectToDatabase();
        
        try {
            lista=broker.getAllZaposleni();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        broker.closeConnectio();
        
        for(Zaposleni z:lista){
            if(zaposleni.getUsername().equals(z.getUsername())&&zaposleni.getPassword().equals(z.getPassword())){
                ulogovaniZaposleni.add(z);
                res.setZaposleni(z);
                b=true;
            }
        }
        res.setUspesno(b);
        return res;
    }

    public ArrayList<Polaganje> vratiOdcovarajucaPolaganja(Student s) {
        ArrayList<Polaganje> listaP=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        try {
            listaP=broker.vratiOdcovarajucaPolaganja(s);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        broker.closeConnectio();
        
        
        return listaP;
    }
    
    public ArrayList<Polaganje> vratiOdcovarajucaPolaganja(Zaposleni z, Predmet p) {
        ArrayList<Polaganje> listaP=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            listaP=broker.vratiOdcovarajucaPolaganja(z,p);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        broker.closeConnectio();
        
        
        return listaP;
    }

    public ArrayList<Rok> vratiOvogodisnjeRokove() {
        ArrayList<Rok> listaR=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        try {
            listaR=broker.vratiOvogodisnjeRokove();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        broker.closeConnectio();
        
        
        return listaR;
    }

    public ArrayList<Termin> vratiOdgovarajuceTermine(Rok rok) {
        ArrayList<Termin> listaT=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            listaT=broker.vratiOdgovarajuceTermine(rok);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        broker.closeConnectio();
        
        
        return listaT;
    }

    public boolean prijaviPolaganje(Object parametar) {
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        Polaganje p=(Polaganje)parametar;
        boolean b=false;
        boolean postoji=false;
        ArrayList<Polaganje> listaP=vratiOdcovarajucaPolaganja(p.getStudent());
        for(Polaganje pol:listaP){
            if(pol.getStudent().getBrIndexa().equals(p.getStudent().getBrIndexa())&&pol.getTermin().getTerminID()==p.getTermin().getTerminID()){
                postoji=true;
            }
        }
        if(!postoji){
            try {
                broker.prijaviPolaganje(p);
                broker.commit();
                b=true;
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                broker.rollback();
                b=false;
            }
        }
        
        broker.closeConnectio();
        return b;
    }

    public boolean unesiOcene(ArrayList<Polaganje> parametar) {
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        boolean b=false;
        try {
            for(Polaganje pol:parametar){
                broker.unesiOcene(pol);
            }
            broker.commit();
            b=true;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            broker.rollback();
            b=false;
        }

        
        broker.closeConnectio();
        return b;
    }

    public ArrayList<Predmet> vratiPredmeteKatedra(Katedra parametar) {
        ArrayList<Predmet> listaP=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        try {
            listaP=broker.vratiPredmeteKatedre(parametar);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        broker.closeConnectio();
        return listaP;
    }

    public String odjavi(Zaposleni zaposleni, Student student) {
        int i=0;
        int index=-1;
        String tip="";
        if(student==null){
            for(Zaposleni z:ulogovaniZaposleni){
                if(z.getZaposleniID()==zaposleni.getZaposleniID()){
                    index=i;
                    tip=zaposleni.getStatus();
                }
                i++;
            }
            ulogovaniZaposleni.remove(index);
        }else{
            for(Student s:ulogovaniStudenti){
                if(s.getBrIndexa().equals(student.getBrIndexa())){
                    index=i;
                }
                i++;
            }
            ulogovaniStudenti.remove(index);
            tip="Student";
        }
        
        return tip;
    }

    public boolean dodajTermin(Termin parametar) {
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        boolean b=false;
        try {
            broker.dodajTermin(parametar);
            broker.commit();
            b=true;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            broker.rollback();
        }
        
        broker.closeConnectio();
        return b;
    }

    public Object vratiKatedre() {
        ArrayList<Katedra> listaK=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            listaK=broker.vratiKatedre();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        broker.closeConnectio();
        return listaK;
    }

    public boolean dodajZaposlenog(Zaposleni parametar) {
        ArrayList<Zaposleni> lista=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        boolean b;
        try {
            lista=broker.getAllZaposleni();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Zaposleni z:lista){
            if(z.getUsername().equals(parametar.getUsername())){
                return false;
            }
        }
        try {
            broker.dodajZaposlenog(parametar);
            broker.commit();
            b=true;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            broker.rollback();
            b=false;
        }
            
        
        broker.closeConnectio();
        return b;
    }

    public boolean dodajStudenta(Student student) {
        ArrayList<Student> lista=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        boolean b;
        try {
            lista=broker.getAllStudents();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Student z:lista){
            if(z.getUsername().equals(student.getUsername())){
                return false;
            }
        }
        try {
            broker.dodajStudenta(student);
            broker.commit();
            b=true;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            broker.rollback();
            b=false;
        }
            
        
        broker.closeConnectio();
        return b;
    }

    public boolean dodajPredmet(Predmet predmet) {
        ArrayList<Predmet> lista=new ArrayList<>();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        boolean b;
        try {
            lista=broker.vratiPredmeteKatedre(predmet.getKatedra());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Predmet p:lista){
            if(p.getNaziv().equals(predmet.getNaziv())){
                return false;
            }
        }
        try {
            broker.dodajPredmet(predmet);
            broker.commit();
            b=true;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            broker.rollback();
            b=false;
        }
            
        
        broker.closeConnectio();
        return b;
    }

    public Response namestiSve() {
        Response r=new Response();
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        
        try {
            r.setListaStudenata(broker.getAllStudents());
            r.setListaZaposlenih(broker.getAllZaposleni());
            r.setParametar(broker.vratiKatedre());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        r.setOperation(Operations.VRATIZAPOSLJENESTUDENTEKATEDRE);
        broker.closeConnectio();
        return r;
    }

    public boolean izmeniZaposlenog(Zaposleni zaposleni) {
        boolean b;
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            broker.izmeniZaposlenog(zaposleni);
            b=true;
            broker.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            b=false;
            broker.rollback();
        }
        
        broker.closeConnectio();
        return b;
    }

    public boolean izmeniStudenta(Student student) {
        boolean b;
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            broker.izmeniStudenta(student);
            b=true;
            broker.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            b=false;
            broker.rollback();
        }
        
        broker.closeConnectio();
        return b;
    }

    public boolean izmeniPredmet(Predmet predmet) {
        boolean b;
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            broker.izmeniPredmet(predmet);
            b=true;
            broker.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            b=false;
            broker.rollback();
        }
        
        broker.closeConnectio();
        return b;
    }

    public boolean obrisiZaposlenog(Zaposleni zaposleni) {
        boolean b;
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            broker.obrisiZaposlenog(zaposleni);
            b=true;
            broker.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            b=false;
            broker.rollback();
        }
            
        broker.closeConnectio();
        return b;
    }

    public boolean obrisiStudenta(Student student) {
        boolean b;
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            broker.obrisiStudenta(student);
            b=true;
            broker.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            b=false;
            broker.rollback();
        }
            
        broker.closeConnectio();
        return b;
    }

    public boolean obrisiPredmet(Predmet predmet) {
        boolean b;
        DBBroker broker=new DBBroker();
        broker.connectToDatabase();
        
        try {
            broker.obrisiPredmet(predmet);
            b=true;
            broker.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            b=false;
            broker.rollback();
        }
            
        broker.closeConnectio();
        return b;
    }
}

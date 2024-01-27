/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import consts.Operations;
import domain.Katedra;
import domain.Polaganje;
import domain.Predmet;
import domain.Rok;
import domain.Student;
import domain.Termin;
import domain.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controller;
import tranfer.Request;
import tranfer.Response;

/**
 *
 * @author Bosko
 */
public class HandleClients extends Thread{
    Socket s;

    public HandleClients(Socket s) {
        this.s = s;
    }
    
    
    @Override
    public void run() {
        while(true){
            Request req=getRequest();
            Response res=new Response();
            switch (req.getOperation()) {
                case Operations.LOGIN:
                    if(req.getStudent()==null){
                        res = Controller.getInstance().logging(req.getZaposleni());
                    }else{
                        res = Controller.getInstance().logging(req.getStudent());
                    }
                    res.setOperation(Operations.LOGIN);
                    
                    sendResponse(res);
                    break;
                case Operations.ODJAVISE:
                    res.setParametar(Controller.getInstance().odjavi(req.getZaposleni(), req.getStudent()));
                    res.setOperation(Operations.ODJAVISE);
                    sendResponse(res);
                    break;
                    //Ukloni ga iz liste prijavljenih kad iskljuci formu
                case Operations.VRATIODGOVARAJUCAPOLAGANJA:
                    ArrayList<Polaganje> p=new ArrayList<>();
                    if(req.getStudent()==null){
                        p=Controller.getInstance().vratiOdcovarajucaPolaganja(req.getZaposleni(), (Predmet) req.getParametar());
                    }else{
                        p=Controller.getInstance().vratiOdcovarajucaPolaganja(req.getStudent());
                    }
                    res.setOperation(Operations.VRATIODGOVARAJUCAPOLAGANJA);
                    res.setListaPolaganja(p);
                    sendResponse(res);
                    break;
                case Operations.VRATIOVOGODISNJEROKOVE:
                    ArrayList<Rok> rokovi=Controller.getInstance().vratiOvogodisnjeRokove();
                    res.setListaRokova(rokovi);
                    res.setOperation(Operations.VRATIOVOGODISNJEROKOVE);
                    if(req.getStudent()==null){
                        res.setZaposleni(req.getZaposleni());
                    }else{
                        res.setStudent(req.getStudent());
                    }
                    sendResponse(res);
                    break;
    ///////////////////////////////////////////////////////CONFIG
                case Operations.VRATIODGOVARAJUCETERMINE:
                    ArrayList<Termin> termini=Controller.getInstance().vratiOdgovarajuceTermine((Rok)req.getParametar());
                    res.setParametar(termini);
                    res.setOperation(Operations.VRATIODGOVARAJUCETERMINE);
                    sendResponse(res);
                    break;
                case Operations.PRIJAVIPOLAGANJE:
                    res.setUspesno(Controller.getInstance().prijaviPolaganje(req.getParametar()));
                    res.setOperation(Operations.PRIJAVIPOLAGANJE);
                    sendResponse(res);
                    break;
                case Operations.UNESIOCENE:
                    res.setUspesno(Controller.getInstance().unesiOcene((ArrayList<Polaganje>) req.getParametar()));
                    res.setOperation(Operations.UNESIOCENE);
                    sendResponse(res);
                    break;
                case Operations.VRATIPREDMETEKATEDRE:
                    res.setParametar(Controller.getInstance().vratiPredmeteKatedra((Katedra) req.getParametar()));
                    res.setOperation(Operations.VRATIPREDMETEKATEDRE);
                    sendResponse(res);
                    break;
                case Operations.DODAJTERMIN:
                    res.setUspesno(Controller.getInstance().dodajTermin((Termin) req.getParametar()));
                    res.setOperation(Operations.DODAJTERMIN);
                    sendResponse(res);
                    break;
                case Operations.VRATIKATEDRE:
                    res.setParametar(Controller.getInstance().vratiKatedre());
                    res.setOperation(Operations.VRATIKATEDRE);
                    sendResponse(res);
                    break;
                case Operations.DODAJZAPOSLENOG:
                    res.setUspesno(Controller.getInstance().dodajZaposlenog((Zaposleni) req.getParametar()));
                    res.setOperation(Operations.DODAJZAPOSLENOG);
                    sendResponse(res);
                    break;
                case Operations.DODAJSTUDENTA:
                    res.setUspesno(Controller.getInstance().dodajStudenta((Student) req.getParametar()));
                    res.setOperation(Operations.DODAJSTUDENTA);
                    sendResponse(res);
                    break;
                case Operations.DODAJPREDMET:
                    res.setUspesno(Controller.getInstance().dodajPredmet((Predmet) req.getParametar()));
                    res.setOperation(Operations.DODAJPREDMET);
                    sendResponse(res);
                    break;
                case Operations.VRATIZAPOSLJENESTUDENTEKATEDRE:
                    res=Controller.getInstance().namestiSve();
                    sendResponse(res);
                    break;
                case Operations.IZMENIZAPOSLENOG:
                    res.setUspesno(Controller.getInstance().izmeniZaposlenog((Zaposleni)req.getParametar()));
                    res.setOperation(Operations.IZMENIZAPOSLENOG);
                    sendResponse(res);
                    break;
                case Operations.IZMENISTUDENTA:
                    res.setUspesno(Controller.getInstance().izmeniStudenta((Student)req.getParametar()));
                    res.setOperation(Operations.IZMENISTUDENTA);
                    sendResponse(res);
                    break;
                case Operations.IZMENIPREDMET:
                    res.setUspesno(Controller.getInstance().izmeniPredmet((Predmet)req.getParametar()));
                    res.setOperation(Operations.IZMENIPREDMET);
                    sendResponse(res);
                    break;
                case Operations.OBRISIZAPOSLENOG:
                    res.setUspesno(Controller.getInstance().obrisiZaposlenog((Zaposleni)req.getParametar()));
                    res.setOperation(Operations.OBRISIZAPOSLENOG);
                    sendResponse(res);
                    break;
                case Operations.OBRISISTUDENTA:
                    res.setUspesno(Controller.getInstance().obrisiStudenta((Student)req.getParametar()));
                    res.setOperation(Operations.OBRISISTUDENTA);
                    sendResponse(res);
                    break;
                case Operations.OBRISIPREDMET:
                    res.setUspesno(Controller.getInstance().obrisiPredmet((Predmet)req.getParametar()));
                    res.setOperation(Operations.OBRISIPREDMET);
                    sendResponse(res);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    public Request getRequest(){
        Request req=new Request();
        try {
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            req=(Request) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(HandleClients.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        return req;
    }
    
    public void sendResponse(Response res){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(res);
        } catch (IOException ex) {
            Logger.getLogger(HandleClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

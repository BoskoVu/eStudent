/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import consts.Operations;
import domain.Katedra;
import domain.Predmet;
import java.util.ArrayList;
import logic.ClientController;
import servercommunication.Communication;
import tranfer.Response;

/**
 *
 * @author Bosko
 */
public class HandleServer extends Thread{

    @Override
    public void run() {
        while(true){
            Response res=Communication.getInstance().getResponse();
            switch (res.getOperation()) {
                case Operations.LOGIN:
                    if(res.isUspesno()){
                        if(res.getStudent()!=null){
                            ClientController.getInstance().logStudent(res.getStudent());
                        }else{
                            ClientController.getInstance().logZaposleni(res.getZaposleni());
                        }
                    }
                    break;
                case Operations.ODJAVISE:
                    ClientController.getInstance().odjaviSe((String) res.getParametar());
                    break;
                case Operations.VRATIODGOVARAJUCAPOLAGANJA:
                    ClientController.getInstance().postaviTabelePolaganja(res.getListaPolaganja());
                    break;
                case Operations.VRATIOVOGODISNJEROKOVE:
                    ClientController.getInstance().postaviCBRokova(res.getListaRokova(),res.getStudent());
                    break;
                case Operations.VRATIODGOVARAJUCETERMINE:
                    ClientController.getInstance().postaviTabeluTermina(res.getParametar());
                    break;
                case Operations.PRIJAVIPOLAGANJE:
                    ClientController.getInstance().prijaviPolaganje(res.isUspesno());
                    break;
                case Operations.UNESIOCENE:
                    ClientController.getInstance().uneteOcene(res.isUspesno());
                    break;
                case Operations.VRATIPREDMETEKATEDRE:
                    ClientController.getInstance().unesiCB((ArrayList<Predmet>) res.getParametar());
                    break;
                case Operations.DODAJTERMIN:
                    ClientController.getInstance().uneteOcene(res.isUspesno());
                    break;
                case Operations.VRATIKATEDRE:
                    ClientController.getInstance().vratiKatedre((ArrayList<Katedra>) res.getParametar());
                    break;
                case Operations.DODAJZAPOSLENOG:
                    ClientController.getInstance().dodajZaposlenog(res.isUspesno());
                    break;
                case Operations.DODAJSTUDENTA:
                    ClientController.getInstance().dodajStudenta(res.isUspesno());
                    break;
                case Operations.DODAJPREDMET:
                    ClientController.getInstance().dodajPredmet(res.isUspesno());
                    break;
                case Operations.VRATIZAPOSLJENESTUDENTEKATEDRE:
                    ClientController.getInstance().srediSveCB(res);
                    break;
                case Operations.IZMENIZAPOSLENOG:
                    ClientController.getInstance().izmeniZaposlenog(res.isUspesno());
                    break;
                case Operations.IZMENISTUDENTA:
                    ClientController.getInstance().izmeniStudenta(res.isUspesno());
                    break;
                case Operations.IZMENIPREDMET:
                    ClientController.getInstance().izmeniPredmet(res.isUspesno());
                    break;
                case Operations.OBRISIZAPOSLENOG:
                    ClientController.getInstance().obrisano(res.isUspesno());
                    break;
                case Operations.OBRISISTUDENTA:
                    ClientController.getInstance().obrisano(res.isUspesno());
                    break;
                case Operations.OBRISIPREDMET:
                    ClientController.getInstance().obrisano(res.isUspesno());
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    
    
}

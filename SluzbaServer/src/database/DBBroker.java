/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import domain.Katedra;
import domain.Polaganje;
import domain.Predmet;
import domain.Rok;
import domain.Student;
import domain.Termin;
import domain.Zaposleni;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bosko
 */
public class DBBroker {
    Connection connection;

    public DBBroker() {
    }
    
    
    public void connectToDatabase(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentskasluzba", "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit(){
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnectio(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> lista=new ArrayList<>();
        
        String upit="Select * from student";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            String index=rs.getString("BrojIndeksa");
            String ime=rs.getString("Ime");
            String prezime=rs.getString("Prezime");
            String username=rs.getString("Username");
            String password=rs.getString("Password");
            Date datum=rs.getDate("DatumRodjenja");
            
            Student s=new Student(index, ime, prezime, username, password, datum);
            lista.add(s);
        }
        
        return lista;
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws SQLException {
        ArrayList<Zaposleni> lista=new ArrayList<>();
        
        String upit="Select * from zaposleni";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            int zaposleniID=rs.getInt("ZaposleniID");
            String ime=rs.getString("Ime");
            String prezime=rs.getString("Prezime");
            String username=rs.getString("Username");
            String password=rs.getString("Password");
            String status=rs.getString("Status");
            Date datum=rs.getDate("DatumRodjenja");
            int katedraID=rs.getInt("KatedraID");
            Katedra k=new Katedra(katedraID, "");
            Zaposleni z=new Zaposleni(zaposleniID, ime, prezime, username, password, datum, status, k);
            lista.add(z);
        }
        
        return lista;
    }

    public ArrayList<Polaganje> vratiOdcovarajucaPolaganja(Student s) throws SQLException {
        ArrayList<Polaganje> listaP=new ArrayList<>();
        
        String upit="SELECT * FROM polaganje p JOIN termin t ON t.TerminID=p.TerminID JOIN predmet pr ON pr.PredmetID=t.PredmetID JOIN katedra k ON k.KatedraID=pr.KatedraID JOIN rok r ON r.RokID=t.RokID WHERE p.BrojIndeksa=\""+s.getBrIndexa()+"\"";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            int katedraID=rs.getInt("KatedraID");
            String nazivKatedre=rs.getString("NazivKatedre");
            Katedra k=new Katedra(katedraID, nazivKatedre);
            int predmetID=rs.getInt("PredmetID");
            int ESPB=rs.getInt("ESPB");
            String nazivPredmeta=rs.getString("NazivPredmeta");
            Predmet p=new Predmet(predmetID, ESPB, nazivPredmeta, k);
            Date pocetak=rs.getDate("Pocetak");
            Date zavrsetak=rs.getDate("Zavrsetak");
            int rokID=rs.getInt("RokID");
            String nazivRoka=rs.getString("NazivRoka");
            Rok r=new Rok(rokID, nazivRoka, pocetak, zavrsetak);
            
            int terminID=rs.getInt("TerminID");
            Date datum=rs.getDate("Datum");
            Termin t=new Termin(terminID, p, r, datum);
            
            int ocena=rs.getInt("Ocena");
            int bodovi=rs.getInt("Bodovi");
            Polaganje polaganje=new Polaganje(s, t, ocena, bodovi);
            
            listaP.add(polaganje);
            
        }
        
        return listaP;
    }

    public ArrayList<Rok> vratiOvogodisnjeRokove() throws SQLException {
        ArrayList<Rok> listaR=new ArrayList<>();
        String upit="SELECT * FROM rok WHERE Pocetak>='"+Calendar.getInstance().get(Calendar.YEAR)+"-01-01' AND Pocetak<'"+Calendar.getInstance().get(Calendar.YEAR)+"-12-31'";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            int rokID=rs.getInt("RokID");
            String nazivRoka=rs.getString("NazivRoka");
            Date pocetak=rs.getDate("Pocetak");
            Date zavrsetak=rs.getDate("Zavrsetak");
            
            Rok r=new Rok(rokID, nazivRoka, pocetak, zavrsetak);
            listaR.add(r);
            
        }
        
        return listaR;
    }

    public ArrayList<Termin> vratiOdgovarajuceTermine(Rok rok) throws SQLException {
        ArrayList<Termin> listaT=new ArrayList<>();
        String upit="SELECT * FROM termin t JOIN predmet p ON t.PredmetID=p.PredmetID WHERE RokID="+rok.getRokID();
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            int predmetID=rs.getInt("PredmetID");
            int espb=rs.getInt("ESPB");
            String nazivP=rs.getString("NazivPredmeta");
            Predmet p=new Predmet(predmetID, espb, nazivP, null);
            
            int terminID=rs.getInt("TerminID");
            Date datum=rs.getDate("Datum");
            Termin t=new Termin(terminID, p, rok, datum);
            
            listaT.add(t);
        }
        
        return listaT;
    }

    public void prijaviPolaganje(Polaganje p) throws SQLException {
        String upit="Insert into polaganje(BrojIndeksa, TerminID, Ocena, Bodovi) values(?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setString(1, p.getStudent().getBrIndexa());
        ps.setInt(2, p.getTermin().getTerminID());
        ps.setInt(3, p.getOcena());
        ps.setInt(4, p.getBodovi());
        
        ps.executeUpdate();
    }

    public ArrayList<Polaganje> vratiOdcovarajucaPolaganja(Zaposleni z, Predmet pr) throws SQLException {
        ArrayList<Polaganje> listaP=new ArrayList<>();
        
        String upit="SELECT * FROM polaganje p JOIN termin t ON t.TerminID=p.TerminID JOIN predmet pr ON pr.PredmetID=t.PredmetID "
                + "JOIN katedra k ON k.KatedraID=pr.KatedraID JOIN rok r ON r.RokID=t.RokID JOIN student s ON s.BrojIndeksa=p.BrojIndeksa "
                + "WHERE pr.KatedraID="+z.getKatedra().getKatedraID()+" AND t.PredmetID="+pr.getPredmetID();
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            int katedraID=rs.getInt("KatedraID");
            String nazivKatedre=rs.getString("NazivKatedre");
            Katedra k=new Katedra(katedraID, nazivKatedre);
            
            int predmetID=rs.getInt("PredmetID");
            int ESPB=rs.getInt("ESPB");
            String nazivPredmeta=rs.getString("NazivPredmeta");
            Predmet p=new Predmet(predmetID, ESPB, nazivPredmeta, k);
            
            Date pocetak=rs.getDate("Pocetak");
            Date zavrsetak=rs.getDate("Zavrsetak");
            int rokID=rs.getInt("RokID");
            String nazivRoka=rs.getString("NazivRoka");
            Rok r=new Rok(rokID, nazivRoka, pocetak, zavrsetak);
            
            int terminID=rs.getInt("TerminID");
            Date datum=rs.getDate("Datum");
            Termin t=new Termin(terminID, p, r, datum);
            
            String brojIndeksa=rs.getString("BrojIndeksa");
            String ime=rs.getString("Ime");
            String prezime=rs.getString("Prezime");
            Date datumRodjenja=rs.getDate("DatumRodjenja");
            String username=rs.getString("Username");
            String password=rs.getString("Password");
            Student s=new Student(brojIndeksa, ime, prezime, username, password, datumRodjenja);
            
            int ocena=rs.getInt("Ocena");
            int bodovi=rs.getInt("Bodovi");
            Polaganje polaganje=new Polaganje(s, t, ocena, bodovi);
            
            listaP.add(polaganje);
    }
    
    return listaP;
    }

    public void unesiOcene(Polaganje p) throws SQLException {
        String upit="UPDATE polaganje SET BrojIndeksa=?, TerminID=?, Ocena=?, Bodovi=? "
                + "where BrojIndeksa='"+p.getStudent().getBrIndexa()+"' and TerminID="+p.getTermin().getTerminID();
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setString(1, p.getStudent().getBrIndexa());
        ps.setInt(2, p.getTermin().getTerminID());
        ps.setInt(3, p.getOcena());
        ps.setInt(4, p.getBodovi());
        
        ps.executeUpdate();
    }

    public ArrayList<Predmet> vratiPredmeteKatedre(Katedra parametar) throws SQLException {
        ArrayList<Predmet> listaP=new ArrayList<>();
        
        String upit="SELECT * FROM predmet where KatedraID="+parametar.getKatedraID();
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            int predmetID=rs.getInt("PredmetID");
            int espb=rs.getInt("ESPB");
            String nazivP=rs.getString("NazivPredmeta");
            Predmet p=new Predmet(predmetID, espb, nazivP, parametar);
            
            
            listaP.add(p);
        }
        
        return listaP;
    }

    public void dodajTermin(Termin p) throws SQLException {
        String upit="Insert into termin(TerminID, PredmetID, RokID, Datum) values(?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setInt(1, maxTerminID());
        ps.setInt(2, p.getPredmet().getPredmetID());
        ps.setInt(3, p.getRok().getRokID());
        ps.setDate(4, new java.sql.Date(p.getDatum().getTime()));
        
        ps.executeUpdate();
    }

    private int maxTerminID() throws SQLException {
        String upit="SELECT MAX(TerminID) AS maks FROM termin";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        int maxID=-1;
        while(rs.next()){
            maxID=rs.getInt("maks");
        }
        
        return maxID+1;
    }

    public ArrayList<Katedra> vratiKatedre() throws SQLException {
        ArrayList<Katedra> listaK=new ArrayList<>();
        
        String upit="SELECT * FROM katedra";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        while(rs.next()){
            int katedraID=rs.getInt("KatedraID");
            String naziv=rs.getString("NazivKatedre");
            Katedra k=new Katedra(katedraID, naziv);
            
            listaK.add(k);
        }
        
        return listaK;
    }

    public void dodajZaposlenog(Zaposleni parametar) throws SQLException {
        String upit="Insert into zaposleni(ZaposleniID, Ime, Prezime, DatumRodjenja, Status, Username, Password, KatedraID) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setInt(1, maxZaposleniID());
        ps.setString(2, parametar.getIme());
        ps.setString(3, parametar.getPrezime());
        ps.setDate(4,  new java.sql.Date(parametar.getDatumRodjenja().getTime()));
        ps.setString(5, parametar.getStatus());
        ps.setString(6, parametar.getUsername());
        ps.setString(7, parametar.getPassword());
        ps.setInt(8, parametar.getKatedra().getKatedraID());
        
        ps.executeUpdate();
    }

    private int maxZaposleniID() throws SQLException {
        String upit="SELECT MAX(ZaposleniID) AS maks FROM zaposleni";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        int maxID=-1;
        while(rs.next()){
            maxID=rs.getInt("maks");
        }
        
        return maxID+1;
    }

    public void dodajStudenta(Student parametar) throws SQLException {
        String upit="Insert into student(BrojIndeksa, Ime, Prezime, DatumRodjenja, Username, Password) values(?,?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setString(1, parametar.getBrIndexa());
        ps.setString(2, parametar.getIme());
        ps.setString(3, parametar.getPrezime());
        ps.setDate(4,  new java.sql.Date(parametar.getDatumRodjenja().getTime()));
        ps.setString(5, parametar.getUsername());
        ps.setString(6, parametar.getPassword());
        
        ps.executeUpdate();
    }

    public void dodajPredmet(Predmet parametar) throws SQLException {
        String upit="Insert into predmet(PredmetID, ESPB, NazivPredmeta, KatedraID) values(?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setInt(1, maxPredmetID());
        ps.setInt(2, parametar.getESPB());
        ps.setString(3, parametar.getNaziv());
        ps.setInt(4,  parametar.getKatedra().getKatedraID());
        
        ps.executeUpdate();
    }

    private int maxPredmetID() throws SQLException {
        String upit="SELECT MAX(PredmetID) AS maks FROM predmet";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        int maxID=-1;
        while(rs.next()){
            maxID=rs.getInt("maks");
        }
        
        return maxID+1;
    }

    public void izmeniZaposlenog(Zaposleni zaposleni) throws SQLException {
        String upit="UPDATE zaposleni SET Ime=?, Prezime=?, DatumRodjenja=?, Status=?, Username=?, Password=?, KatedraID=? "
                + "where ZaposleniID="+zaposleni.getZaposleniID();
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setString(1, zaposleni.getIme());
        ps.setString(2, zaposleni.getPrezime());
        ps.setDate(3, new java.sql.Date(zaposleni.getDatumRodjenja().getTime()));
        ps.setString(4, zaposleni.getStatus());
        ps.setString(5, zaposleni.getUsername());
        ps.setString(6, zaposleni.getPassword());
        ps.setInt(7, zaposleni.getKatedra().getKatedraID());
        
        ps.executeUpdate();
    }

    public void izmeniStudenta(Student student) throws SQLException {
        String upit="UPDATE student SET Ime=?, Prezime=?, DatumRodjenja=?, Username=?, Password=? WHERE BrojIndeksa=?";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setString(1, student.getIme());
        ps.setString(2, student.getPrezime());
        ps.setDate(3, new java.sql.Date(student.getDatumRodjenja().getTime()));
        ps.setString(4, student.getUsername());
        ps.setString(5, student.getPassword());
        ps.setString(6, student.getBrIndexa());
        
        ps.executeUpdate();
    }

    public void izmeniPredmet(Predmet predmet) throws SQLException {
        String upit="UPDATE predmet SET ESPB=?, NazivPredmeta=?, KatedraID=? WHERE PredmetID=?";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setInt(1, predmet.getESPB());
        ps.setString(2, predmet.getNaziv());
        ps.setInt(3, predmet.getKatedra().getKatedraID());
        ps.setInt(4, predmet.getPredmetID());
        
        ps.executeUpdate();
    }

    public void obrisiZaposlenog(Zaposleni zaposleni) throws SQLException {
        String upit="DELETE from zaposleni where ZaposleniID=?";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setInt(1, zaposleni.getZaposleniID());
        
        
        ps.executeUpdate();
    }

    public void obrisiStudenta(Student student) throws SQLException {
        String upit="DELETE from student where BrojIndeksa=?";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setString(1, student.getBrIndexa());
        
        
        ps.executeUpdate();
    }

    public void obrisiPredmet(Predmet predmet) throws SQLException {
        String upit="DELETE from predmet where PredmetID=?";
        PreparedStatement ps=connection.prepareStatement(upit);
        
        ps.setInt(1, predmet.getPredmetID());
        
        
        ps.executeUpdate();
    }
}

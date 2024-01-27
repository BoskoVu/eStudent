/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Polaganje;
import forms.FrmZaposleni;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bosko
 */
public class ModelPolaganjaZaposleni extends AbstractTableModel{
    
    ArrayList<Polaganje> listaPolaganja=new ArrayList<>();
    SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
    
    @Override
    public int getRowCount() {
        return listaPolaganja.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Polaganje p= listaPolaganja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getStudent().getIme()+" "+p.getStudent().getPrezime();
            case 1:
                return p.getStudent().getBrIndexa();
            case 2:
                return sdf.format(p.getTermin().getDatum());
            case 3:
                return p.getBodovi();
            case 4:
                return p.getOcena();
            default:
                return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Ime i prezime";
            case 1:
                return "Indeks";
            case 2:
                return "Datum";
            case 3:
                return "Bodovi";
            case 4:
                return "Ocena";
            default:
                return "Greska";
        }
    }

    public void postaviListu(ArrayList<Polaganje> listaPolaganja) {
        this.listaPolaganja=listaPolaganja;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return true;
            default:
                return false;
        }
    }

    public ArrayList<Polaganje> getLista() {
        return listaPolaganja;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try{
            Polaganje p=listaPolaganja.get(rowIndex);
            switch (columnIndex) {
                case 3:
                    p.setBodovi((Integer.parseInt((String) aValue)));
                    break;
                case 4:
                    p.setOcena((Integer.parseInt((String) aValue)));
                    break;
                default:
                    throw new AssertionError();
            }
        }catch(Exception ex){
            Polaganje p=listaPolaganja.get(rowIndex);
            switch (columnIndex) {
                case 3:
                    p.setBodovi(p.getBodovi());
                    break;
                case 4:
                    p.setOcena(p.getOcena());
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    
}

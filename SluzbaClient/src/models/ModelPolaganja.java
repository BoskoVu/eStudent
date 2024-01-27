/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Polaganje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bosko
 */
public class ModelPolaganja extends AbstractTableModel{
    
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
                return p.getTermin().getPredmet().getNaziv();
            case 1:
                return p.getTermin().getPredmet().getESPB();
            case 2:
                return sdf.format(p.getTermin().getDatum());
            case 3:
                return p.getOcena();
            case 4:
                return p.getBodovi();
            default:
                return "Greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Naziv predmeta";
            case 1:
                return "ESPB";
            case 2:
                return "Datum";
            case 3:
                return "Ocena";
            case 4:
                return "Bodovi";
            default:
                return "Greska";
        }
    }

    public void postaviListu(ArrayList<Polaganje> listaPolaganja) {
        this.listaPolaganja=listaPolaganja;
        fireTableDataChanged();
    }
    
    
    
}

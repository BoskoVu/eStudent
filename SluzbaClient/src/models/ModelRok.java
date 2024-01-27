/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Termin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bosko
 */
public class ModelRok extends AbstractTableModel{
    
    private ArrayList<Termin> listaT=new ArrayList<>();
    SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy. hh:mm");
    
    @Override
    public int getRowCount() {
        return listaT.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Termin t=listaT.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getPredmet().getNaziv();
            case 1:
                return sdf.format(t.getDatum());
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Predmet";
            case 1:
                return "Datum i vreme";
            default:
                throw new AssertionError();
        }
    }

    public ArrayList<Termin> getListaT() {
        return listaT;
    }

    public void setListaT(ArrayList<Termin> listaT) {
        this.listaT = listaT;
    }
    
    
}

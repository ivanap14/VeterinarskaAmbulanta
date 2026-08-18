/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.sa.ambulanta.domain.*;

/**
 *
 * @author Korisnik
 */
public class TableModelLoggedInUsers extends AbstractTableModel{
    private List<Veterinarian> veterinarians;
    private String[] columnNames = {"Ulogovani korisnici"};
    private Class[] columnClass = {String.class};

    public TableModelLoggedInUsers(List<Veterinarian> veterinarians) {
        this.veterinarians=veterinarians;
    }

    @Override
    public int getRowCount() {
        if (veterinarians.isEmpty()) return 0;
        else return veterinarians.size();
        
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinarian veterinarian = veterinarians.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veterinarian.getEmail();
            default:
                return "n/a";
        }

    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    public void setVeterinarians(List<Veterinarian> veterinarians) {
        this.veterinarians = veterinarians;
        fireTableDataChanged();
    }
    
}

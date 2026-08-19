/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.model;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelIntervention extends AbstractTableModel{
    private final List<Intervention> interventions;
    private String[] columnNames = {"id intervencije", "datum", "veterinar", "životinja"};
    private Class[] columnClass = {String.class, String.class, String.class, String.class};
    
    public TableModelIntervention(List<Intervention> interventions) {
        this.interventions = interventions;
    }
    
    @Override
    public int getRowCount() {
        if (interventions == null) return 0;
        else return interventions.size();  
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Intervention intervention = interventions.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return intervention.getId();
            case 1:
                return intervention.getDate();
            case 2:
                return intervention.getVeterinarian().toString();
            case 3:
                return intervention.getAnimal().toString();   
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

    public Intervention getIntervention(int row) {
        return interventions.get(row);
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }

    public void sortInterventionsByDate() {
         interventions.sort((i1, i2) -> i2.getDate().compareTo(i1.getDate()));
         fireTableRowsUpdated(0, interventions.size()-1);
    }
}

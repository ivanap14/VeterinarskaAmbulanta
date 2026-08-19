/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.model;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelSpecialization extends AbstractTableModel{
    
    private final List<VetSpec> vetspecs;
    private String[] columnNames = {"specijalizacija", "kategorija", "godina sticanja", "institucija"};
    private Class[] columnClass = {String.class, String.class, String.class,String.class};
    
    public TableModelSpecialization(List<VetSpec> vetspecs) {
        this.vetspecs = vetspecs;
    }
    

    @Override
    public int getRowCount() {
        if (vetspecs == null) return 0;
        else return vetspecs.size();
        
    }

    @Override
    public int getColumnCount() {
        return 3;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VetSpec vs = vetspecs.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vs.getSpecialization().getName();
            case 1:
                return vs.getSpecialization().getCategory().toString();
            case 2:
                return vs.getGraduationDate().toString();
            case 3:
                return vs.getInstitution();
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


    

}

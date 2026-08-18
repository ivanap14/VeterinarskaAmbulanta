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
public class TableModelVeterinarian extends AbstractTableModel{
    
    private final List<Veterinarian> veterinarians;
    private String[] columnNames = {"id", "ime", "prezime"};
    private Class[] columnClass = {String.class, String.class, String.class};
    
    public TableModelVeterinarian(List<Veterinarian> veterinarians) {
        this.veterinarians = veterinarians;
    }
    

    @Override
    public int getRowCount() {
        if (veterinarians == null) return 0;
        else return veterinarians.size();
        
    }

    @Override
    public int getColumnCount() {
        return 3;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinarian veterinarian = veterinarians.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veterinarian.getId();
            case 1:
                return veterinarian.getFirstname();
            case 2:
                return veterinarian.getLastname();
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


    public Veterinarian getVeterinarian(int row) {
        return veterinarians.get(row);
    }

    public int getRowByVeterinarian(Veterinarian veterinarian) {
        return IntStream.range(0, veterinarians.size())
                .filter(i -> veterinarians.get(i).equals(veterinarian))
                .findFirst().orElse(-1);
    }
    

}

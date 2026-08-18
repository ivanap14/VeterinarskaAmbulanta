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
public class TableModelAnimal extends AbstractTableModel{
    private final List<Animal> animals;
    private String[] columnNames = {"id životinje", "nadimak", "vrsta", "jmbg vlasnika"};
    private Class[] columnClass = {String.class, String.class, String.class, String.class};
    
    public TableModelAnimal(List<Animal> animals) {
        this.animals = animals;
    }
    
    @Override
    public int getRowCount() {
        if (animals == null) return 0;
        else return animals.size();
        
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = animals.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return animal.getId();
            case 1:
                return animal.getName();
            case 2:
                return animal.getSpecies();
            case 3:
                return animal.getOwner().getJmbg();
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

    public Animal getAnimal(int row) {
        return animals.get(row);
    }

}

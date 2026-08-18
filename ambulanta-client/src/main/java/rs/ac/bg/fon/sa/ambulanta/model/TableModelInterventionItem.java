/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.model;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Korisnik
 */
public class TableModelInterventionItem extends AbstractTableModel{

    private final Intervention intervention;
    private final String[] columnNames = new String[]{"rb", "naziv", "cena (din)", "količina", "iznos (din)"};
    private final Class[] columnClass = {Integer.class, String.class, Double.class, Integer.class, Double.class};

    public TableModelInterventionItem(Intervention intervention) {
        this.intervention = intervention;
    }
   
    
    @Override
    public int getRowCount() {
        if(intervention.getInterventionItems().isEmpty()) return 0;
        return intervention.getInterventionItems().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column]; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InterventionItem item = intervention.getInterventionItems().get(rowIndex);
        
        switch(columnIndex) {
            
            case 0: return item.getRb();     
            
            case 1: return item.getService().getName();
            
            case 2: return item.getPrice();
            
            case 3: return item.getQuantity();
            
            case 4: return item.getAmount();
  
            default: return null;
        }        
    }
    
    public void addInterventionItem(Intervention intervention, Service service, Animal animal) throws Exception {
        
        InterventionItem item= new InterventionItem();
        item.setIntervention(intervention);
        item.setRb(intervention.getInterventionItems().size()+1);
        item.setPrice(service.getPrice());
        item.setQuantity(1);
        item.setAmount(item.getPrice()*item.getQuantity());
        item.setService(service);
        intervention.getInterventionItems().add(item);
        
        calculateTotals(animal);
        
        fireTableRowsInserted(intervention.getInterventionItems().size()-1, intervention.getInterventionItems().size()-1);
    }
    
    public void removeInterventionItem(int row, Animal animal) {
        intervention.getInterventionItems().remove(row);
        for (int i = row; i < getInterventionItems().size(); i++){
            getInterventionItems().get(i).setRb(i+1);
        }       
        calculateTotals(animal);
        fireTableRowsDeleted(row, row);
    }

        public void calculateTotals(Animal animal) {
            if(animal == null){
                return;
            }
            
            if(animal.getOwner().getLoyaltyCard()){
                intervention.setDiscountForLoyalty(10);
            } else intervention.setDiscountForLoyalty(0);
            
            if(intervention.getInterventionItems().size()>3){
                intervention.setDiscountForNumberOfServices(15);
            } else intervention.setDiscountForNumberOfServices(0);
            
            double total = getInterventionItems().stream().mapToDouble(i->i.getAmount()).sum();
            intervention.setTotalAmountWithoutDiscount(total);
            
            double totalWithDiscount = total*(1-intervention.getDiscountForLoyalty()/100.0)*(1-intervention.getDiscountForNumberOfServices()/100.0);
            intervention.setTotalAmountWithDiscount(totalWithDiscount);
        }
        
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
         try {
             InterventionItem item = intervention.getInterventionItems().get(rowIndex);
             if((int)aValue<=0) throw new Exception("Kolicina mora biti veća od nule!");
             switch (columnIndex) {
                 case 3:
                     item.setQuantity((int) aValue);
                     item.setAmount(item.getPrice()*item.getQuantity());
                     intervention.getInterventionItems().set(rowIndex, item);
                     fireTableRowsUpdated(rowIndex, rowIndex);
                     break;
             }
             fireTableRowsUpdated(rowIndex, rowIndex);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Upozorenje!", JOptionPane.ERROR_MESSAGE);
         }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            return true;
        }
        return false;
    }
        
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnClass[columnIndex];
        }
        
        public List<InterventionItem> getInterventionItems(){
            return intervention.getInterventionItems();
        }
    
        public Intervention getIntervention() {
            return intervention;
        }
    
}

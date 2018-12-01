package org.particl.ui.table;

import javax.swing.JTable;

public class DataTable<T> extends JTable {

   private final DataTableModel<T> model;
   public DataTable() 
   {
      super();
      model = new DataTableModel<T>();
      setModel(model);
      
   }
   
   
   abstract protected class StringColumn extends DataTableColumn 
   {
      
   }
   
   abstract protected class IntColumn extends DataTableColumn
   {
      
   }
   
   
}

package org.particl.ui.table;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StringEditor extends DefaultCellEditor {

   
   public StringEditor() 
   {
      super(new JTextField());
   }
   
   @Override
   public Component getTableCellEditorComponent(JTable table, Object value, boolean is_selected, int r, int c) 
   {
      DataTableEntry<?> entry = (DataTableEntry<?>) value;
      if(!(entry.getCell() instanceof String)) 
      {
         
      }
      String str = (String) entry.getCell();
      ((JTextField) getComponent()).setText(str);
      
      return getComponent();
   }
   
   @Override
   public Object getCellEditorValue() 
   {
      return (String) super.getCellEditorValue();
   }
   
   
}

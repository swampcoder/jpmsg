package org.particl.ui.table;

import java.awt.Component;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

abstract public class DataTableColumn<T> {

   private final DataTable<T> table;
   private String title = null;
   
   private final Set<String> attributes = new HashSet<String>();

   abstract protected Object extractCellData(T t);

   abstract protected Comparator<?> getComparator();

   abstract protected void setCellData(T t, Object cellObj);

   protected DataTableColumn(DataTable<T> table, String title) {
      super();
      this.table = table;
      this.title = title;
   }

   public String getTitle() {
      return title;
   }

   public boolean isEditable() {
      return false;
   }

   public TableCellEditor getEditor() {
      return null;
   }

   @SuppressWarnings("serial")
   public TableCellRenderer getRenderer() {
      return new DefaultTableCellRenderer() {
         @Override
         public Component getTableCellRendererComponent(JTable _table, Object value, boolean selected, boolean hasFocus,
               int row, int column) {
            @SuppressWarnings("unchecked")
            DataTableEntry<T> entry = (DataTableEntry<T>) value;
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, entry.getCell(), selected, hasFocus, row, column);
            table.universalDecorate(entry, label, selected);
            return label;
         }
      };
   }

   protected void setAttribute(String attribute) {
      attributes.add(attribute);
   }

   public boolean hasAttribute(String attribute) {
      return attributes.contains(attribute);
   }

   protected void decorate(DataTableEntry<T> entry, boolean abSelected) {  }

}
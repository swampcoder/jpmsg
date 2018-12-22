package org.particl.ui.table;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

abstract public class DataTable<T> extends JTable {

   private List<DataTableColumn<T>> columns = new ArrayList<DataTableColumn<T>>();
   private DataTableModel<T> model = null;
   private TableRowSorter<DataTableModel<T>> sorter = null;
   private TableColumnAdjuster columnAdjuster = null;

   public DataTable() {
      super();
   }

   abstract protected void initColumns(List<DataTableColumn<T>> columns);

   public void initialize() {
      synchronized (columns) {
         if (columns.size() > 0) {
            throw new IllegalStateException("Already initialized");
         }
         initColumns(columns);
         model = new DataTableModel<T>(columns);
         setModel(model);
        // sorter = new TableRowSorter<DataTableModel<T>>();
        // setRowSorter(sorter);
         for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumnModel().getColumn(i);
            TableCellRenderer renderer = columns.get(i).getRenderer();
            column.setCellRenderer(renderer);
            Comparator<?> comparator = columns.get(i).getComparator();
            //if (comparator != null)
            //   sorter.setComparator(i, comparator);
         }
         addMouseListener(new TableMouseListener());
         columnAdjuster = new TableColumnAdjuster(this);
         model.addTableModelListener(columnAdjuster);
         addPropertyChangeListener(columnAdjuster);
      }
   }

   public void addOrUpdate(T t) {
      model.addOrUpdate(t);
   }

   public void removeRow(T t, boolean removeAll) {
      model.removeRow(t, removeAll);
   }

   public void addRow(T t) {
      model.addRow(t);
   }

   public boolean contains(T t) {
      return model.contains(t);
   }

   @Override
   public DataTableModel<T> getModel() {
      return model;
   }

   public void applyFilters() {
      model.applyFilters();
   }
   
   public TableColumnAdjuster getColumnAdjuster()
   {
      return columnAdjuster;
   }

   abstract protected static class DataTableColumn<T> {

      private String title = null;
      private final Set<String> attributes = new HashSet<String>();

      abstract protected Object extractCellData(T t);

      abstract protected Comparator<?> getComparator();

      abstract protected void setCellData(T t, Object cellObj);

      protected DataTableColumn(String title) {
         super();
         this.title = title;
      }

      public String getTitle() {
         return title;
      }
      
      public boolean isEditable() 
      {
         return false;
      }
      
      public TableCellEditor getEditor() { return null; }

      @SuppressWarnings("serial")
      public TableCellRenderer getRenderer() {
         return new DefaultTableCellRenderer()
         {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, 
                  boolean hasFocus, int row, int column) 
            {
               @SuppressWarnings("unchecked")
               DataTableEntry<T> entry = (DataTableEntry<T>) value;
               JLabel label = (JLabel) super.getTableCellRendererComponent(table, entry.getCell(), 
                     selected, hasFocus, row, column);
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

      protected void decorate(DataTableEntry<T> entry, boolean abSelected) { }
   }

   // Override where required
   protected void universalDecorate(DataTableEntry<T> entry, boolean selected) {

   }

   abstract protected class StringColumn extends DataTableColumn<T> {
      protected StringColumn(String title) {
         super(title);
      }

      @Override
      protected Comparator<?> getComparator() {
         return new StringComparator();
      }

      @Override
      protected void setCellData(T t, Object cellObj) {
      }

   }

   abstract protected class EditableStringColumn extends StringColumn {

      protected EditableStringColumn(String title) {
         super(title);
      }

   }

   abstract protected class NumericColumn extends DataTableColumn<T> {

      protected NumericColumn(String title) {
         super(title);
      }

      @Override
      protected Comparator<?> getComparator() {
         return new NumberComparator();
      }
   }

   abstract protected class IntColumn extends NumericColumn {

      protected IntColumn(String title) {
         super(title);
      }

      @Override
      protected void setCellData(T t, Object cellObj) {
      }
   }

   abstract protected class EditableIntColumn extends IntColumn {

      protected EditableIntColumn(String title) {
         super(title);
      }

   }

   protected static class StringComparator implements Comparator<String> {
      @Override
      public int compare(String a, String b) {
         return a.compareTo(b);
      }
   }

   protected static class NumberComparator implements Comparator<Number> {

      @Override
      public int compare(Number o1, Number o2) {

         return Double.compare(o1.doubleValue(), o2.doubleValue());
      }

   }

   protected class TableMouseListener extends MouseAdapter {

   }

}

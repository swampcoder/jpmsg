package org.particl.ui.table;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

abstract public class DataTable<T> extends JTable {

   private final DataTable<T> table = this;
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

   protected void universalDecorate(DataTableEntry<T> entry, Component component, boolean selected) {

   }

   // Override where required
   abstract protected class StringColumn extends DataTableColumn<T> {
      protected StringColumn(String title) {
         super(table, title);
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
         super(table, title);
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

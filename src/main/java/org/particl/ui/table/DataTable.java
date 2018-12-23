package org.particl.ui.table;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

abstract public class DataTable<T> extends JTable {

   
   private static List<DataTable<?>> tables = new CopyOnWriteArrayList<DataTable<?>>();
   private static Timer adjustTimer = new Timer("Data Table static adjuster");
   static
   {
      adjustTimer.schedule(new TimerTask() {
         @Override
         public void run() 
         {
            for(DataTable<?> table : tables) 
            {
               table.columnAdjuster.adjustColumns();
            }
         }
      }, 5555, 5555);
   }
   private final DataTable<T> table = this;
   private List<DataTableColumn<T>> columns = new ArrayList<DataTableColumn<T>>();
   private DataTableModel<T> model = null;
   private TableRowSorter<DataTableModel<T>> sorter = null;
   private TableColumnAdjuster columnAdjuster = null;
   private final List<IDataTableSelectionListener<T>> selectListeners = 
         new ArrayList<IDataTableSelectionListener<T>>();

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
            // if (comparator != null)
            // sorter.setComparator(i, comparator);
         }
         columnAdjuster = new TableColumnAdjuster(this);
         //model.addTableModelListener(columnAdjuster);
         //addPropertyChangeListener(columnAdjuster);

         getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               handleSelection(e);
            }
         });
         
         addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  JTable target = (JTable)e.getSource();
                  int row = target.getSelectedRow();
                  T t = model.get(row);
                  if(t != null) 
                  {
                     handleDoubleClick(t);
                  }
               } else if(e.getClickCount() == 1 && e.isPopupTrigger()) 
               {
                  handleRightClick(e);
               }
            }
         });
         tables.add(this);
      }
   }
   
   public void destroyTable() 
   {
      tables.remove(this);
   }

   public void addSelectListener(IDataTableSelectionListener<T> listener)
   {
      selectListeners.add(listener);
   }
   
   public void removeSelectListener(IDataTableSelectionListener<T> listener) 
   {
      selectListeners.remove(listener);
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

   public TableColumnAdjuster getColumnAdjuster() {
      return columnAdjuster;
   }

   protected void universalDecorate(DataTableEntry<T> entry, Component component, boolean selected) {

   }
   
   protected void handleDoubleClick(T t) {}

   protected void handleSelection(ListSelectionEvent lse) {
      int[] selectedRow = table.getSelectedRows();
      List<T> selections = new ArrayList<T>();
      for (int i = 0; i < selectedRow.length; i++) {
         T t = model.get(selectedRow[i]);
         selections.add(t);
      }
   }
   
   protected void handleRightClick(MouseEvent e) 
   {
      
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
      
      abstract protected void applyString(String value);
      
      @Override
      protected void setCellData(T t, Object cellObj) {
         String str  = (String) cellObj;
         applyString(str);
      }
      
      @Override
      public TableCellEditor getEditor() 
      {
         return new StringEditor();
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
}

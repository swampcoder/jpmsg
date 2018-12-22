package org.particl.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

public class DataTableModel<T> extends AbstractTableModel {

   private final List<DataTableColumn<T>> columns;
   private List<T> filteredList = new ArrayList<T>();
   private List<T> unfilteredList = new ArrayList<T>();
   private List<T> activeList = null;
   
   private List<IDataTableFilter<T>> filters = new ArrayList<IDataTableFilter<T>>();
   
   public DataTableModel(List<DataTableColumn<T>> columns)
   {
      super();
      this.columns = columns;
      this.activeList = unfilteredList;
   }
   
   public T get(int rowIndex) 
   {
      return activeList.get(rowIndex);
   }
   
   public void addFilter(IDataTableFilter<T> filter) 
   {
      filters.add(filter);
   }
   
   public void addOrUpdate(List<T> list) 
   {
      Runnable r = new Runnable() 
      {
         @Override
         public void run() 
         {
            for(T t : list)
            {
               int indexOf = activeList.indexOf(t);
               if(indexOf == -1) 
               {
                  activeList.add(t);
                  fireTableRowsInserted(activeList.size()-1, activeList.size()-1);
               }
               else 
               {
                  activeList.set(indexOf, t);
                  fireTableRowsUpdated(indexOf,indexOf);
               }
            }
         }
      };
      execute(r);
   }
   
   public void addOrUpdate(T t) 
   {
      Runnable r = new Runnable() 
      {
         @Override
         public void run() 
         {
            int indexOf = activeList.indexOf(t);
            if(indexOf == -1) 
            {
               activeList.add(t);
               fireTableRowsInserted(activeList.size()-1, activeList.size()-1);
            }
            else 
            {
               activeList.set(indexOf, t);
               fireTableRowsUpdated(indexOf, indexOf);
            }
            fireTableDataChanged();
         }
      };
      this.execute(r);
   }
   
   public void addRow(T t)
   {
      Runnable r = new Runnable() 
      {
         @Override
         public void run() 
         {
            activeList.add(t);
            fireTableRowsInserted(activeList.size()-1, activeList.size()-1);
         }
      };
      this.execute(r);
   }
   
   public void removeRow(T t, boolean removeAll) 
   {
      Runnable r = new Runnable() 
      {
         @Override
         public void run() 
         {
            boolean remove = activeList.remove(t) && removeAll;
            while(remove)
            {
               remove = activeList.remove(t);
            }
         }
      };
      execute(r);
   }

   public boolean contains(T t)
   {
      return activeList.contains(t);
   }
   
   public int indexOf(T t)
   {
      return activeList.indexOf(t);
   }
   
   public void clear() 
   {
      Runnable r = new Runnable() 
      {
         @Override
         public void run() 
         {
            int size = activeList.size();
            if(size > 0) 
            {
               activeList.clear();
               fireTableRowsDeleted(0, size-1);
            }
         }
      };
      execute(r);
   }
   
   @Override
   public boolean isCellEditable(int r, int c)
   {
      return columns.get(c).isEditable();
   }

   @Override
   public int getColumnCount() {

      return columns.size();
   }
   
   @Override
   public Class<?> getColumnClass(int c) 
   {
      return String.class;
   }

   @Override
   public int getRowCount() {

      return activeList.size();
   }
   
   @Override
   public String getColumnName(int c) 
   {
      return columns.get(c).getTitle();
   }

   @Override
   public Object getValueAt(int r, int c) {

      DataTableColumn<T> column = columns.get(c);
      T row = activeList.get(r);
      Object cellObj = column.extractCellData(row);
      DataTableEntry<T> entry = new DataTableEntry<T>(row, cellObj);
      entry.setColumn(column);
      return entry;

   }
   
   @Override
   public void setValueAt(Object obj, int r, int c) 
   {
      T t = activeList.get(r);
      DataTableColumn<T> column = columns.get(c);
      column.setCellData(t, obj);
   }
   
   void applyFilters() 
   {
      // todo 
   }
   
   private void execute(Runnable r) 
   {
      if(SwingUtilities.isEventDispatchThread())
      {
         r.run();
      }
      else 
      {
         SwingUtilities.invokeLater(r);
      }
   }
}

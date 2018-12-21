package org.particl.ui.table;

import java.util.Comparator;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public interface IDataTableColumn<T> {

   public String getColumnTitle();
   
   public Class<?> getColumnClass();

   public boolean isEditable();

   public Object extractCellData(T t);

   public void setCellData(T t, Object o);

   public TableCellRenderer getRenderer();

   public TableCellEditor getEditor();

   public Comparator<?> getComparator();
}

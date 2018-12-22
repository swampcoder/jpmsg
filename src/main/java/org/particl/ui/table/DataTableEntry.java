package org.particl.ui.table;

public class DataTableEntry<T>
{
   private final T row;

   private final Object cell;
   
   private DataTableColumn<T> column = null;

   DataTableEntry(T row, Object cell)
   {
      this.row = row;
      this.cell = cell;
   }

   public T getRow()
   {
      return row;
   }

   public Object getCell()
   {
      return cell;
   }
   
   public DataTableColumn<T> getColumn() 
   {
      return column;
   }
   
   @Override
   public String toString() 
   {
      return "row=" + row + "  cell=" + cell;
   }
   
   void setColumn(DataTableColumn<T> column) 
   {
      this.column = column;
   }
}

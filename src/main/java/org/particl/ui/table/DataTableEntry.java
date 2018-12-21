package org.particl.ui.table;


public class DataTableEntry<T>
{
   private final T row;

   private final Object cell;

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
   
   @Override
   public String toString() 
   {
      return "row=" + row + "  cell=" + cell;
   }
}

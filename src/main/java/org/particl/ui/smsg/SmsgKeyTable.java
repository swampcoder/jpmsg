package org.particl.ui.smsg;

import java.util.List;

import javax.swing.table.TableCellRenderer;

import org.particl.rpc.core.smsg.SmsgKey;
import org.particl.rpc.core.smsg.SmsgPoller;
import org.particl.ui.table.*;

public class SmsgKeyTable extends DataTable<SmsgKey> implements SmsgPoller.ISmsgKeyHandler {

   public SmsgKeyTable() 
   {
      super();
      initialize();
   }
   
   @Override
   public void notifyLocalKeys(List<SmsgKey> keys) {
     
      getModel().addOrUpdate(keys);
   }

   @Override
   protected void initColumns(List<DataTableColumn<SmsgKey>> columns) {

      AddressColumn addrcol = new AddressColumn();
      PubkeyColumn pkcol = new PubkeyColumn();
      LabelColumn labelcol = new LabelColumn();
      
      columns.add(labelcol);
      columns.add(addrcol);
      columns.add(pkcol);
   }
   
   protected class AddressColumn extends StringColumn 
   {

      protected AddressColumn( ) {
         super("Address");
      }

      @Override
      protected Object extractCellData(SmsgKey t) {

         return t.getAddress();
      }
   }
   
   protected class PubkeyColumn extends StringColumn
   {

      protected PubkeyColumn( ) {
         super("Pubkey");
      }

      @Override
      protected Object extractCellData(SmsgKey t) {

         return t.getPublicKey();
      }
   }
   
   protected class LabelColumn extends StringColumn
   {

      protected LabelColumn( ) {
         super("Label");
      }

      @Override
      protected Object extractCellData(SmsgKey t) {

         return t.getLabel();
      } 
   }


}

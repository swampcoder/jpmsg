package org.particl.ui.smsg;

import java.awt.Component;
import java.util.List;

import javax.swing.table.TableCellRenderer;

import org.particl.rpc.core.IParticlCore;
import org.particl.rpc.core.smsg.SmsgLocation;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.rpc.core.smsg.SmsgPoller;
import org.particl.ui.table.DataTable;
import org.particl.ui.table.*;

public class SmsgMessageTable extends DataTable<SmsgMessage>
      implements SmsgPoller.ISmsgInboxHandler, SmsgPoller.ISmsgOutboxHandler {

   private final IParticlCore rpc;
   private final SmsgMessageTableFilter filter;
   private boolean useBase64Encoding = false;

   public SmsgMessageTable(IParticlCore rpc) {
      this.rpc = rpc;
      filter = new SmsgMessageTableFilter(this);
      initialize();
   }

   @Override
   public void notifyOutbox(List<SmsgMessage> outbox) {
      getModel().addOrUpdate(outbox);
   }

   @Override
   public void notifyInbox(List<SmsgMessage> inbox) {
      getModel().addOrUpdate(inbox);
   }

   public void setUseBase64Encoding(boolean useBase64Encoding) {
      this.useBase64Encoding = useBase64Encoding;
      getModel().fireTableDataChanged();
   }

   public void setLocationFilter(SmsgLocation... locations) {

      filter.updateLocations(locations);
   }
   
   public SmsgMessageTableFilter getFilter() 
   {
      return filter;
   }
   
   @Override
   protected void handleDoubleClick(SmsgMessage msg) 
   {
      System.out.println("Double: "+ msg);
   }

   @Override
   protected void initColumns(List<DataTableColumn<SmsgMessage>> columns) {

      columns.add(new FromColumn("From"));
      columns.add(new ToColumn("To"));
      columns.add(new MsgIdColumn("ID"));
      columns.add(new MsgColumn("Message"));
   }

   @Override
   public void universalDecorate(DataTableEntry<SmsgMessage> entry, Component component, boolean selected) {

      
   }

   protected class ReadColumn {

   }

   protected class FromColumn extends StringColumn {

      protected FromColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {
         return t.getFromAddress();
      }

   }

   protected class ToColumn extends StringColumn {
      protected ToColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {
         return t.getToAddress();
      }

   }

   protected class MsgColumn extends StringColumn {

      protected MsgColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {
         return t.getMsgText();
      }

   }

   protected class MsgIdColumn extends StringColumn {

      protected MsgIdColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {
         return t.getMsgId();
      }

   }

   protected class SizeColumn extends StringColumn {

      protected SizeColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {

         return (double) t.getMsgSize() / 1024L + "kb";
      }

   }

   protected class LocationColumn extends StringColumn {

      protected LocationColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {

         return t.getMsgLocation().toString();
      }

   }

   protected class MsgPaidColumn extends StringColumn {

      protected MsgPaidColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {

         if (t.isMsgPaid())
            return "y";
         else
            return "n";
      }

   }

   protected class DaysRetention extends IntColumn {

      protected DaysRetention() {
         super("Retention");
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {
         return t.getDaysRetention();
      }

   }

   protected class SentTimeColumn extends StringColumn {

      protected SentTimeColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {
         return t.getSentTime().toString();
      }

   }

   protected class RecvTimeColumn extends StringColumn {

      protected RecvTimeColumn(String title) {
         super(title);
      }

      @Override
      protected Object extractCellData(SmsgMessage t) {
         return t.getReceiveTime().toString();
      }

   }

}

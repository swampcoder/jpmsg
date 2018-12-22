package org.particl.ui.smsg;

import java.util.List;

import javax.swing.SwingUtilities;

import org.particl.rpc.core.smsg.SmsgBucket;
import org.particl.rpc.core.smsg.SmsgPoller;
import org.particl.ui.table.*;

public class SmsgBucketTable extends DataTable<SmsgBucket> implements SmsgPoller.ISmsgBucketHandler {

   public SmsgBucketTable() {
      super();
      initialize();
   }

   @Override
   public void notifyBuckets(List<SmsgBucket> buckets) {

      getModel().addOrUpdate(buckets);
   }

   @Override
   protected void initColumns(List<DataTableColumn<SmsgBucket>> columns) {

      columns.add(new BucketIdColumn());
      columns.add(new NumMsgColumn());
      columns.add(new NumActiveMsgColumn());
      columns.add(new BucketSizeKbcolumn());
      columns.add(new HashColumn());
   }

   private class BucketIdColumn extends IntColumn {

      protected BucketIdColumn() {
         super("Bucket ID");
      }

      @Override
      protected Object extractCellData(SmsgBucket t) {

         return (int) t.getBucketId();
      }

   }

   private class NumMsgColumn extends IntColumn {

      protected NumMsgColumn() {
         super("# Messages");
      }

      @Override
      protected Object extractCellData(SmsgBucket t) {
         return t.getNumMessages();
      }

   }

   private class NumActiveMsgColumn extends IntColumn {

      protected NumActiveMsgColumn() {
         super(" # Active ");
      }

      @Override
      protected Object extractCellData(SmsgBucket t) {

         System.out.println("Active get: "+  t.getActiveMessages());
         return t.getActiveMessages();
      }

   }

   private class HashColumn extends StringColumn {

      protected HashColumn() {
         super("Bucket Hash");
      }

      @Override
      protected Object extractCellData(SmsgBucket t) {

         return t.getHash().toString();
      }

   }

   private class LastChangeTimeColumn extends StringColumn {

      protected LastChangeTimeColumn() {
         super("Last Modified");
      }

      @Override
      protected Object extractCellData(SmsgBucket t) {

         return t.getLastChangeTime();
      }

   }

   private class BucketSizeKbcolumn extends StringColumn {

      protected BucketSizeKbcolumn() {
         super("Bucket Size");

      }

      @Override
      protected Object extractCellData(SmsgBucket t) {

         return null;
      }

   }

}

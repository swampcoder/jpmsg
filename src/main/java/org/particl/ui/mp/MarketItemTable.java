package org.particl.ui.mp;

import java.util.List;

import org.particl.rpc.mp.MarketPoller;
import org.particl.rpc.mp.dto.Item;
import org.particl.ui.table.DataTable;
import org.particl.ui.table.DataTableColumn;

public class MarketItemTable extends DataTable<Item> implements MarketPoller.IMarketItemHandler {

   public MarketItemTable() 
   {
      super();
      initialize();
   }
   
   @Override
   public void notifyMarketListings(List<Item> listings) {
      
      addOrUpdate(listings);
   }
   
   @Override
   protected void initColumns(List<DataTableColumn<Item>> columns) {
      
      columns.add(new Market());
      columns.add(new Seller());
      columns.add(new Description());
   }
   
   private class Market extends StringColumn 
   {
      protected Market( ) {
         super("Market");
      }

      @Override
      protected Object extractCellData(Item t) {

         return t.getMarket().getName();
      }
   }
   
   private class Seller extends StringColumn 
   {
      protected Seller( ) {
         super("Seller");
      }

      @Override
      protected Object extractCellData(Item t) {

         return t.getSeller();
      }
   }

   private class Description extends StringColumn 
   {
      protected Description( ) {
         super("Listing Description");
      }

      @Override
      protected Object extractCellData(Item t) {

         return t.getItemInformation().getShortDescription();
      }
      
   }

}

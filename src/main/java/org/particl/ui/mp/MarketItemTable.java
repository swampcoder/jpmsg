package org.particl.ui.mp;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.particl.app.Application;
import org.particl.rpc.mp.MarketPoller;
import org.particl.rpc.mp.dto.Item;
import org.particl.rpc.mp.dto.ItemImage;
import org.particl.rpc.mp.dto.ItemImageData;
import org.particl.ui.table.DataTable;
import org.particl.ui.table.DataTableColumn;

public class MarketItemTable extends DataTable<Item> 
   implements MarketPoller.IMarketItemHandler, ImageRetriever.IRetrievableImageHandler {

   public MarketItemTable() 
   {
      super();
      initialize();
      
      setFont(new Font("Sans Serif", Font.PLAIN, 10));
   }
   

   @Override
   public void notifyImageRetrieval(ItemImage image, Map<ItemImageData, BufferedImage> imageMap) {
     
      System.out.println("Retrieval complete: " + imageMap);
      
      Iterator<BufferedImage> images = imageMap.values().iterator();
      while(images.hasNext()) 
      {
         System.out.println(images.next());
      }
   }
   
   @Override
   protected void handleDoubleClick(Item item) 
   {
      Iterator<ItemImage> images = item.getItemInformation().getItemImages().iterator();
      while(images.hasNext()) 
      {
         ItemImage image = images.next();
         Application.getService(ImageRetriever.class).requestImage(image, this);
      }
   }
   
   @Override
   public void notifyMarketListings(List<Item> listings) {
      
      addOrUpdate(listings);
   }
   
   @Override
   protected void initColumns(List<DataTableColumn<Item>> columns) {
      
      columns.add(new Market());
      columns.add(new ItemTitle());
     // columns.add(new TimeLeft());
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
   
   private class ItemTitle extends StringColumn
   {

      protected ItemTitle() {
         super("Item");
         // TODO Auto-generated constructor stub
      }

      @Override
      protected Object extractCellData(Item t) {
         // TODO Auto-generated method stub
         return t.getItemInformation().getTitle();
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
   
   private class TimeLeft extends StringColumn
   {

      protected TimeLeft( ) {
         super("Time Left");

      }

      @Override
      protected Object extractCellData(Item t) {

         return Long.toString(t.getExpiredAt() - System.currentTimeMillis());
      }
      
   }


}

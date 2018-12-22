package org.particl.ui.smsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.particl.rpc.core.smsg.SmsgLocation;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.ui.table.IDataTableFilter;

public class SmsgMessageTableFilter implements IDataTableFilter<SmsgMessage> {

   private final SmsgMessageTable table;
   private List<SmsgLocation> permittedLocations = new ArrayList<SmsgLocation>();
   private String from = null;
   
   private Long beginTimeFilter = null;
   private Long endTimeFilter = null;
   private boolean paidOnly = false;
   private boolean unreadOnly = false;
   
   public SmsgMessageTableFilter(SmsgMessageTable table)
   {
      super();
      this.table = table;
   }
   
   public synchronized void setAddressFromFilter(String from) {
      this.from = from;
      table.applyFilters();
   }
   
   public synchronized void updateLocations(SmsgLocation...locations) 
   {
      permittedLocations.clear();
      permittedLocations.addAll(Arrays.asList(locations));
      table.applyFilters();
   }
   
   public synchronized void upateTimeFilter(long beginTime, long endTime) 
   {
      beginTimeFilter = beginTime;
      endTimeFilter = endTime;
      table.applyFilters();
   }
   
   public synchronized void clearTimeFilter() 
   {
      beginTimeFilter = null;
      endTimeFilter = null;
      table.applyFilters();
   }
   
   public synchronized void setPaidOnly(boolean paidOnly) 
   {
      this.paidOnly = paidOnly;
      table.applyFilters();
   }
   
   public synchronized void setUnreadOnly(boolean unreadOnly)
   {
      this.unreadOnly = unreadOnly;
      table.applyFilters();
   }

   @Override
   public synchronized boolean isVisible(SmsgMessage t) {

      if(permittedLocations.size() > 0 && !permittedLocations.contains(t.getMsgLocation()))
      {
         return false;
      }
      if(from != null && !t.getFromAddress().equals(from)) 
      {
         return false;
      }
      if(beginTimeFilter != null) 
      {
         if(t.getSentTime() < beginTimeFilter || t.getSentTime() > endTimeFilter) 
         {
            return false;
         }
      }
      if(paidOnly && !t.isMsgPaid()) 
      {
         return false;
      }
      if(unreadOnly && t.isMsgRead())
      {
         return false;
      }
      return true;
   }
}

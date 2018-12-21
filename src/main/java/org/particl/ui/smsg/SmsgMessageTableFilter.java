package org.particl.ui.smsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.particl.rpc.core.IParticlCore.SmsgLocation;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.ui.table.IDataTableFilter;

public class SmsgMessageTableFilter implements IDataTableFilter<SmsgMessage> {

   private final SmsgMessageTable table;
   private List<SmsgLocation> permittedLocations = new ArrayList<SmsgLocation>();
   private String from = null;
   
   private Long beginTimeFilter = null;
   private Long endTimeFilter = null;
   
   public SmsgMessageTableFilter(SmsgMessageTable table)
   {
      super();
      this.table = table;
   }
   
   public synchronized void setAddressFromFilter(String from) {
      this.from = from;
      // update
   }
   
   public synchronized void updateLocations(SmsgLocation...locations) 
   {
      permittedLocations.clear();
      permittedLocations.addAll(Arrays.asList(locations));
   }
   
   public synchronized void upateTimeFilter(long beginTime, long endTime) 
   {
      beginTimeFilter = beginTime;
      endTimeFilter = endTime;
      // update
   }
   
   public synchronized void clearTimeFilter() 
   {
      beginTimeFilter = null;
      endTimeFilter = null;
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
      return true;
   }
}

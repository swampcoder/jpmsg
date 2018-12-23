package org.particl.ui.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.particl.ui.smsg.SmsgBucketViewFactory;
import org.particl.ui.smsg.SmsgInboxViewFactory;
import org.particl.ui.smsg.SmsgLocalKeyViewFactory;
import org.particl.ui.smsg.SmsgOutboxViewFactory;

// refactor this later
public class Factories {

   private final static Map<String, List<IDesktopViewFactory>> factoryMap = new 
         HashMap<String, List<IDesktopViewFactory>>();
   
   static
   {
      addFactory(new SmsgInboxViewFactory());
      addFactory(new SmsgOutboxViewFactory());
      addFactory(new SmsgLocalKeyViewFactory());
      addFactory(new SmsgBucketViewFactory());
   }
   
   public static Iterator<String> getViewGroups() 
   {
      return factoryMap.keySet().iterator();
   }
   
   public static Iterator<IDesktopViewFactory> getViewGroupFactories(String viewGroup) 
   {
      return factoryMap.get(viewGroup).iterator();
   }
   
   private static void addFactory(IDesktopViewFactory factory) 
   {
      List<IDesktopViewFactory> factoryList = factoryMap.get(factory.getViewGroup());
      if(factoryList == null)
      {
         factoryList = new ArrayList<IDesktopViewFactory>();
         factoryMap.put(factory.getViewGroup(),factoryList);
      }
      factoryList.add(factory);
   }
   
   private Factories() {}
}

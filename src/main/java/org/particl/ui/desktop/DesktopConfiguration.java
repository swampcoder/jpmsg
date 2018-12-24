package org.particl.ui.desktop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mapdb.HTreeMap;
import org.particl.app.Application;
import org.particl.app.IAppService;


public class DesktopConfiguration implements IAppService {

   public static void init() {}
   public static final String DesktopConfigLayoutMap = "DesktopLayoutMap";
   public static final String DesktopConfigLayoutKeyPrefix = "DesktopLayoutConfig ";
   static 
   {
      System.out.println("Stati init");
      DesktopConfiguration config = new DesktopConfiguration();
      boolean init = Application.initService(config, DesktopConfiguration.class);
   
      System.out.println("Service init: " + init);;
   }
   
   private List<DesktopLayout> layouts = new ArrayList<DesktopLayout>();

   private DesktopConfiguration() 
   {
      loadLayouts();
   }
   
   public Iterator<DesktopLayout> getLayouts( ){
      return layouts.iterator();
   }
   
   private void loadLayouts() 
   {
      HTreeMap<String,String> layoutMap = Application.getdb().stringmap(DesktopConfigLayoutMap);
      Iterator<String> layoutNames = layoutMap.keySet().iterator();
      while(layoutNames.hasNext())
      {
         String layoutName = layoutNames.next();
         String layoutXml = layoutMap.get(layoutName);
         
         // parse jaxb layout string 
      }   
   }
}

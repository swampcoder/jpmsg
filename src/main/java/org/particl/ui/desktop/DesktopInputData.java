package org.particl.ui.desktop;

import java.util.HashMap;
import java.util.Map;

public class DesktopInputData {

   private final Desktop parentDesktop;
   private final Map<String, Object> inputMap = new HashMap<String, Object>();
   private IDesktopViewFactory factory = null;
  
   public DesktopInputData(Desktop parentDesktop) 
   {
      super();
      this.parentDesktop = parentDesktop;
   }
   
   public Desktop getParentDesktop() 
   {
      return parentDesktop;
   }
   
   public Object getValue(String k) 
   {
      return inputMap.get(k);
   }
   
   public void setValue(String k, Object v)
   {
      inputMap.put(k,v);
   }
   
   public IDesktopViewFactory getFactory() 
   {
      return factory;
   }
   
   void setFactory(IDesktopViewFactory factory) 
   {
      this.factory = factory;
   }
}

package org.particl.ui.desktop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DesktopLayout {

   private String layoutName = null;
   
   private List<DesktopFrameLayout> frameLayouts = new ArrayList<DesktopFrameLayout>();
   
   public DesktopLayout() 
   {
      super();
   }
   
   public DesktopLayout(Desktop desktop) 
   {
      Iterator<DesktopFrame> frames = desktop.frames();
      while(frames.hasNext()) 
      {
         DesktopFrame frame = frames.next();
         frameLayouts.add(new DesktopFrameLayout(frame));
      }
   }
   
   public String getLayoutName() 
   {
      return layoutName;
   }
   
   public Iterator<DesktopFrameLayout> getLayouts() 
   {
      return frameLayouts.iterator();
   }
}

package org.particl.ui.desktop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DesktopFrameLayout {

   private final List<DesktopViewState> viewStates = new ArrayList<DesktopViewState>();
   
   public DesktopFrameLayout() 
   {
      super();
   }
   
   public DesktopFrameLayout(DesktopFrame frame)
   {
      Iterator<DesktopView> views = frame.getViews();
      while(views.hasNext()) 
      {
         DesktopView view = views.next();
         viewStates.add(new DesktopViewState(view));
      }
   }
   
   public int numViews() 
   {
      return viewStates.size();
   }
   
   public Iterator<DesktopViewState> getViewStates() 
   {
      return viewStates.iterator();
   }
}

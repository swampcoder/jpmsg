package org.particl.ui.desktop;

public interface IDesktopViewFactory {

   public String getViewGroup();
   
   public String getViewDescription();
   
   public DesktopView createView(DesktopInputData viewData) throws DesktopViewException;
   
   default public void getInput(DesktopInputData viewData)
   {
      // default impl gets no input 
   }
}

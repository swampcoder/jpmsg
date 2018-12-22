package org.particl.ui.desktop;

public interface IDesktopViewFactory {

   public DesktopView createView(DesktopInputData viewData);
   
   public void getInput(DesktopInputData viewData);
}

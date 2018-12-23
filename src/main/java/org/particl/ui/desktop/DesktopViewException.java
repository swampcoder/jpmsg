package org.particl.ui.desktop;

public class DesktopViewException extends Exception {

   public DesktopViewException(Exception e) 
   {
      super(e);
   }
   
   public DesktopViewException(String msg) 
   {
      super(msg);
   }
   
   public DesktopViewException(String msg, Exception e)
   {
      super(msg, e);
   }
}

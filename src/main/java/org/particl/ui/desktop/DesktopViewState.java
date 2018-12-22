package org.particl.ui.desktop;

// state for a single desktop view instance 
public class DesktopViewState {

   private String factoryClass = null;
   private String title = null;
   private long stateId = 0;
   private double x_position_weight = 0;
   private double y_position_weight = 0;
   
   public DesktopViewState() 
   {
      super();
   }
   
   public DesktopViewState(DesktopView view) 
   {
      factoryClass = view.getFactory().getClass().getName();
      title = view.getTitle();
   }
   
   public String getTitle() 
   {
      return title;
   }
   
   public double getXWeight() 
   {
      return x_position_weight;
   }
   
   public double getYWeight() 
   {
      return y_position_weight;
   }
   
   public double getWidthWeight() 
   {
      return 0;
   }
   
   public double getHeightWeight() 
   {
      return 0;
   }
   
   public long getstateId() 
   {
      return stateId;
   }
   
   @SuppressWarnings("unchecked")
   public IDesktopViewFactory createFactory() 
   {
      try {
         Class<? extends IDesktopViewFactory> factoryCls = (Class<? extends IDesktopViewFactory>) 
               Class.forName(factoryClass);
         return factoryCls.newInstance();
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
         e.printStackTrace();
      }
      return null;
   }
   
   void saveViewState(DesktopFrame viewFrame, DesktopView view) 
   {
      title = view.getTitle();  
   }
}

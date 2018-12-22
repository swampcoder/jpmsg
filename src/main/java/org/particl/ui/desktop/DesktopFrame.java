package org.particl.ui.desktop;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DesktopFrame extends JInternalFrame implements ChangeListener {

   private final List<DesktopView> frameViews = new ArrayList<DesktopView>();
   private final Desktop desktop;
   private JMenuBar frameMenubar = new JMenuBar();
   
   private JPanel singlePanelContent = null;
   private JTabbedPane tabContent = null;
   
   public DesktopFrame(Desktop desktop)
   {
      super();
      this.desktop = desktop;
      this.setJMenuBar(frameMenubar);
   }
   
   public DesktopFrame(Desktop desktop, DesktopFrameLayout frameLayout) 
   {
      this.desktop = desktop;
      this.setJMenuBar(frameMenubar);
      Iterator<DesktopViewState> viewStates = frameLayout.getViewStates();
      while(viewStates.hasNext())
      {
         DesktopViewState viewState = viewStates.next();
         loadViewState(viewState);
      }
   }
   
   public Iterator<DesktopView> getViews() 
   {
      return frameViews.iterator();
   }
   
   public void addView(DesktopView view)
   {
      frameViews.add(view);
      
      if(frameViews.size() > 1)
      {
         if(tabContent == null)
         {
            tabContent = new JTabbedPane();
            getContentPane().remove(singlePanelContent);
            getContentPane().invalidate();
            getContentPane().add(tabContent);
            getContentPane().revalidate();
            tabContent.addChangeListener(this);
         }
         
      }
   }
   
   public void removeView(DesktopView view)
   {
      
   }

   @Override
   public void stateChanged(ChangeEvent e) {

      if(e.getSource() == tabContent) 
      {
         
      }
   }
   
   private void loadViewState(DesktopViewState viewState) 
   {
      IDesktopViewFactory factory = viewState.createFactory();
      if(factory != null) 
      {
         DesktopView view = factory.createView(new DesktopInputData(desktop));
      }
   }
}

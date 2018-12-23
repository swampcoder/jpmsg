package org.particl.ui.desktop;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DesktopFrame extends JInternalFrame implements ChangeListener {

   public static ImageIcon icon;
   static 
   {
      icon = new ImageIcon(ClassLoader.getSystemResource("particl-logo.png"));
   }
   private static Integer frameIds = 0;
   private final int frameid = frameid();
   private final List<DesktopView> frameViews = new ArrayList<DesktopView>();
   private final Desktop desktop;
   private JMenuBar frameMenubar = new JMenuBar();
   private final ViewOptionMenu viewOptionMenu;
   private Iterable<JMenu> currentMenus = null;

   private JTabbedPane tabContent = null;
   
   public DesktopFrame(Desktop desktop, DesktopView initialView)
   {
      super();
      
      this.desktop = desktop;
      setJMenuBar(frameMenubar);
      getContentPane().add(initialView);
      viewOptionMenu = new ViewOptionMenu(this);
      loadViewMenu(initialView);
      frameViews.add(initialView);
      setFrameIcon(icon);
      setTitle(initialView.getTitle());
   }
   
   public DesktopFrame(Desktop desktop, DesktopFrameLayout frameLayout) throws DesktopViewException 
   {
      super();
      this.desktop = desktop;
      setJMenuBar(frameMenubar);
      viewOptionMenu = new ViewOptionMenu(this);
      
      List<DesktopView> views = new ArrayList<DesktopView>();
      Iterator<DesktopViewState> viewStates = frameLayout.getViewStates();
      while(viewStates.hasNext())
      {
         DesktopViewState viewState = viewStates.next();
         loadViewState(views, viewState);
      }
      if(views.size() > 0)
      {
         initViews(views);
      }
      else 
      {
         throw new DesktopViewException("frame loading failed! #=" + frameLayout.numViews());
      }
      
      
   }
   
   public Desktop getDesktop() 
   {
      return desktop;
   }
   
   public Iterator<DesktopView> getViews() 
   {
      return frameViews.iterator();
   }
   
   public void addView(DesktopView view)
   {
      frameViews.add(view);
      if(tabContent == null) 
      {
         tabContent = new JTabbedPane();
         invalidate();
         getContentPane().removeAll();
         for(DesktopView frameView : frameViews) 
         {
            tabContent.addTab(frameView.getTitle(), frameView);
         }
         getContentPane().add(tabContent);
         tabContent.addChangeListener(this);
         revalidate();
      }
      else
      {
         tabContent.addTab(view.getTitle(), view);
         tabContent.setSelectedIndex(tabContent.getTabCount()-1);
      }
   }
   
   public void removeView(DesktopView view)
   {
      frameViews.remove(view);
      
      if(frameViews.size() == 0) 
      {
         invalidate();
         removeAll();
         setVisible(false);
         dispose();
      }
      else if(frameViews.size() == 1) 
      {
         invalidate();
         remove(tabContent);
         tabContent.removeChangeListener(this);
         tabContent.removeAll();
         getContentPane().add(view);
         validate();
      }
      else
      {
         
      }
   }
   
   public DesktopView getCurrentView() 
   {
      if(tabContent == null) return frameViews.get(0);
      else return (DesktopView) tabContent.getComponentAt(tabContent.getSelectedIndex());
   }

   @Override
   public void stateChanged(ChangeEvent e) {

      if(e.getSource() == tabContent) 
      {
         DesktopView view = (DesktopView) tabContent.getComponentAt(tabContent.getSelectedIndex());
         loadViewMenu(view);
         setTitle(view.getTitle());
      }
   }
   
   @Override
   public int hashCode() 
   {
      return frameIds;
   }
   
   @Override
   public boolean equals(Object cmp) 
   {
      if(cmp instanceof DesktopFrame) 
      {
         DesktopFrame frame = (DesktopFrame) cmp;
         return frame.frameid == frameid;
      }
      return false;
   }
   
   void closeCurrent() 
   {
      removeView(getCurrentView()); 
   }
   
   private void initViews(List<DesktopView> views) 
   {
      if(views.size() == 1) 
      {
         getContentPane().add(views.get(0));
         setTitle(views.get(0).getTitle());
      }
      else 
      {
         tabContent = new JTabbedPane();
         for(DesktopView view : views) 
         {
            tabContent.addTab(view.getTitle(), view);
         }
         getContentPane().add(tabContent);
         tabContent.addChangeListener(this);
      }
   }
   
   private void loadViewMenu(DesktopView view) 
   {
      frameMenubar.invalidate();
      if(currentMenus != null) 
      {
         Iterator<JMenu> menus = currentMenus.iterator();
         while(menus.hasNext()) 
         {
            frameMenubar.remove(menus.next());
         }
      }
      frameMenubar.add(viewOptionMenu);
      currentMenus = view.getMenus();
      Iterator<JMenu> menus = currentMenus.iterator();
      while(menus.hasNext()) 
      {
         frameMenubar.add(menus.next());
      }
      frameMenubar.revalidate();
   }
   
   private void loadViewState( List<DesktopView>  views, DesktopViewState viewState) 
   {
      IDesktopViewFactory factory = viewState.createFactory();
      if(factory != null) 
      {
         try {
            DesktopView view = factory.createView(new DesktopInputData(desktop));
            view.setTitle(viewState.getTitle());
            //load state todo
            views.add(view);
         } catch (DesktopViewException e) {
            e.printStackTrace();
         }
      }
   }
   
   private static int frameid() 
   {
      synchronized(frameIds) 
      {
         frameIds++;
         return frameIds;
      }
   }
}

package org.particl.ui.mp;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
  * A special layout manager for the custom title panel.
  */
 public class TitlebarLayoutManager implements LayoutManager {
     
     private Dimension preferredSize = null;

     @Override
     public void addLayoutComponent(String name, Component comp) {
     }

     @Override
     public void removeLayoutComponent(Component comp) {
     }

     @Override
     public Dimension preferredLayoutSize(Container parent) {
         layoutContainer(parent);
         return preferredSize;
     }

     @Override
     public Dimension minimumLayoutSize(Container parent) {
         return parent.getMinimumSize();
     }

     @Override
     public void layoutContainer(Container parent) {
         // Calculate the new positions of the titlebar controls and the preferred size of the panel
         int width = 0;
         int count = parent.getComponentCount();
         int w[] = new int[count];
         for (int i = 0; i < count; i++) {
             Component c = parent.getComponent(i);
             if (c.isVisible()) {
                 w[i] = c.getPreferredSize().width;
                 width += w[i];
                 width += 4;
             }
         }
         if (preferredSize == null) {
             preferredSize = new Dimension(width, parent.getHeight());
         } else {
             preferredSize.width = width;
             preferredSize.height = parent.getHeight();
         }
         int x = parent.getWidth() - width;
         if (!parent.getComponentOrientation().isLeftToRight()) {
             x = 0;
         }
         int y = 0;
         int h = parent.getHeight() - 2;
         for (int i = 0; i < count; i++) {
             Component c = parent.getComponent(i);
             if (c.isVisible()) {
                 c.setBounds(x, y, w[i], h);
                 x += w[i] + 4;
             }
         }
     }
 } // end TitlebarLayoutManager
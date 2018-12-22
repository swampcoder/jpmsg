package org.particl.ui.smsg;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class StatusLabel extends JLabel {
   
   private Color outlineClr = Color.BLACK;
   private Color fillClr = Color.green;
   
   public StatusLabel(String text)
   {
      super(text);
      
      setBorder(BorderFactory.createEmptyBorder(1, 15, 1, 1));
   }
   
   @Override
   protected void paintComponent(Graphics g) 
   {
      super.paintComponent(g);
      
      // draw gumball label 
      g.setColor(outlineClr);
      g.fillOval(-1, -1, 14, 14);
      g.setColor(fillClr);
      g.fillOval(0,0, 12, 12);
      
   }
}

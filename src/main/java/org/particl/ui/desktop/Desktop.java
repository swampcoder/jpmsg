package org.particl.ui.desktop;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

import org.particl.rpc.mp.Utils;

public class Desktop extends JDesktopPane {
   
   public final static BufferedImage ParticlWallpaper;
   static
   {
      try {
         ParticlWallpaper = ImageIO.read( ClassLoader.getSystemResource( "PART_WALL_0002.jpg" ));
      } catch (IOException e) {
         throw new ExceptionInInitializerError(e);
      }
   }
   private final String desktopName;
   private final List<DesktopFrame> desktopFrames = new ArrayList<DesktopFrame>();

   public Desktop(String desktopName) 
   {
      super();
      Utils.notNull(desktopName);
      this.desktopName = desktopName;
   }
   
   public String getDesktopName() 
   {
      return desktopName;
   }
   
   public Iterator<DesktopFrame> frames() 
   {
      return desktopFrames.iterator();
   }
   
   public void addFrame(DesktopFrame frame) 
   {
      desktopFrames.add(frame);
      add(frame);
      frame.setBounds(25, 25, 333, 333);
      frame.setVisible(true);
      frame.setResizable(true);
      frame.setClosable(true);
      frame.setIconifiable(true);
   }
   
   public void removeFrame(DesktopFrame frame)
   {
      if(desktopFrames.remove(frame)) 
      {
         remove(frame);
      }
   }
   
   @Override
   public int hashCode() 
   {
      return desktopName.hashCode();
   }
   
   @Override
   public boolean equals(Object cmp) 
   {
      if(cmp instanceof Desktop) 
      {
         Desktop desktop = (Desktop) cmp;
         return desktop.desktopName.equals(desktopName);
      }
      return false;
   }
   
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawImage(ParticlWallpaper, 0, 0, null);
   }
}

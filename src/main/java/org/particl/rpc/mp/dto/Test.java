package org.particl.rpc.mp.dto;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {

   public static void main(String[] args)
   {
      JPanel p = new JPanel();
      p.setLayout(new GridLayout(2,2));
      for(int i = 0; i < 4; i++) 
      {
         JPanel p2 = new JPanel();
         p2.setBorder(BorderFactory.createTitledBorder("dumm"));
         p.add(p2);
      }
      JFrame f = new JFrame();
      f.add(p);
      p.setPreferredSize(new Dimension(300,300));
      f.pack();
      f.setExtendedState(JFrame.MAXIMIZED_BOTH);
      f.setVisible(true);;
   }
}

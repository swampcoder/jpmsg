package org.particl.rabbitmq.testui;

import java.awt.Dimension;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import org.particl.rabbitmq.ParticlSmsgUserNode;
import org.particl.rpc.core.ParticlJSONRPCClient;

public class LocalTester {

   private static ParticlSmsgUserNode userNode1 = null;
   private static ParticlSmsgUserNode userNode2 = null;
   public static void main(String[] args) 
   {
      try {
         ParticlJSONRPCClient rpc1 = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
         ParticlJSONRPCClient rpc2 = new ParticlJSONRPCClient("localhost", "particl", "password", 22223);
         userNode1 = new ParticlSmsgUserNode(rpc1);
         userNode2 = new ParticlSmsgUserNode(rpc2);
         
         MultiUserGridPanel testPanel = new MultiUserGridPanel(userNode1, userNode2);
         
         JFrame frame = new JFrame();
         frame.setAlwaysOnTop(true);
         frame.setTitle("Particl SMSG Node Tester");
         frame.getContentPane().add(testPanel);
         frame.pack();
         frame.setVisible(true);
         frame.setLocationRelativeTo(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         frame.setSize(new Dimension(1200,800));
         frame.setLocation(0, 40);
         
      } catch (MalformedURLException e) {
         e.printStackTrace();
      }
   }
}

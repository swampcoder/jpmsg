package org.particl.rabbitmq.testui;

import java.net.MalformedURLException;

import javax.swing.JFrame;

import org.particl.rabbitmq.ParticlSmsgUserNode;
import org.particl.rabbitmq.SmsgUserNodeMonitorThread;
import org.particl.rpc.core.ParticlJSONRPCClient;

public class LocalTester {

   private static ParticlSmsgUserNode userNode = null;
   public static void main(String[] args) 
   {
      try {
         ParticlJSONRPCClient rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
         
         userNode = new ParticlSmsgUserNode(rpc);
         
         MultiUserGridPanel testPanel = new MultiUserGridPanel(userNode);
         
         JFrame frame = new JFrame();
         frame.setAlwaysOnTop(true);
         frame.setTitle("Particl SMSG Node Tester");
         frame.getContentPane().add(testPanel);
         frame.pack();
         frame.setVisible(true);
         frame.setLocationRelativeTo(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      } catch (MalformedURLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}

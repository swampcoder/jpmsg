package org.particl.ui.smsg;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.particl.rpc.core.ParticlJSONRPCClient;

public class SmsgClient {

   private final ParticlJSONRPCClient client;
   private JFrame clientFrame = new JFrame("Particl SMSG Client");
   private SmsgViewPanel smsgPanel;
   private SmsgClientStatusBarPanel statusPanel;
   public SmsgClient(ParticlJSONRPCClient client) 
   {
      this.client = client;
      smsgPanel = new SmsgViewPanel(client);
      statusPanel = new SmsgClientStatusBarPanel(client);
      JPanel contentPane = new JPanel();
      contentPane.setLayout(new BorderLayout());
      contentPane.add(smsgPanel, BorderLayout.CENTER);
      contentPane.add(statusPanel, BorderLayout.SOUTH);
      clientFrame.getContentPane().add(contentPane);
      
      clientFrame.pack();
      
      clientFrame.setVisible(true);
   }
   
   
   public static void main(String[] args) throws MalformedURLException 
   {
      ParticlJSONRPCClient rpc2 = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
      new SmsgClient(rpc2);
   }
   
}

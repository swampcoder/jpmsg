package org.particl.rabbitmq.testui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.particl.rabbitmq.ParticlSmsgUserNode;
import org.particl.rpc.core.ParticlJSONRPCClient;

public class MultiUserGridPanel extends JPanel {

   public MultiUserGridPanel(ParticlSmsgUserNode particl) 
   {
      setLayout(new GridLayout(2,2));
      
      for(int i = 0 ; i < 4; i++) 
      {
         String[] addr_pk = particl.createAddress();
         System.out.println("Created address " + addr_pk[0]);
         UserChatPanel userPanel = new UserChatPanel(particl, addr_pk[0]);
         add(userPanel);
      
      }
   }
}

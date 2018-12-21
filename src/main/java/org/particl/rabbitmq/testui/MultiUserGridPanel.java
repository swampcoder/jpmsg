package org.particl.rabbitmq.testui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.particl.rabbitmq.ParticlSmsgUserNode;
import org.particl.rpc.core.ParticlJSONRPCClient;

public class MultiUserGridPanel extends JPanel {

   public MultiUserGridPanel(ParticlSmsgUserNode... particl) 
   {
      setLayout(new GridLayout(1,2));
      for(ParticlSmsgUserNode node : particl) 
      {
         String[] addr_pk = node.createAddress();
         System.out.println("Created address " + addr_pk[0]);
         UserChatPanel userPanel = new UserChatPanel(node, addr_pk[0]);
         add(userPanel);
      }
      
   }
}

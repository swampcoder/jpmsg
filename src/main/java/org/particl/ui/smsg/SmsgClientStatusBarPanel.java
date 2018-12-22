package org.particl.ui.smsg;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.particl.rpc.core.ParticlJSONRPCClient;

public class SmsgClientStatusBarPanel extends JPanel {

   
   public SmsgClientStatusBarPanel(ParticlJSONRPCClient client) 
   {
      super();
      
      
      setPreferredSize(new Dimension(800,19));
      
      setBorder(BorderFactory.createLoweredBevelBorder());
      
      setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
      
      StatusLabel status = new StatusLabel("Core RPC");
      
      StatusLabel mpStatus = new StatusLabel("Market RPC");
      add(Box.createHorizontalStrut(10));
      add(status);
      add(Box.createHorizontalStrut(15));
      add(mpStatus);
      add(Box.createHorizontalGlue());
   }
}

package org.particl.ui.smsg;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

import javax.swing.JScrollPane;

import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;
import org.particl.ui.desktop.DesktopViewException;

public class SmsgOutboxView extends DesktopView {

   private final SmsgMessageTable inbox;

   public SmsgOutboxView(DesktopInputData input) throws DesktopViewException {
      super(input);

      setLayout(new BorderLayout());

      setTitle("SMSG Outbox");

      inbox = new SmsgMessageTable();
      
      add(new JScrollPane(inbox), BorderLayout.CENTER);
   }

   @Override
   protected void closeView() 
   {
      // todo
   }
}

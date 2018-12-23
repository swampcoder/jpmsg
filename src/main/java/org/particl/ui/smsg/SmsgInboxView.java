package org.particl.ui.smsg;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

import javax.swing.JScrollPane;

import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;
import org.particl.ui.desktop.DesktopViewException;

public class SmsgInboxView extends DesktopView {

   private ParticlJSONRPCClient rpc;

   private final SmsgMessageTable inbox;

   public SmsgInboxView(DesktopInputData input) throws DesktopViewException {
      super(input);

      try {
         rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
      } catch (MalformedURLException e) {
         throw new DesktopViewException("bad RPC url", e);
      }

      setLayout(new BorderLayout());

      setTitle("SMSG Inbox");

      inbox = new SmsgMessageTable(rpc);
      
      add(new JScrollPane(inbox), BorderLayout.CENTER);
   }

   @Override
   protected void closeView() 
   {
      // todo
   }
}

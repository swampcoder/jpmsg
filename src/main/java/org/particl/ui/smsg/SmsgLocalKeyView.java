package org.particl.ui.smsg;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

import javax.swing.JScrollPane;

import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;
import org.particl.ui.desktop.DesktopViewException;

public class SmsgLocalKeyView extends DesktopView {

   private final ParticlJSONRPCClient rpc;
   private final SmsgKeyTable keyTable;
   
   public SmsgLocalKeyView(DesktopInputData viewData) throws DesktopViewException 
   {
      super(viewData);
      
      try {
         rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
      } catch (MalformedURLException e) {
         throw new DesktopViewException("bad RPC url", e);
      }
      
      keyTable = new SmsgKeyTable();
   
      add(new JScrollPane(keyTable), BorderLayout.CENTER);
      
      setTitle("SMSG Local Keys");
   }

   @Override
   protected void closeView() {

      
   }
}

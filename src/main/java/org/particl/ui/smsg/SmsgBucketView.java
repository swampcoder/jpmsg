package org.particl.ui.smsg;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

import javax.swing.JScrollPane;

import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;
import org.particl.ui.desktop.DesktopViewException;

public class SmsgBucketView extends DesktopView {

   private final ParticlJSONRPCClient rpc;
   private SmsgBucketTable table = null;

   public SmsgBucketView(DesktopInputData input) throws DesktopViewException {
      super(input);

      try {
         rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
      } catch (MalformedURLException e) {
         throw new DesktopViewException("bad RPC url", e);
      }
      
      table = new SmsgBucketTable();
      add(new JScrollPane(table), BorderLayout.CENTER);
      setTitle("SMSG Bucket Statistics");
   }

   @Override
   protected void closeView() {

   }

}

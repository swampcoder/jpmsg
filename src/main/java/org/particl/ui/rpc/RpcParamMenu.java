package org.particl.ui.rpc;

import javax.swing.JMenu;

import org.particl.ui.menu.DynamicMenu;

public class RpcParamMenu extends DynamicMenu {

   public RpcParamMenu( ) {
      super("RPC Config");
   }

   @Override
   protected void buildMenu() {
      
      JMenu marketRPC = new JMenu("Market RPC Configuration");

      
      JMenu coreRPC = new JMenu("Core RPC Configuration");
      
      
      add(coreRPC);
      add(marketRPC);
   }

}

package org.particl.ui.smsg;

import org.particl.mapdb.IMapDbMonitor;

public class RpcConnectionStateMonitor implements IMapDbMonitor {

   public RpcConnectionStateMonitor() 
   {
      super();
   }

   @Override
   public boolean monitorTriggered() {

      return false;
   }

   @Override
   public void handleMonitorTriggered() {
      
      
   }
}

package org.particl.mapdb;

// implementation of a monitor 
public interface IMapDbMonitor {

   public boolean monitorTriggered();
  
   public void handleMonitorTriggered();
}

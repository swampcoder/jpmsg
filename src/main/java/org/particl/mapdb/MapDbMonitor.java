package org.particl.mapdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.particl.app.Application;
import org.particl.app.IAppService;

public class MapDbMonitor implements IAppService {

   static 
   {
      MapDbMonitor dbMonitor = new MapDbMonitor();
      Application.initService(dbMonitor, MapDbMonitor.class);
   }
   
   private final Map<EMonitorRateOption, Timer> monitorThreads = new HashMap<EMonitorRateOption, Timer>();
   private final Map<EMonitorRateOption, List<IMapDbMonitor>> monitors = new HashMap<EMonitorRateOption, List<IMapDbMonitor>>();
   private MapDbMonitor( )
   {
      for(EMonitorRateOption rate : EMonitorRateOption.values()) 
      {
         Timer monitorThread = new Timer("mapdb monitor rate=" + rate.getRateMs() + " ms");
         monitorThreads.put(rate, monitorThread);
         monitors.put(rate, new ArrayList<IMapDbMonitor>());
         monitorThread.schedule(new TimerTask() {
            @Override
            public void run()
            {
               monitorImpl(rate);
            }
         }, 0L, rate.getRateMs());
      }
   }
   
   public void addMapDbMonitor(EMonitorRateOption rateOption, IMapDbMonitor monitor) 
   {
      List<IMapDbMonitor> monitorList = monitors.get(rateOption);
      synchronized(monitorList) 
      {
         monitorList.add(monitor);
      }
   }
   
   public void removeMapDbMonitor(EMonitorRateOption rateOption, IMapDbMonitor monitor) 
   {
      List<IMapDbMonitor> monitorList = monitors.get(rateOption);
      synchronized(monitorList) 
      {
         if(!monitorList.remove(monitor))
         {
            // log warning - could have provided wrong rate option
         }
      }
   }
   
   private void monitorImpl(EMonitorRateOption rateOption)
   {
      List<IMapDbMonitor> monitorList = monitors.get(rateOption);
      synchronized(monitorList) 
      {
         for(IMapDbMonitor monitor : monitorList)
         {
            if(monitor.monitorTriggered())
            {
               monitor.handleMonitorTriggered();
            }
         }
      }
   }
}

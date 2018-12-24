package org.particl.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import org.particl.app.Application;
import org.particl.app.IAppService;

public class DisplayTimer implements IAppService  {

   static
   {
      DisplayTimer tasker = new DisplayTimer();
      Application.initService(tasker, DisplayTimer.class);
   }
   
   public static interface IDisplayTask 
   {
      public void invoke(Duration duration);
   }
   
   public static enum Duration 
   {
      One(1000L),
      Two(2000L),
      Five(5000L),
      Ten(10000L),
      Thirty(30000L);
      
      private final long rateMs;
      private Duration(long rateMs)
      {
         this.rateMs = rateMs;
      }
   }
   
   private final Map<Duration, List<IDisplayTask>> durationTasks = new HashMap<Duration, List<IDisplayTask>>();
   //private final Map<Duration, Timer> durationTimers = new HashMap<Duration, Timer>();
   
   private DisplayTimer() 
   {
      for(Duration duration : Duration.values()) 
      {
         Timer timer = new Timer("DisplayTimer duration=" + duration.name());
         timer.schedule(new TimerTask() {
            @Override
            public void run() 
            {
               final List<IDisplayTask> tasks = durationTasks.get(duration);
               if(tasks.size() > 0) 
               {
                  SwingUtilities.invokeLater(new Runnable() {
                     @Override
                     public void run() 
                     {
                        for(IDisplayTask task : tasks)
                        {
                           task.invoke(duration);
                        }
                     }
                  });
               }
            }
         }, 0L, duration.rateMs);
      }
   }
   
   public void registerTask(IDisplayTask task, Duration duration)
   {
      durationTasks.get(duration).add(task);
   }
   
   public void unregisterTask(IDisplayTask task, Duration duration) 
   {
      durationTasks.get(duration).remove(task);
   }
}

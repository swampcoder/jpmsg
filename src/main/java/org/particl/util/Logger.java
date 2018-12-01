package org.particl.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.impl.Log4JLogger;

// todo
public class Logger {

   private final static Map<Class<?>, Log4JLogger> Loggers = new HashMap<Class<?>, Log4JLogger>();
   static {
      // init Logger.class log4jlogger
   }
   
   public synchronized static Logger get(Object source) 
   {
      if(source == null)
      {
         
      }
      return null;
   }
   
   private static Log4JLogger initLogger(Class<?> clazz)
   {
      return null;
   }
   
   private Logger() {} 
}

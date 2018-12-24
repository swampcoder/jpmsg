package org.particl.app;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.particl.mapdb.MapDbCache;
import org.particl.ui.desktop.AppFrame;

public class Application {

   private static Boolean initialized = false;
   private static boolean dbInitialized = false;
   private static AppFrame appFrame = null;
   private static MapDbCache db = null;
   private final static Map<Class<? extends IAppService>, IAppService> appServices = 
         new HashMap<Class<? extends IAppService>, IAppService>();
   
   public static MapDbCache getdb() 
   {
      return db;
   }

   @SuppressWarnings("unchecked")
   public static <S extends IAppService> S getService(Class<S> serviceClass)
   {
      initialize();
      forceInit(serviceClass);
      return (S) appServices.get(serviceClass);
   }
   
   public static <S extends IAppService> boolean initService(S service, Class<S> serviceClass) 
   {
      initialize();
      
      return appServices.put(serviceClass, service) == null;
   }

   public static AppFrame frame() 
   {
      initialize();
      return appFrame;
   }
   
   public static void initialize() 
   {
      if(!initialized) 
      {
         synchronized(initialized) 
         {
            if(!initialized) 
            {
               appFrame = new AppFrame();
               initialized = true;
            }
         }
      }
   }
   
   public static synchronized boolean initializeDb(IDbResolver resolver) throws IOException 
   {
      File dbLocation = new File("/home/mint/mapdb_app.db"); //resolver.getDbLocation();
      if(dbLocation == null) 
      {
         return false;
      }
      
      db = new MapDbCache(dbLocation, 50*1024*1024);
      
      return true;
   }
   
   public static <T> Class<T> forceInit(Class<T> klass) {
      try {
          Class.forName(klass.getName(), true, klass.getClassLoader());
      } catch (ClassNotFoundException e) {
          throw new AssertionError(e);  // Can't happen
      }
      return klass;
  } 

   
   private Application() 
   {}

}


package org.particl.app;

import java.util.HashMap;
import java.util.Map;

import org.particl.mapdb.MapDbCache;
import org.particl.ui.desktop.AppFrame;

public class Application {

   private static boolean initialized = false;
   private static AppFrame appFrame = null;
   private final static Map<String, MapDbCache> dbs = new HashMap<String, MapDbCache>();
   private final static Map<Class<? extends IAppService>, IAppService> appServices = 
         new HashMap<Class<? extends IAppService>, IAppService>();
   
   public static MapDbCache getdb(String dbName) 
   {
      initialize();
      synchronized(dbs) 
      {
         MapDbCache db = dbs.get(dbName);
         if(db == null) 
         {
            db = new MapDbCache();
            dbs.put(dbName, db);
         }
         return db;
      }
   }
   
   @SuppressWarnings("unchecked")
   public static <S extends IAppService> S getService(Class<S> serviceClass)
   {
      initialize();
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
   
   public static synchronized void initialize() 
   {
      if(!initialized) 
      {
         appFrame = new AppFrame();
         initialized = true;
      }
   }
   
   private Application() 
   {}

}


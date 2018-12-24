package org.particl.rpc.mp;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mapdb.HTreeMap;
import org.particl.app.Application;
import org.particl.app.IAppService;
import org.particl.rpc.core.ParticlJSONRPCClient;

public class ParticlConnection implements IAppService {

   // defaults
   private static String corehost = "localhost";
   private static String coreuser = "particl";
   private static String corepassword = "password";
   private static Integer coreport = 22222;
   
   private static String mphost = "localhost";
   private static String mpuser = "test";
   private static String mppassword = "test";
   private static Integer mpport = 3000;
   
   public final static String ConnectionDbMapName = "ParticlConnectParams";
   static 
   {
      Application.initService(new ParticlConnection(), ParticlConnection.class);

      HTreeMap<String, Integer> paramMapInt = Application.getdb().intmap(ConnectionDbMapName);
      HTreeMap<String, String> paramMapStr = Application.getdb().stringmap(ConnectionDbMapName);

      paramMapStr.putIfAbsent("corehost", corehost);
      paramMapStr.putIfAbsent("coreuser", coreuser);
      paramMapStr.putIfAbsent("corepassword",  corepassword);
      paramMapInt.putIfAbsent("coreport", coreport);
      
      paramMapStr.putIfAbsent("mphost", mphost);
      paramMapStr.putIfAbsent("mpuser", mpuser);
      paramMapStr.putIfAbsent("mppassword",  mppassword);
      paramMapInt.putIfAbsent("mpport", mpport);
   }
   
   public static void init( ) {}
   
   private static final int MAX_CLIENTS = 20;
   private static final Random indexer = new Random();
  
   
   private final List<ParticlJSONRPCClient> core_clients = new ArrayList<ParticlJSONRPCClient>();
   private final List<ParticlMarketApi> mp_clients = new ArrayList<ParticlMarketApi>();
   
   private ParticlConnection() 
   {
      super();
   }
   
   public ParticlJSONRPCClient core() 
   {
      synchronized(core_clients)
      {
         if(core_clients.size() < MAX_CLIENTS) 
         {
            ParticlJSONRPCClient core;
            try {
               
               HTreeMap<String, Integer> paramMapInt = Application.getdb().intmap(ConnectionDbMapName);
               HTreeMap<String, String> paramMapStr = Application.getdb().stringmap(ConnectionDbMapName);
               
               core = new ParticlJSONRPCClient(paramMapStr.get("corehost"), paramMapStr.get("coreuser"), 
                     paramMapStr.get("corepassword"), paramMapInt.get("coreport"));
               
            } catch (MalformedURLException e) {
               throw new IllegalStateException("malformed url bug");
            }
            core_clients.add(core);
            return core;
         }
         else
         {
            return core_clients.get(indexer.nextInt(MAX_CLIENTS));
         }  
      }
   }
   
   public ParticlMarketApi market() 
   {
      synchronized(mp_clients) 
      {
         if(mp_clients.size() < MAX_CLIENTS) 
         {
            ParticlMarketApi mp;
            try {
               
               HTreeMap<String, Integer> paramMapInt = Application.getdb().intmap(ConnectionDbMapName);
               HTreeMap<String, String> paramMapStr = Application.getdb().stringmap(ConnectionDbMapName);
               
               mp = new ParticlMarketApi(paramMapStr.get("mphost"), paramMapInt.get("mpport"), 
                     paramMapStr.get("mpuser"), paramMapStr.get("mppassword"), null);
            } catch (MalformedURLException e) {
               throw new IllegalStateException("malformed url bug");
            }
            mp_clients.add(mp);
            return mp;
         }
         else
         {
            return mp_clients.get(indexer.nextInt(MAX_CLIENTS));
         }
      }
   }

   public void updateCoreParams(String host, String user, String password, int port) 
   {
      Utils.notNull(host,user,password);
      synchronized(core_clients)
      {
         HTreeMap<String, Integer> paramMapInt = Application.getdb().intmap(ConnectionDbMapName);
         HTreeMap<String, String> paramMapStr = Application.getdb().stringmap(ConnectionDbMapName);
         core_clients.clear();
         paramMapStr.put("corehost", host);
         paramMapStr.put("coreuser", user);
         paramMapStr.put("corepassword",  password);
         paramMapInt.put("coreport", port);
      }
      
   }
   
   public void updateMarketParams(String host, String user, String password, int port) 
   {
      Utils.notNull(host,user,password);
      synchronized(mp_clients) 
      {
         HTreeMap<String, Integer> paramMapInt = Application.getdb().intmap(ConnectionDbMapName);
         HTreeMap<String, String> paramMapStr = Application.getdb().stringmap(ConnectionDbMapName);
         mp_clients.clear();
         paramMapStr.put("mphost", host);
         paramMapStr.put("mpuser", user);
         paramMapStr.put("mppassword",  password);
         paramMapInt.put("mpport", port);
      }
   }

}

package org.particl.rpc.mp;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.particl.app.Application;
import org.particl.app.IAppService;
import org.particl.rpc.core.ParticlJSONRPCClient;

public class ParticlConnection implements IAppService {

   static 
   {
      Application.initService(new ParticlConnection(), ParticlConnection.class);
   }
   private static final int MAX_CLIENTS = 20;
   private static final Random indexer = new Random();
   private String corehost = "localhost";
   private String coreuser = "particl";
   private String corepassword = "password";
   private Integer coreport = 22222;
   
   private String mphost = "localhost";
   private String mpuser = "test";
   private String mppassword = "test";
   private Integer mpport = 3000;
   
   private final List<ParticlJSONRPCClient> core_clients = new ArrayList<ParticlJSONRPCClient>();
   private final List<ParticlMarketApi> mp_clients = new ArrayList<ParticlMarketApi>();
   
   private ParticlConnection() 
   {
      super();
   }
   
   public synchronized ParticlJSONRPCClient core() 
   {
      if(core_clients.size() < MAX_CLIENTS) 
      {
         ParticlJSONRPCClient core;
         try {
            core = new ParticlJSONRPCClient(corehost, coreuser, corepassword,coreport);
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
   
   public synchronized ParticlMarketApi market() 
   {
      if(mp_clients.size() < MAX_CLIENTS) 
      {
         ParticlMarketApi mp;
         try {
            mp = new ParticlMarketApi(mphost, mpport, mpuser, mppassword, null);
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

   public synchronized void updateCoreParams(String host, String user, String password, int port) 
   {
      core_clients.clear();
      this.corehost = host;
      this.coreuser = user;
      this.corepassword = password;
      this.coreport = port;
      
   }
   
   public synchronized void updateMpEndpoint(String host, String user, String password, int port) 
   {
      mp_clients.clear();
      this.mphost = host;
      this.mpuser = user;
      this.mppassword = password;
      this.mpport = port;
   }

}

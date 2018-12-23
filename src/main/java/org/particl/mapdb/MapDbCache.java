package org.particl.mapdb;

import org.mapdb.DB;
import org.mapdb.DBMaker;

// wrapper for map db functions 
public class MapDbCache {

   private DB db = null;
   
   public MapDbCache() 
   {
      super();
      
      db= DBMaker
            .fileDB("/home/mint/db.test")
            .allocateStartSize(1024L*1024L*25L)
            //TODO encryption API
            //.encryptionEnable("password")
            .make();
   }
   
   public DB db() 
   {
      return db;
   }
}

package org.particl.mapdb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

// wrapper for map db functions 
public class MapDbCache {

   private DB db = null;

   private final Map<String, HTreeMap<String, String>> htree_string = new HashMap<String, HTreeMap<String, String>>();
   private final Map<String, HTreeMap<String, Integer>> htree_int = new HashMap<String, HTreeMap<String, Integer>>();

   public MapDbCache(File dbfile, long size) throws IOException {
      super();

      if(dbfile.length() == 0L) dbfile.delete();
      System.out.println("DB FILE: " + dbfile);
      //dbDir.createNewFile();
      db = DBMaker.fileDB(dbfile).allocateStartSize(size)
            // TODO encryption API
            // .encryptionEnable("password")
            .make();
   }

   public DB db() {
      return db;
   }

   public synchronized HTreeMap<String, String> stringmap(String name) {
      HTreeMap<String, String> string_map = htree_string.get(name);
      if (string_map == null) {
         string_map = db.hashMap(name).keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen();
         htree_string.put(name, string_map);
      }
      return string_map;
   }
   
   public synchronized HTreeMap<String, Integer> intmap(String name) {
      HTreeMap<String, Integer> intmap = htree_int.get(name);
      if (intmap == null) {
         intmap = db.hashMap(name).keySerializer(Serializer.STRING).valueSerializer(Serializer.INTEGER).createOrOpen();
         htree_int.put(name, intmap);
      }
      return intmap;
   }
}

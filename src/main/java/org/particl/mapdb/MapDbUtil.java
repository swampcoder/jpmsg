package org.particl.mapdb;

import java.io.File;
import java.io.IOException;

public class MapDbUtil {

   private final static String TmpDir = System.getProperty("java.io.tmpdir");
   private final static File TmpDirFile;
   static 
   {
      System.out.println("TMP DIR: " + TmpDir);
      if(TmpDir == null || !(new File(TmpDir)).exists())
      {
         throw new ExceptionInInitializerError("java.io.tmpdir does not exist. value=" + TmpDir);
      }
      
      TmpDirFile = new File(TmpDir + File.separator + "default.mapdb2");
   }

   public static File getDefaultMapDb() throws IOException 
   {
      boolean defaultFileGen = TmpDirFile.createNewFile();
      if(defaultFileGen) 
      {
         // log creation 
      }
      return TmpDirFile;
   }

   private MapDbUtil() {} 
}

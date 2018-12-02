package org.particl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartUtil {

   private final static DateFormat ParticlRpcFormat = new SimpleDateFormat("");
   
   public static void assertNotNull(Object... objects) {
      for (Object o : objects) {
         if (o == null) {
            throw new IllegalArgumentException("Object cannot be null");
         }
      }
   }
   
   public static String particlTimeToString(long time)
   {
      Date utc = new Date(time);
      
      return ParticlRpcFormat.format(utc);
   }
   
   public static long particlStringToTime(String timeStr)
   {
      return 0L;
   }

   private PartUtil() {
   }
}

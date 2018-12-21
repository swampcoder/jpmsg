package org.particl.rpc.mp;

public class Utils {

   public static void notNull(Object...objects)
   {
      for(int i = 0; i < objects.length; i++) 
      {
         if(objects[i] == null) throw new IllegalArgumentException("object cannot be null - index=" + i);
      }
   }
}

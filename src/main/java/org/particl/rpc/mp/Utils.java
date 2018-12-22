package org.particl.rpc.mp;

import javax.swing.SwingUtilities;

public class Utils {

   public static void notNull(Object... objects) {
      for (int i = 0; i < objects.length; i++) {
         if (objects[i] == null)
            throw new IllegalArgumentException("object cannot be null - index=" + i);
      }
   }

   public static void assertTrue(String msg, boolean test) {
      if (!test) {
         throw new IllegalStateException("Assertion Failed: " + msg);
      }
   }

   public static void assertFalse(String msg, boolean test) {
      if (test) {
         throw new IllegalStateException("Assertion Failed: " + msg);
      }
   }

   public static void assertSwingThread() {
      if (!SwingUtilities.isEventDispatchThread()) {
         throw new IllegalStateException("Thread must be swing!");
      }
   }

   private Utils() {
   }
}

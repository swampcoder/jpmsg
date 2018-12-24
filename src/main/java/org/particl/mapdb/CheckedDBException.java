package org.particl.mapdb;

public class CheckedDBException extends Exception {
   
   public CheckedDBException(RuntimeException runtimeEx)
   {
      super(runtimeEx);
   }

}

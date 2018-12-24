package org.particl.mapdb;

public enum EMonitorRateOption {

   ONE_SECOND(1),
   TWO_SECOND(2),
   FIVE_SECOND(5),
   TEN_SECOND(10),
   THIRTY_SECOND(30),
   ONE_MINUTE(60);
   
   private final long rateMs;
   private EMonitorRateOption(int seconds)
   {
      this.rateMs = seconds*1000L;
   }
   
   public long getRateMs() 
   {
      return rateMs;
   }
}

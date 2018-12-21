package org.particl.rpc.core.smsg;

public class SmsgBucket {

   private final long bucketId;
   private Long time = null;
   private Integer numMessages = null;
   private Integer activeMessages = null;
   private Long hash = null;
   private String lastChangeTime = null;
   private Double bucketSizeKb = null;
   
   public SmsgBucket(long bucketId) {
      this.bucketId = bucketId;
   }
   
   @Override
   public String toString() 
   {
      return "SmsgBucket id=" + bucketId + " time=" + time + " # messages=" + numMessages + " active messages=" + activeMessages +
            " hash=" + hash + " lastChangeTime=" + lastChangeTime + " bucketSizeKb=" + bucketSizeKb;
   }
   
   @Override
   public int hashCode() 
   {
      return Long.hashCode(bucketId);
   }
   
   @Override
   public boolean equals(Object cmp)
   {
      if(cmp instanceof SmsgBucket)
      {
         SmsgBucket bucket = (SmsgBucket) cmp;
         return bucket.getBucketId() == getBucketId();
      }
      return false;
   }

   public Long getBucketId() {
      return bucketId;
   }

   public Long getTime() {
      return time;
   }

   public Integer getNumMessages() {
      return numMessages;
   }

   public Integer getActiveMessages() {
      return activeMessages;
   }

   public Long getHash() {
      return hash;
   }

   public String getLastChangeTime() {
      return lastChangeTime;
   }

   public Double getBucketSizeKb() {
      return bucketSizeKb;
   }

   void setTime(Long time) {
      this.time = time;
   }

   void setNumMessages(Integer numMessages) {
      this.numMessages = numMessages;
   }

   void setActiveMessages(Integer activeMessages) {
      this.activeMessages = activeMessages;
   }

   void setHash(Long hash) {
      this.hash = hash;
   }

   void setLastChangeTime(String lastChangeTime) {
      this.lastChangeTime = lastChangeTime;
   }

   void setBucketSizeKb(Double bucketSizeKb) {
      this.bucketSizeKb = bucketSizeKb;
   }
   
   
}

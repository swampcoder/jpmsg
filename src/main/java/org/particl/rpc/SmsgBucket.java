package org.particl.rpc;

public class SmsgBucket {

   private Long bucketId = null;
   private Long time = null;
   private Integer numMessages = null;
   private Integer activeMessages = null;
   private Integer hash = null;
   private String lastChangeTime = null;
   private Double bucketSizeKb = null;
   
   public SmsgBucket() {}
   
   @Override
   public String toString() 
   {
      return "SmsgBucket id=" + bucketId + " time=" + time + " # messages=" + numMessages + " active messages=" + activeMessages +
            " hash=" + hash + " lastChangeTime=" + lastChangeTime + " bucketSizeKb=" + bucketSizeKb;
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

   public Integer getHash() {
      return hash;
   }

   public String getLastChangeTime() {
      return lastChangeTime;
   }

   public Double getBucketSizeKb() {
      return bucketSizeKb;
   }

   void setBucketId(Long bucketId) {
      this.bucketId = bucketId;
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

   void setHash(Integer hash) {
      this.hash = hash;
   }

   void setLastChangeTime(String lastChangeTime) {
      this.lastChangeTime = lastChangeTime;
   }

   void setBucketSizeKb(Double bucketSizeKb) {
      this.bucketSizeKb = bucketSizeKb;
   }
   
   
}

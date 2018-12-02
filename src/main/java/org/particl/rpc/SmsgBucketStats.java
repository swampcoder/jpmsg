package org.particl.rpc;

import java.util.ArrayList;
import java.util.List;

public class SmsgBucketStats {

   private final List<SmsgBucket> buckets = new ArrayList<SmsgBucket>();
   private Integer numBuckets = null;
   private Integer numPurged = null;
   private Integer numMessages = null;
   private Double totalSizeKb = null;

   public SmsgBucketStats() {
      super();
   }

   public Integer getNumBuckets() {
      return numBuckets;
   }

   public Integer getNumPurged() {
      return numPurged;
   }

   public Integer getNumMessages() {
      return numMessages;
   }

   public Double getTotalSizeKb() {
      return totalSizeKb;
   }

   public List<SmsgBucket> getBuckets() {
      return buckets;
   }

   @Override
   public String toString() {
      StringBuilder debug = new StringBuilder();
      debug.append("SMSG Bucket Statistics\n");
      for (SmsgBucket bucket : buckets) {
         debug.append(bucket + "\n");
      }
      return debug.toString();
   }

   void addBucket(SmsgBucket bucket) {
      buckets.add(bucket);
   }

   void setNumBuckets(Integer numBuckets) {
      this.numBuckets = numBuckets;
   }

   void setNumPurged(Integer numPurged) {
      this.numPurged = numPurged;
   }

   void setNumMessages(Integer numMessages) {
      this.numMessages = numMessages;
   }

   void setTotalSizeKb(Double totalSizeKb) {
      this.totalSizeKb = totalSizeKb;
   }

}

package org.particl.rpc.mp.dto;

public class CurrencyPrice {

   private Integer id = null;
   private String from = null;
   private String to = null;
   private Double price = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   public CurrencyPrice() {}

   public Integer getId() {
      return id;
   }

   public String getFrom() {
      return from;
   }

   public String getTo() {
      return to;
   }

   public Double getPrice() {
      return price;
   }

   public Long getUpdatedAt() {
      return updatedAt;
   }

   public Long getCreatedAt() {
      return createdAt;
   }
   
   
}

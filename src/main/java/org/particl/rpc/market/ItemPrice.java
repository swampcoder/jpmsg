package org.particl.rpc.market;

public class ItemPrice {

   private Integer id = null;
   private String currency = null;
   private Double basePrice = null;
   private Integer paymentInformationId = null;
   private String cryptocurrencyAddress = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private ShippingPrice shippingPrice = null;
   
   public ItemPrice() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public String getCurrency() {
      return currency;
   }

   public Double getBasePrice() {
      return basePrice;
   }

   public Integer getPaymentInformationId() {
      return paymentInformationId;
   }

   public String getCryptocurrencyAddress() {
      return cryptocurrencyAddress;
   }

   public Long getUpdatedAt() {
      return updatedAt;
   }

   public Long getCreatedAt() {
      return createdAt;
   }

   public ShippingPrice getShippingPrice() {
      return shippingPrice;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setCurrency(String currency) {
      this.currency = currency;
   }

   void setBasePrice(Double basePrice) {
      this.basePrice = basePrice;
   }

   void setPaymentInformationId(Integer paymentInformationId) {
      this.paymentInformationId = paymentInformationId;
   }

   void setCryptocurrencyAddress(String cryptocurrencyAddress) {
      this.cryptocurrencyAddress = cryptocurrencyAddress;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }

   void setShippingPrice(ShippingPrice shippingPrice) {
      this.shippingPrice = shippingPrice;
   }
   
   
}

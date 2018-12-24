package org.particl.rpc.mp.dto;

public class PriceTicker {

   private Integer id;
   private String cryptoId;
   private String cryptoSymbol;
   private Integer cryptoRank;
   private Double cryptoPriceUsd;
   private Double cryptoPriceBtc;
   private Double crypto24HVolumeUSD;
   private Double cryptoMarketCapUsd;
   private Double cryptoAvailableSupply;
   private Double cryptoTotalSupply;
   private Double cryptoMaxSupply;
   private Double cryptoPercentChange1H;
   private Double cryptoPercentChange24H;
   private Double cryptoPercentChange7D;
   private Long cryptoLastUpdated;
   private Double cryptoPriceEur;
   private Double crypto24HVolumeEur;
   private Double cryptoMarketCapEur;
   
   public PriceTicker() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public String getCryptoId() {
      return cryptoId;
   }

   public String getCryptoSymbol() {
      return cryptoSymbol;
   }

   public Integer getCryptoRank() {
      return cryptoRank;
   }

   public Double getCryptoPriceUsd() {
      return cryptoPriceUsd;
   }

   public Double getCryptoPriceBtc() {
      return cryptoPriceBtc;
   }

   public Double getCrypto24HVolumeUSD() {
      return crypto24HVolumeUSD;
   }

   public Double getCryptoMarketCapUsd() {
      return cryptoMarketCapUsd;
   }

   public Double getCryptoAvailableSupply() {
      return cryptoAvailableSupply;
   }

   public Double getCryptoTotalSupply() {
      return cryptoTotalSupply;
   }

   public Double getCryptoMaxSupply() {
      return cryptoMaxSupply;
   }

   public Double getCryptoPercentChange1H() {
      return cryptoPercentChange1H;
   }

   public Double getCryptoPercentChange24H() {
      return cryptoPercentChange24H;
   }

   public Double getCryptoPercentChange7D() {
      return cryptoPercentChange7D;
   }

   public Long getCryptoLastUpdated() {
      return cryptoLastUpdated;
   }

   public Double getCryptoPriceEur() {
      return cryptoPriceEur;
   }

   public Double getCrypto24HVolumeEur() {
      return crypto24HVolumeEur;
   }

   public Double getCryptoMarketCapEur() {
      return cryptoMarketCapEur;
   }
   
   
}

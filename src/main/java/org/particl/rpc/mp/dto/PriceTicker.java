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
   
   public PriceTicker() 
   {
      super();
   }
}

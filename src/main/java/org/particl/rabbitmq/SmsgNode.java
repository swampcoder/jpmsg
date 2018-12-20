package org.particl.rabbitmq;

import org.particl.rpc.core.ParticlJSONRPCClient;

public class SmsgNode {

   protected final ParticlJSONRPCClient particl;
   protected SmsgNode(ParticlJSONRPCClient particl) 
   {
      this.particl = particl;
   }
   
   public String[] createAddress() 
   {

      //particlRPC.getSMSG().enable("wallet.dat");
      String newReceiveAddress = particl.getNewAddress();
      String pubkey = particl.getSMSG().getpubkey(newReceiveAddress);
      boolean result = particl.getSMSG().addaddress(newReceiveAddress, pubkey);
      result = result && particl.getSMSG().addlocaladdress(newReceiveAddress);
      if(!result) 
      {
         return null;
      }
      return new String[] {newReceiveAddress, pubkey};
   }
}

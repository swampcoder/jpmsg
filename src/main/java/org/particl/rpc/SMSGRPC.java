package org.particl.rpc;

import org.particl.util.PrivateKey;
import org.particl.util.PubKey;

public class SMSGRPC {

   public void addAddress(ParticlAddress address, PubKey pubKey) throws ParticlRpcException {
   }

   public void addLocalAddress(ParticlAddress address, PubKey pubKey) throws ParticlRpcException {
   }

   public void buckets(EBucketOption option) throws ParticlRpcException {
   }

   public void disable() throws ParticlRpcException {
   }

   public void enable(String walletName) throws ParticlRpcException {
   }

   public PubKey getPubKey(ParticlAddress address) throws ParticlRpcException {
      return null;
   }

   public void importPrivateKey(PrivateKey privateKey) throws ParticlRpcException {
   }

   public void inbox() throws ParticlRpcException {
   }

   public void purge(SmsgId id) throws ParticlRpcException {
   }

   public void scanBuckets() throws ParticlRpcException {
   }

   public void scanchain() throws ParticlRpcException {
   }

   public void send(ParticlAddress from, ParticlAddress to, boolean paid, double daysRetention)
         throws ParticlRpcException {
   }

   public void sendAnon(ParticlAddress from, ParticlAddress to) throws ParticlRpcException {
   }

   public void view(String addressLabel, long startTime, long stopTime) throws ParticlRpcException {
   };

}
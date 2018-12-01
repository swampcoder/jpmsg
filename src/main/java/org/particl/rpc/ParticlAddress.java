package org.particl.rpc;

import org.particl.crypto.PubKey;

public class ParticlAddress {

   private final String address;
   private final PubKey pubKey;

   public ParticlAddress(String address) {
      super();
      this.address = address;
      pubKey = null;
   }

   public String getAddress() {
      return address;
   }

   public PubKey getPubKey() {
      return pubKey;
   }

   @Override
   public String toString() {
      return address;
   }

   @Override
   public int hashCode() {
      return address.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof ParticlAddress) {
         ParticlAddress partAddr = (ParticlAddress) obj;
         return partAddr.address.equals(partAddr.address); // case?
      }
      return false;
      // also allow strings?
   }
}

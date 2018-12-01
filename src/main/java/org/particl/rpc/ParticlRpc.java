package org.particl.rpc;

import java.util.List;

public class ParticlRpc extends RPCClient {

   private SMSGRPC smsgRpc = null;

   public ParticlRpc() {
      smsgRpc = new SMSGRPC();
   }

   public List<String> listWallets() throws ParticlRpcException {
      return null;
   }

   public SMSGRPC getSMSG() {
      return null;
   }
}

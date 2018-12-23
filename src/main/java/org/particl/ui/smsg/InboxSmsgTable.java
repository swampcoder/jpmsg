package org.particl.ui.smsg;

import java.util.UUID;

import org.particl.rpc.core.IParticlCore;
import org.particl.rpc.core.smsg.SmsgLocation;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.rpc.core.smsg.SmsgSendFailException;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class InboxSmsgTable extends SmsgMessageTable {

   public InboxSmsgTable(IParticlCore rpc) {
      super(rpc);
      setLocationFilter(SmsgLocation.Inbox);
   }

   
   @Override
   protected void handleDoubleClick(SmsgMessage msg) 
   {
      try {
         rpc().getSMSG().send(msg.getFromAddress(), msg.getToAddress(), UUID.randomUUID().toString());
      } catch (BitcoinRPCException | SmsgSendFailException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   
}

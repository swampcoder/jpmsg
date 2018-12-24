package org.particl.ui.smsg;

import java.util.UUID;

import org.particl.app.Application;
import org.particl.rpc.core.IParticlCore;
import org.particl.rpc.core.smsg.SmsgLocation;
import org.particl.rpc.core.smsg.SmsgMessage;
import org.particl.rpc.core.smsg.SmsgSendFailException;
import org.particl.rpc.mp.ParticlConnection;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

public class InboxSmsgTable extends SmsgMessageTable {

   public InboxSmsgTable(IParticlCore rpc) {
      setLocationFilter(SmsgLocation.Inbox);
   }

   
   @Override
   protected void handleDoubleClick(SmsgMessage msg) 
   {
      try {
         Application.getService(ParticlConnection.class).core().getSMSG().send(
               msg.getFromAddress(),  msg.getToAddress(),  UUID.randomUUID().toString());
      } catch (BitcoinRPCException | SmsgSendFailException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   
}

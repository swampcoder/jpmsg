package org.particl.rpc;

import org.particl.model.ParticlMsg;

// interface for callbacks from polling threads in application
public interface IParticlRpcListener {

   public void notifyParticlMsg(ParticlMsg newMsg);

}

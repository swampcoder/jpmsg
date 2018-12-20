package org.particl.rabbitmq;

public interface IParticlUserNodeListener {

   public void notifyUserNodeMsg(NodeMsgType msgType, Object nodeObject);
}

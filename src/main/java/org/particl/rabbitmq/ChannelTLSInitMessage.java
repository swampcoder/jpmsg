package org.particl.rabbitmq;

// sent from the SMSG bootstrap node via the particl iface
// to the java user node where it is imported and used to secure the connection 
public class ChannelTLSInitMessage {

   
   private String tls_certificate = null;
   private String routing_key = null;
   
   public ChannelTLSInitMessage(String routing_key, String tls_certificate)
   {
      this.tls_certificate = tls_certificate;
   }
}

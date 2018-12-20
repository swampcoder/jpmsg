package org.particl.rabbitmq;

import org.apache.commons.codec.binary.Base64;
import org.particl.rpc.core.smsg.SmsgMessage;

public class SmsgMessageData {

   public final static int SMSG_ID_LENGTH = 4;
   public final static String SMSG_BASE64_NODE_HEADER = Base64.encodeBase64String("PARTICL_NODE_HEADER_TEXT".getBytes());
   public final static int FULL_HEADER_LEN = SMSG_ID_LENGTH + SMSG_BASE64_NODE_HEADER.length();
   private final SmsgMessage smsg;
   
   public SmsgMessageData(SmsgMessage smsg) 
   {
     this.smsg = smsg;
   }
   
   public boolean isNodeMsg() 
   {
      if(smsg.getMsgText().length() < FULL_HEADER_LEN) 
         return false;

      return true;
   }
   
   public String getMsgId() 
   {
      if(!isNodeMsg()) 
      {
         throw new IllegalStateException("Not a node msg can't call getMsgId()");
      }
      return smsg.getMsgText().substring(SMSG_BASE64_NODE_HEADER.length(), FULL_HEADER_LEN);
   }
   
   public String getData(boolean decode) 
   {
      if(!isNodeMsg()) 
      {
         throw new IllegalStateException("Not a node msg can't call getData()");
      }
      if(decode) {
         
         return new String(Base64.decodeBase64(smsg.getMsgText().substring(FULL_HEADER_LEN, smsg.getMsgText().length())));
      }
      else
      {
         return smsg.getMsgText().substring(FULL_HEADER_LEN, smsg.getMsgText().length());
      }
   }
}

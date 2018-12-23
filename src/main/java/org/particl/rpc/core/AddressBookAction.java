package org.particl.rpc.core;

public enum AddressBookAction {

   Add("add"),
   Edit("edit"),
   Delete("del"),
   Info("info"),
   NewSend("newsend");

   private final String cmd;
   private AddressBookAction(String cmd) 
   {
      this.cmd = cmd;
   }
   
   public String cmd_text() 
   {
      return cmd;
   }
}

package org.particl.rpc;

public enum ParticlCommand {

	listwallets,
	smsg,
	smsgaddaddress,
	smsgaddlocaladdress,
	smsgbuckets,
	smsgdisable,
	smsgenable,
	smsggetpubkey,
	smsgimportprivkey,
	smsginbox,
	smsglocalkeys,
	smsgoptions,
	smsgoutbox,
	smsgpurge,
	smsgscanbuckets,
	smsgscanchain,
	smsgsend,
	smsgsendanon,
	smsgview;
	
	
	public String getMethod() 
	{
		return name();
	}
}

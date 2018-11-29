package org.particl.model;

public class ParticlMsg {

	private long msgTime;
	private EParticlMsgType msgType;
	private ParticlUser sender = null;
	private ParticlUser recipient = null;
	private String msgContent;
	
	
	public ParticlMsg()
	{
		
	}

	public long getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(long msgTime) {
		this.msgTime = msgTime;
	}

	public ParticlUser getSender() {
		return sender;
	}

	public void setSender(ParticlUser sender) {
		this.sender = sender;
	}

	public ParticlUser getRecipient() {
		return recipient;
	}

	public void setRecipient(ParticlUser recipient) {
		this.recipient = recipient;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	@Override
	public String toString() 
	{
		return "ParticlMsg  type=" + msgType.name() + "  time=" + getMsgTime() + "  sender=" + getSender() + "  recipient=" + getRecipient() + "  msg=" + getMsgContent();
	}
	
	
}

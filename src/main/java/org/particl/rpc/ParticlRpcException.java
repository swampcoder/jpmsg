package org.particl.rpc;

public class ParticlRpcException extends Exception {

	public ParticlRpcException(String msg)
	{
		super(msg);
	}
	
	public ParticlRpcException(Exception e)
	{
		super(e);
	}
	
	public ParticlRpcException(String msg, Exception e)
	{
		super(msg, e);
	}
}

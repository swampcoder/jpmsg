package org.particl.rpc;

public class ParticlAddress {

	private final String address;
	public ParticlAddress(String address) 
	{
		super();
		this.address = address;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	@Override
	public String toString() 
	{
		return address;
	}
	
	@Override
	public int hashCode() 
	{
		return address.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof ParticlAddress)
		{
			ParticlAddress partAddr = (ParticlAddress) obj;
			return partAddr.address.equals(partAddr.address); // case?
		}
		return false;
		// also allow strings? 
	}
}

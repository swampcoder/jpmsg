package org.particl.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.particl.rpc.ParticlAddress;

public class ParticlUser {

	private String alias = null;
	private List<ParticlAddress> userAddresses = new ArrayList<ParticlAddress>();
	
	public ParticlUser()
	{
		
	}
	
	public Iterator<ParticlAddress> getAddresses() 
	{
		return userAddresses.iterator();
	}
}

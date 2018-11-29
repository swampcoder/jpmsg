package org.particl.util;

public class PartUtil {

	public static void assertNotNull(Object...objects)
	{
		for(Object o : objects)
		{
			if(o == null)
			{
				throw new IllegalArgumentException("Object cannot be null");
			}
		}
	}
	private PartUtil() {}
}

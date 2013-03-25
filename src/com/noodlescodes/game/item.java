package com.noodlescodes.game;

import android.content.Context;

/*
 * Type: 0 = tool, 1 = food, 2 = raw materials, 3 = equipable, rest currently unused
 * RRP = recommended price
 */

abstract class item
{
	protected String name;
	protected String id;
	protected String type;
	protected String RRP;
	static protected Context context;
	
	public void setName(String nm)
	{
		name = nm;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setID(String ID)
	{
		id = ID;
	}
	
	public String getID()
	{
		return id;
	}
	
	public void setType(String typ)
	{
		type = typ;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setRRP(String rrp)
	{
		RRP = rrp;
	}
	
	public String getRRP()
	{
		return RRP;
	}
}
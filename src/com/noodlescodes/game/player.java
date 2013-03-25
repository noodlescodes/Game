package com.noodlescodes.game;

import android.content.Context;

abstract class player
{
	protected String name;
	protected String id;
	protected double xPos;
	protected double yPos;
	static protected Context context;
	
	protected double[] attributes;
	
	public double getXpos()
	{
		return xPos;
	}
	
	public double getYpos()
	{
		return yPos;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setId(String ID)
	{
		id = ID;
	}

	public String getId()
	{
		return id;
	}
	
	public void setXpos(double x)
	{
		xPos = x;
	}
	
	public void setYpos(double y)
	{
		yPos = y;
	}
	
	public void setName(String nm)
	{
		name = nm;
	}
}
package com.noodlescodes.game;

import android.content.Context;
import android.content.res.TypedArray;


class food extends item
{
	int maxUses, timesUsed;
	public food(String ID, Context cntxt)
	{
		context = cntxt;
		TypedArray item_details = context.getResources().obtainTypedArray(R.array.item_details);
		String[] item = context.getResources().getStringArray(item_details.getResourceId(Integer.parseInt(ID), 0));
		name = item[0];
		id = ID;
		type = item[1];
		maxUses = Integer.parseInt(item[2]);
		timesUsed = 0;
	}
	
	public void use()
	{
		timesUsed++;
	}
	
	public int getMaxUses()
	{
		return maxUses;
	}
	
	public int getTimesUsed()
	{
		return timesUsed;
	}
}
package com.noodlescodes.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

class playerSelf extends player
{
	Inventory inventory;
	static long time;
	static boolean created;
	static int NUMBER_OF_ATTRIBUTES;
	
	//additional attributes
	long experience;
	long level;
	
	public playerSelf(String nm, String ID, Context cntxt)
	{
		name = nm;
		id = ID;
		xPos = 0;
		yPos = 0;
		time = System.currentTimeMillis() / 1000 + 604800;
		context = cntxt;
		inventory = new Inventory(context);
		getInventoryFromFile();
		NUMBER_OF_ATTRIBUTES = Integer.parseInt(context.getResources().getString(R.string.number_of_playerSelf_attributes));
		attributes = new double[NUMBER_OF_ATTRIBUTES];
		try
		{
			InputStream in = context.openFileInput("playerSelfAttributes.dat");
			in.close();
			created = true;
		}
		catch(FileNotFoundException e0)
		{
			created = false;
		}
		catch(Exception e1) {}
		fillAttributes();
	}
	
	public double getAttributeAtPosition(int i)
	{
		return attributes[i];
	}
	
	public void setAttributeAtPosition(int i, double value)
	{
		attributes[i] = value;
	}
	
	public String getInventoryInPositionTool(int i)
	{
		return inventory.getInventoryInPositionTool(i);
	}
	
	public int getInventorySizeTool()
	{
		return inventory.getInventorySizeTool();
	}
	
	public String getInventoryInPositionFood(int i)
	{
		return inventory.getInventoryInPositionFood(i);
	}
	
	public int getInventorySizeFood()
	{
		return inventory.getInventorySizeFood();
	}
	
	public String getInventoryInPositionRaw(int i)
	{
		return inventory.getInventoryInPositionRaw(i);
	}
	
	public int getInventorySizeRaw()
	{
		return inventory.getInventorySizeRaw();
	}
	
	public int getInventorySize()
	{
		return inventory.getInventorySize();
	}
	
	public boolean addToInventory(String id)
	{
		return inventory.addToInventory(id);
	}

	public boolean move(int dir)
	{
		switch(dir)
		{
			case 1:
				yPos = yPos + getAttributeAtPosition(9);
				removeTime(1);
				return true;
			case 2:
				xPos = xPos + getAttributeAtPosition(9);
				removeTime(1);
				return true;
			case 3:
				yPos = yPos - getAttributeAtPosition(9);
				removeTime(1);
				return true;
			case 4:
				xPos = xPos - getAttributeAtPosition(9);
				removeTime(1);
				return true;
			default:
				return false;
		}
	}
	
	public boolean inventoryToFile()
	{
		return inventory.inventoryToFile();
	}
	
	public boolean getInventoryFromFile()
	{
		return inventory.getInventoryFromFile();
	}
	
	public void removeTime(long t)
	{
		time -= t;
	}
	
	public void setTime(int tm)
	{
		time = tm;
	}
	
	public void addTime(int tm)
	{
		if(getTime() <= 0)
		{
			time = tm + System.currentTimeMillis() / 1000;
		}
		else
		{
			time += tm;
		}
	}
	
	public long getTime()
	{
		long left = time - System.currentTimeMillis() / 1000;
		if(left > 0)
		{
			return left;
		}
		else
		{
			return 0;
		}
	}
	
	public void fillAttributes()
	{
		/*
		 * 0 <!-- max health -->
		 * 1 <!-- max mana -->
		 * 2 <!-- max fatigue -->
		 * 3 <!-- strength -->
		 * 4 <!-- physical defense -->
		 * 5 <!-- magical defense/willpower -->
		 * 6 <!-- critical hit chance -->
		 * 7 <!-- speed (physical movement) -->
		 * 8 <!-- agility (ability to run away) -->
		 * 9 <!-- charm/bartering  -->
		 * 10 <!-- luck -->
		 */
		
		if(created)
		{
			try
			{
				InputStream in = context.openFileInput("playerSelfAttributes.dat");
				InputStreamReader inputreader = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(inputreader);
				
				for(int i = 0; i < NUMBER_OF_ATTRIBUTES; i++)
				{
					attributes[i] = Double.parseDouble(reader.readLine());
				}
			}
			catch(Exception e) {}
		}
		else
		{
			String[] temp = context.getResources().getStringArray(R.array.playerSelf_defaults);
			for(int i = 0; i < NUMBER_OF_ATTRIBUTES; i++)
			{
				attributes[i] = Double.parseDouble(temp[i]);
			}
			created = true;
			attributesToFile();
		}
	}
	
	public void attributesToFile()
	{
		try
		{
			FileOutputStream out = context.openFileOutput("playerSelfAttributes.dat", Context.MODE_PRIVATE);
			
			String output;
			
			for(int i = 0; i < NUMBER_OF_ATTRIBUTES - 1; i++)
			{
				output = "" + attributes[i] + "\n";
				out.write(output.getBytes());
				out.flush();
			}
			
			output = "" + attributes[NUMBER_OF_ATTRIBUTES - 1];
			out.write(output.getBytes());
			out.flush();
			
			out.close();
		}
		catch(Exception e) {}
	}
}
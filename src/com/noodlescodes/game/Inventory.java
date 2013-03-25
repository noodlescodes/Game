package com.noodlescodes.game;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;


class Inventory
{
	int TOTAL_POSSIBLE_TOOLS;
	int TOTAL_POSSIBLE_FOOD;
	int TOTAL_POSSIBLE_RAW;
	int TOTAL_POSSIBLE_EQUIBABLE;
	int MAX_INVENTORY_SIZE;
	
	tool[] inventory_tools;
	food[] inventory_food;
	rawMaterial[] inventory_raw;
	equipable[] inventory_equipable;
	int number_tools, number_food, number_raw, number_equipable;
	
	Context context;
	
	public Inventory(Context cntxt)
	{
		context = cntxt;
		TOTAL_POSSIBLE_TOOLS = Integer.parseInt(context.getResources().getString(R.string.total_tools));
		TOTAL_POSSIBLE_FOOD = Integer.parseInt(context.getResources().getString(R.string.total_food));
		TOTAL_POSSIBLE_RAW = Integer.parseInt(context.getResources().getString(R.string.total_raw));
		TOTAL_POSSIBLE_EQUIBABLE = Integer.parseInt(context.getResources().getString(R.string.total_equipable));
		MAX_INVENTORY_SIZE = Integer.parseInt(context.getResources().getString(R.string.max_inventory_size));
		inventory_tools = new tool[MAX_INVENTORY_SIZE];
		inventory_food = new food[MAX_INVENTORY_SIZE];
		inventory_raw = new rawMaterial[MAX_INVENTORY_SIZE];
		inventory_equipable = new equipable[MAX_INVENTORY_SIZE];
		number_tools = 0;
		number_food = 0;
		number_raw = 0;
		number_equipable = 0;
	}
	
	public String getInventoryInPositionTool(int i)
	{
		return inventory_tools[i].getID();
	}
	
	public int getInventorySizeTool()
	{
		return number_tools;
	}
	
	public String getInventoryInPositionFood(int i)
	{
		return inventory_food[i].getID();
	}
	
	public int getInventorySizeFood()
	{
		return number_food;
	}
	
	public String getInventoryInPositionRaw(int i)
	{
		return inventory_raw[i].getID();
	}
	
	public int getInventorySizeRaw()
	{
		return number_raw;
	}
	
	public String getInventoryInPositionEquipable(int i)
	{
		return inventory_equipable[i].getID();
	}
	
	public int getInventorySizeEquipable()
	{
		return number_equipable;
	}
	
	public int getInventorySize()
	{
		return number_tools + number_food + number_raw + number_equipable;
	}
	
	public boolean inInventory(String id)
	{
		if(Integer.parseInt(id) < TOTAL_POSSIBLE_TOOLS)
		{
			for(int i = 0; i < getInventorySizeTool(); i++)
			{
				if(inventory_tools[i].getID().compareToIgnoreCase(id) == 0)
				{
					return true;
				}
			}
		}
		else if(Integer.parseInt(id) < TOTAL_POSSIBLE_FOOD + TOTAL_POSSIBLE_TOOLS)
		{
			for(int i = 0; i < getInventorySizeFood(); i++)
			{
				if(inventory_food[i].getID().compareToIgnoreCase(id) == 0)
				{
					return true;
				}
			}
		}
		else if(Integer.parseInt(id) < TOTAL_POSSIBLE_FOOD + TOTAL_POSSIBLE_TOOLS + TOTAL_POSSIBLE_RAW)
		{
			for(int i = 0; i < getInventorySizeRaw(); i++)
			{
				if(inventory_raw[i].getID().compareToIgnoreCase(id) == 0)
				{
					return true;
				}
			}
		}
		else if(Integer.parseInt(id) < TOTAL_POSSIBLE_FOOD + TOTAL_POSSIBLE_TOOLS + TOTAL_POSSIBLE_RAW + TOTAL_POSSIBLE_EQUIBABLE)
		{
			for(int i = 0; i < getInventorySizeEquipable(); i++)
			{
				if(inventory_equipable[i].getID().compareToIgnoreCase(id) == 0)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean addToInventory(String id)
	{
		if(getInventorySize() < MAX_INVENTORY_SIZE)
		{
			if(Integer.parseInt(id) < TOTAL_POSSIBLE_TOOLS)
			{
				inventory_tools[getInventorySizeTool()] = new tool(id, context);
				number_tools++;
				return true;
			}
			else if(Integer.parseInt(id) < TOTAL_POSSIBLE_FOOD + TOTAL_POSSIBLE_TOOLS)
			{
				inventory_food[getInventorySizeFood()] = new food(id, context);
				number_food++;
				return true;
			}
			else if(Integer.parseInt(id) < TOTAL_POSSIBLE_FOOD + TOTAL_POSSIBLE_TOOLS + TOTAL_POSSIBLE_RAW)
			{
				inventory_raw[getInventorySizeRaw()] = new rawMaterial(id, context);
				number_raw++;
				return true;
			}
			else if(Integer.parseInt(id) < TOTAL_POSSIBLE_FOOD + TOTAL_POSSIBLE_TOOLS + TOTAL_POSSIBLE_RAW + TOTAL_POSSIBLE_EQUIBABLE)
			{
				inventory_equipable[getInventorySizeEquipable()] = new equipable(id, context);
				number_equipable++;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean inventoryToFile()
	{
		try
		{
			FileOutputStream out = context.openFileOutput("inventory.dat", Context.MODE_PRIVATE);
			
			String output;
			
			output = "" + getInventorySize() + "\n";
			out.write(output.getBytes());
			out.flush();
			
			if(getInventorySizeTool() != 0)
			{
				for(int i = 0; i < getInventorySizeTool() - 1; i++)
				{
					output = "" + getInventoryInPositionTool(i) + "\n";
					out.write(output.getBytes());
					out.flush();
				}
				
				output = "" + getInventoryInPositionTool(getInventorySizeTool() - 1);
				out.write(output.getBytes());
				out.flush();
			}
			
			if(getInventorySizeFood() != 0)
			{
				output = "\n";
				out.write(output.getBytes());
				out.flush();
			
				for(int i = 0; i < getInventorySizeFood() - 1; i++)
				{
					output = "" + getInventoryInPositionFood(i) + "\n";
					out.write(output.getBytes());
					out.flush();
				}
				
				output = "" + getInventoryInPositionFood(getInventorySizeFood() - 1);
				out.write(output.getBytes());
				out.flush();
			}
			
			if(getInventorySizeRaw() != 0)
			{
				output = "\n";
				out.write(output.getBytes());
				out.flush();
						
				for(int i = 0; i < getInventorySizeRaw() - 1; i++)
				{
					output = "" + getInventoryInPositionRaw(i) + "\n";
					out.write(output.getBytes());
					out.flush();
				}
				
				output = "" + getInventoryInPositionRaw(getInventorySizeRaw() - 1);
				out.write(output.getBytes());
				out.flush();
			}
			
			if(getInventorySizeEquipable() != 0)
			{
				output = "\n";
				out.write(output.getBytes());
				out.flush();
			
				for(int i = 0; i < getInventorySizeEquipable() - 1; i++)
				{
					output = "" + getInventoryInPositionEquipable(i) + "\n";
					out.write(output.getBytes());
					out.flush();
				}
				
				output = "" + getInventoryInPositionEquipable(getInventorySizeEquipable() - 1);
				out.write(output.getBytes());
				out.flush();
			}
			
			out.close();
		}
		catch(Exception e) {return false;}
		
		return true;
	}
	
	public boolean getInventoryFromFile()
	{
		try
		{
			InputStream in = context.openFileInput("inventory.dat");
			InputStreamReader inputreader = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(inputreader);
			String tmp ="";
			
			reader.readLine();
			
			while(tmp != null)
			{
				tmp = reader.readLine();
				addToInventory(tmp);
			}
		}
		catch(Exception e) {return false;}
		
		return true;
	}
}
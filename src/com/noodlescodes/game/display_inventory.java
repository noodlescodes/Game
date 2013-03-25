package com.noodlescodes.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class display_inventory extends Activity
{
	private ListView list;
	private ArrayAdapter<String> adapter;
	private String[] inventory_ids;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		
		list = (ListView)findViewById(R.id.list_layout);
		
		adapter = new ArrayAdapter<String>(this, R.layout.list_view_layout, fillArray());
		
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				if(inventory_ids[position].compareTo("Empty") != 0)
				{
					TypedArray item_details = getResources().obtainTypedArray(R.array.item_details);
					int identifier = item_details.getResourceId(Integer.parseInt(inventory_ids[(int)id]), 0);
					String[] items = getResources().getStringArray(identifier);
					item_details.recycle();
					AlertDialog.Builder alert = new AlertDialog.Builder(display_inventory.this);
					alert.setTitle(items[0]);
					alert.setMessage("" + constructString(items));
					alert.setPositiveButton("OK", null);
					alert.show();
				}
			}
		});
	}
	
	public String constructString(String[] items)
	{
		String str = "";
		
		str = "Name: " + items[0];
		str += "\nType: ";
		if(Integer.parseInt(items[1]) == 0)
		{
			str += "Tool";
			str += "\nMax uses: " + items[2];
			str += "\nRRP: " + items[5] + " time";
		}
		else if(Integer.parseInt(items[1]) == 1)
		{
			str += "Food";
			str += "\nMax uses: " + items[2];
			str += "\nRRP: " + items[5] + " time";
		}
		else if(Integer.parseInt(items[1]) == 2)
		{
			str += "Raw material";
			str += "\nMax uses: " + items[2];
			str += "\nRRP: " + items[5] + " time";
		}
		else if(Integer.parseInt(items[1]) == 3)
		{
			str += "Equipable";
			str += "\nMax uses: " + items[2];
			str += "\nRRP: " + items[5] + " time";
		}
		
		return str;
	}
	
	private String[] fillArray()
	{
		String[] inventory = new String[0];
		String[] all_items = getResources().getStringArray(R.array.item_list);
		
		try
		{
			InputStream in = openFileInput("inventory.dat");
			InputStreamReader inputreader = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(inputreader);
			
			int lines = Integer.parseInt(reader.readLine());
			
			inventory = new String[lines];
			
			for(int i = 0; i < lines; i++)
			{
				inventory[i] = reader.readLine();
			}
		}
		catch(Exception e) {}
		
		if(inventory.length == 0)
		{
			inventory = new String[1];
			inventory[0] = "Empty";
			inventory_ids = new String[1];
			inventory_ids[0] = "Empty";
		}
		else
		{
			inventory_ids = new String[inventory.length];
			for(int i = 0; i < inventory.length; i++)
			{
				inventory_ids[i] = inventory[i];
				inventory[i] = all_items[Integer.parseInt(inventory[i])];
			}
		}
		
		return inventory;
	}
}
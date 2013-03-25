package com.noodlescodes.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class display_attributes extends Activity
{
	private ListView list;
	private ArrayAdapter<String> adapter;
	private String[] attributes;
	
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
				String[] attributes_name = getResources().getStringArray(R.array.playerSelf_attributes_name);
				AlertDialog.Builder alert = new AlertDialog.Builder(display_attributes.this);
				alert.setTitle(attributes_name[position]);
				alert.setMessage("" + constructString(attributes_name[position], position));
				alert.setPositiveButton("OK", null);
				alert.show();
			}
		});
	}
	
	public String constructString(String name, int pos)
	{
		String str = "";
		
		str = "Name: " + name + "\n";
		
		str += "Value: " + attributes[pos];
		
		return str;
	}
	
	private String[] fillArray()
	{
		int number_of_attributes = Integer.parseInt(getBaseContext().getResources().getString(R.string.number_of_playerSelf_attributes));
		attributes = new String[number_of_attributes];
		String[] attributes_name = new String[number_of_attributes];
		
		try
		{
			InputStream in = openFileInput("playerSelfAttributes.dat");
			InputStreamReader inputreader = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(inputreader);
			
			for(int i = 0; i < number_of_attributes; i++)
			{
				attributes[i] = reader.readLine();
			}
			attributes_name = getBaseContext().getResources().getStringArray(R.array.playerSelf_attributes_name);
		}
		catch(Exception e) {}
		
		return attributes_name;
	}
}
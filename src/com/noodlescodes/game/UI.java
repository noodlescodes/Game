package com.noodlescodes.game;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UI extends Activity
{
	TextView name, ID, xpos, ypos, time;
	Button move_but, inventory_but, attributes_but;
	playerSelf self;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		name = (TextView)findViewById(R.id.name_field);
		ID = (TextView)findViewById(R.id.id_field);
		xpos = (TextView)findViewById(R.id.xpos_field);
		ypos = (TextView)findViewById(R.id.ypos_field);
		time = (TextView)findViewById(R.id.time_field);
		
		move_but = (Button)findViewById(R.id.move_button);
		inventory_but = (Button)findViewById(R.id.inventory_button);
		attributes_but = (Button)findViewById(R.id.attributes_button);
		
		self = new playerSelf("Nathan", "00000000", getBaseContext());
		
		name.setText(self.getName());
		ID.setText(self.getId());

		move_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Random rand = new Random();
				self.move((rand.nextInt(4)) + 1);
				gameLoop();
			}
		});
		
		inventory_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inventoryToFile();
				Intent inventory = new Intent(UI.this, display_inventory.class);
				startActivity(inventory);
			}
		});
		
		attributes_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				attributesToFile();
				Intent attributes = new Intent(UI.this, display_attributes.class);
				startActivity(attributes);
			}
		});
		
		gameLoop();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		gameLoop();
	}
	
	public void gameLoop()
	{
		update();
	}
	
	public void update()
	{
		xpos.setText(Double.toString(self.getXpos()));
		ypos.setText(Double.toString(self.getYpos()));
		time.setText(Long.toString(self.getTime()));
	}
	
	public void inventoryToFile()
	{
		self.inventoryToFile();
	}
	
	public void attributesToFile()
	{
		self.attributesToFile();
	}
}
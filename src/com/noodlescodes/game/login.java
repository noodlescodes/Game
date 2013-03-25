package com.noodlescodes.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity
{
	Button login_but, create_but, forgot_but;
	EditText username, password;
	ProgressDialog progressDialog;
	String uname, pword;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		login_but = (Button)findViewById(R.id.login_button);
		create_but = (Button)findViewById(R.id.new_account_but);
		forgot_but = (Button)findViewById(R.id.forgot_pword_but);
		
		username = (EditText)findViewById(R.id.username_field);
		password = (EditText)findViewById(R.id.password_field);
		
		login_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				uname = username.getText().toString();
				pword = password.getText().toString();
				if(uname.compareToIgnoreCase("") == 0 || pword.compareToIgnoreCase("") == 0)
				{
					Toast.makeText(getBaseContext(), "Please fill in both username and password!", Toast.LENGTH_LONG).show();
				}
				else
				{
					verify();
				}
			}
		});
		
		create_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), characterCreation1.class);
				startActivity(i);
				finish();
			}
		});
	}
	
	public void verify()
	{
		new Upload().execute((Void[])null);
	}
	
	private class Upload extends AsyncTask<Void, Void, Void>
	{
		String responded;
		
		@Override
		protected void onPreExecute()
		{
			progressDialog = ProgressDialog.show(login.this, "Checking login details...", "Please wait");
		}
		
		@Override
		protected Void doInBackground(Void... v)
		{
			try
			{
				InetAddress address = InetAddress.getByName("noodles-codes.dyndns-home.com");
				Socket socket = new Socket(address, 48500);
				DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
				DataInputStream dataIn = new DataInputStream(socket.getInputStream());
				dataOut.writeUTF(uname + " " + pword);
				responded = dataIn.readUTF();
			}
			catch(UnknownHostException e0)
			{
				progressDialog.dismiss();
			}
			catch(SecurityException e1)
			{
				progressDialog.dismiss();
			}
			catch(Exception e)
			{
				progressDialog.dismiss();
			}
			
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Void... v)
		{
			super.onProgressUpdate(v);
		}
		
		@Override
		protected void onPostExecute(Void args0)
		{
			progressDialog.dismiss();
			interpretResult();
		}
		
		public void interpretResult()
		{
			if(responded.length() == 1 && responded.compareTo("0") == 0)
			{
				login();
			}
			else if(responded.length() == 1 && responded.compareTo("2") == 0)
			{
				nonExists();
			}
			else
			{
				unknownError();
			}
		}
		
		public void login()
		{
			Intent i = new Intent(getBaseContext(), UI.class);
			startActivity(i);
			finish();
		}
		
		public void nonExists()
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(login.this);
			alert.setTitle("ERROR");
			alert.setMessage("" + getBaseContext().getResources().getString(R.string.account_nonexist));
			alert.setPositiveButton("OK", null);
			alert.show();
		}
		
		public void unknownError()
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(login.this);
			alert.setTitle("ERROR");
			alert.setMessage("" + getBaseContext().getResources().getString(R.string.login_error));
			alert.setPositiveButton("OK", null);
			alert.show();
		}
	}
}
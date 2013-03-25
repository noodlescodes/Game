package com.noodlescodes.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class characterCreation1 extends Activity
{
	EditText uname, email, pword;
	Button next;
	ProgressDialog progressDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.character_creation_1);
		
		uname = (EditText)findViewById(R.id.username_character_creation_1_field);
		email = (EditText)findViewById(R.id.email_character_creation_1_field);
		pword = (EditText)findViewById(R.id.password_character_creation_1_field);
		
		next = (Button)findViewById(R.id.character_creation_1_next_button);
		
		next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(isEmail())
				{
					if(checkUniqueUname())
					{
						submit();
					}
				}
			}
		});
	}
	
	public boolean checkUniqueUname()
	{
		String username = uname.getText().toString();
		String respond = "";
		try
		{
			InetAddress address = InetAddress.getByName("noodles-codes.dyndns-home.com");
			Socket socket = new Socket(address, 48501);
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream dataIn = new DataInputStream(socket.getInputStream());
			dataOut.writeUTF(username);
			respond = dataIn.readUTF();
		}
		catch(UnknownHostException e0) {}
		catch(SecurityException e1) {}
		catch(Exception e) {}
		
		if(respond.compareTo("0") == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isEmail()
	{
		String mail = email.getText().toString();
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(mail);
		
		return m.matches();
	}
	
	public void submit()
	{
		//Not sure whether to submit details now and then possibly have a unfinished player on file, or submit at the end and have a possible contradiction in names.
		//Alternative is to have a "reserved username" for a minimum of 24hrs to a specific email address.
		//new Upload().execute((Void[])null);
	}
}
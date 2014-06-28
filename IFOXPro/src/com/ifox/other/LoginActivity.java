package com.ifox.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ifox.main.R;

public class LoginActivity extends Activity {
	private EditText username, password;
	private Button login ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.login);
		
		intiButton();
	}
	private void intiButton() {
		username = (EditText) findViewById(R.id.other_login_username);		
		password = (EditText) findViewById(R.id.other_login_password);	
		login = (Button) findViewById(R.id.other_login_login);
		
		login.setOnClickListener(new ButtonOnclickListener());
	}
	
	public class ButtonOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String un = username.getText().toString();
			String pw = password.getText().toString();
			if(un.equals("ifox")&&pw.equals("zs")){
				
				Intent intent = new Intent(LoginActivity.this,ContactActivity.class);
				LoginActivity.this.startActivity(intent);
							
			}else{
				Toast.makeText(LoginActivity.this, "ÇëµÇÂ¼", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
		
	
	
	
	public void back(View v) {
		finish();
		overridePendingTransition(0, R.anim.out_news_text);
	}
}

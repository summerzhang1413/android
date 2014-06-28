package com.ifox.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ifox.main.R;

public class ContactActivity extends Activity {
	public static String data[][] = new String[][]{{"≥¬ŒÈ“⁄","		15680728781"},{"’≈    “∞","		13488980160"},{"¡ı    Ë¥","		13608192547"}} ;
	private List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	private SimpleAdapter sim = null;
	private ListView contact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.contact);
		
		this.contact = (ListView)super.findViewById(R.id.contact) ;
		for(int x = 0;x<this.data.length;x++){
			Map<String,String> map = new HashMap<String,String>() ;
			map.put("linkman", data[x][0]);
			map.put("tel", data[x][1]);
			this.list.add(map);		
			
		}
		this.sim = new SimpleAdapter(this,this.list,R.layout.contact_details,new String[]{"linkman","tel"},new int[]{R.id.linkman,R.id.tel});
		this.contact.setAdapter(this.sim);
//		
//		this.contact.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
				// TODO Auto-generated method stub
//				for(int x=0;x<data.length;x++){
//					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"  
//                        + data[x][1]));
//					ContactActivity.this.startActivity(intent);
//				}
//			}
//		});
//		
	}
	
}

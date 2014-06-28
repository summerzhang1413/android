package com.ifox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ifox.main.R;
import com.ifox.other.AboutActivity;
import com.ifox.other.LoginActivity;
import com.ifox.other.UpdateActivity;

public class OtherFragment extends Fragment {
	
	private LinearLayout other_login, other_about, other_update, other_cancel ;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		return inflater.inflate( R.layout.other_fragment, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		intiLinearLayout();
		
	}

	private void intiLinearLayout() {
		View view = getView();
		other_login=(LinearLayout) view.findViewById(R.id.other_login);
		other_cancel=(LinearLayout) view.findViewById(R.id.other_cancel);
		other_about=(LinearLayout) view.findViewById(R.id.other_about);
		other_update=(LinearLayout) view.findViewById(R.id.other_update);
		
		
		other_login.setOnClickListener(new LinearLayoutOnClickListener(0));
		other_cancel.setOnClickListener(new LinearLayoutOnClickListener(1));
		other_about.setOnClickListener(new LinearLayoutOnClickListener(2));
		other_update.setOnClickListener(new LinearLayoutOnClickListener(3));
		
	}
	
	public class LinearLayoutOnClickListener implements View.OnClickListener{
		
		private int index = 0;
		
		public LinearLayoutOnClickListener(int i){
			this.index=i;
		}  
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (index) {
			case 0 :{
					Intent intent = new Intent(getActivity(),LoginActivity.class);
					getActivity().startActivity(intent);
					getActivity().overridePendingTransition(R.anim.otherin, R.anim.hold);
					break;
				}
			case 2 :{
				Intent intent = new Intent(getActivity(),AboutActivity.class);
				getActivity().startActivity(intent);
				getActivity().overridePendingTransition(R.anim.otherin, R.anim.hold);
				break;
				}
			case 3:{
				Intent intent = new Intent(getActivity(),UpdateActivity.class);
				getActivity().startActivity(intent);
				getActivity().overridePendingTransition(R.anim.otherin, R.anim.hold);
				break;
				}
			case 1 :{
				
				break;
				}
			}
		}
		
	}
	
}

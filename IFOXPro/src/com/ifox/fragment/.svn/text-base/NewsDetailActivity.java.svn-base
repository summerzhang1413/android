package com.ifox.fragment;

import com.ifox.main.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

public class NewsDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle saveInatanceState) {
		super.onCreate(saveInatanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE) ;
		super.setContentView(R.layout.news_detail_activity) ;
	}

	public void back(View v) {
		finish();
		overridePendingTransition(0, R.anim.out_news_text);
	}

}

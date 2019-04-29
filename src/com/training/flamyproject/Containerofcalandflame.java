package com.training.flamyproject;

import java.util.List;
import java.util.Vector;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class Containerofcalandflame extends AppCompatActivity implements OnPageChangeListener, OnTabChangeListener{
	ViewPager viewPager;
	TabHost tabHost;
	FlameCalcuAdapter fcadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabsofcalandflame); 
		this.initViewPager();
		this.initTabHost(savedInstanceState);

	}

	public class fakeContent implements TabContentFactory {
    	Context context;
    	public fakeContent(Context mcontext) {
			context = mcontext;
		}
		@Override
		public View createTabContent(String tag) {
			View fakeView = new View(context);
			fakeView.setMinimumHeight(0);
			fakeView.setMinimumWidth(0);
			return fakeView;
		}	
    	
    }
	private void initViewPager() {

		List<Fragment> listFragments = new Vector<Fragment>();
		listFragments.add(new MainActivity());
		listFragments.add(new Calq());

		this.fcadapter= new FlameCalcuAdapter(
				getSupportFragmentManager(), listFragments);
		this.viewPager = (ViewPager) super.findViewById(R.id.view_pager);
		this.viewPager.setAdapter(this.fcadapter);
		this.viewPager.setOnPageChangeListener(this);
		onRestart();
	}

	private void initTabHost(Bundle args) {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		for (int i=0; i < 2; i++) {

			TabHost.TabSpec tabSpec;
			if (i==0) {
				tabSpec = tabHost.newTabSpec("flame");
				tabSpec.setIndicator("FLAMES");
			} else if(i==1) {
				tabSpec = tabHost.newTabSpec("CALQ");
				tabSpec.setIndicator("CAL-Q");
			} else {
				tabSpec = tabHost.newTabSpec("Tab");
				tabSpec.setIndicator("Tab");
			}
			tabSpec.setContent(new fakeContent(this));
			tabHost.addTab(tabSpec);
		}
		for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#9b7777"));
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			tv.setTextColor(Color.parseColor("#ffffff"));
		}
		tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#B71C1C"));
		tabHost.setOnTabChangedListener(this);
	}

	@Override
	public void onTabChanged(String arg0) {
		int selectedItem = tabHost.getCurrentTab();
		viewPager.setCurrentItem(selectedItem);

		HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
		View tabView = tabHost.getCurrentTabView();
		int scrollPos = tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth()) / 2;
		hScrollView.smoothScrollTo(scrollPos, 0);

		for(int i=0;i< tabHost.getTabWidget().getChildCount();i++) {
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#9b7777"));
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			tv.setTextColor(Color.parseColor("#ffffff"));
		}

		tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#B71C1C"));
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onPageSelected(int selectedItem) {
		tabHost.setCurrentTab(selectedItem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}
}

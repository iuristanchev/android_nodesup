package com.netsecl.nodesup;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class Tabs extends TabActivity {
	/**
	 * Tab names.
	 */
	private static final String MAIN_TAB = "Main";
	private static final String MX_TAB = "MX Records";
	private static final String RDNS_TAB = "RDNS";
	private static final String ABOUT_TAB = "About";
	private static final int NUMBER_OF_TABS = 4;

	// private static final String PROFILE_SPEC = "Profile";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		TabHost tabHost = getTabHost();
		/**
		 * Create an About Intent.
		 */
		Intent about = new Intent(getApplicationContext(), AboutActivity.class);
		String title = "About Yuriy's NodesUP";
		String file = "file:///android_asset/about.html";
		about.putExtra("title", title);
		about.putExtra("file", file);

		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		/**
		 * Add the main tab.
		 */
		tabHost.addTab(tabHost.newTabSpec(MAIN_TAB).setIndicator(MAIN_TAB)
				.setContent(new Intent(this, MainActivity.class)));
		tabHost.setCurrentTab(0);
		tabHost.getTabWidget()
				.getChildAt(0)
				.setLayoutParams(
						new LinearLayout.LayoutParams((width / NUMBER_OF_TABS) - 2, 90));
		/**
		 * Add the MX tab.
		 */
		tabHost.addTab(tabHost.newTabSpec(MX_TAB).setIndicator(MX_TAB)
				.setContent(new Intent(this, MxRecords.class)));
		//tabHost.setCurrentTab(1);
		tabHost.getTabWidget()
				.getChildAt(1)
				.setLayoutParams(
						new LinearLayout.LayoutParams((width / NUMBER_OF_TABS) - 2, 90));
		/**
		 * RDNS tab.
		 */
		tabHost.addTab(tabHost.newTabSpec(RDNS_TAB).setIndicator(RDNS_TAB)
				.setContent(new Intent(this, RDNS.class)));
		//tabHost.setCurrentTab(1);
		tabHost.getTabWidget()
				.getChildAt(2)
				.setLayoutParams(
						new LinearLayout.LayoutParams((width / NUMBER_OF_TABS) - 2, 90));
		
		/**
		 * Add the About tab.
		 */
		tabHost.addTab(tabHost.newTabSpec(ABOUT_TAB).setIndicator(ABOUT_TAB)
				.setContent(about));
		//tabHost.setCurrentTab(2);
		tabHost.getTabWidget()
				.getChildAt(3)
				.setLayoutParams(
						new LinearLayout.LayoutParams((width / NUMBER_OF_TABS) - 2, 90));
	}
}
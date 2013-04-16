package edu.uiuc.whosinline.listeners;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.view.ViewPager;

public class HomeTabsListener implements ActionBar.TabListener {

	final private ViewPager viewPager;
	
	public HomeTabsListener(ViewPager viewPager) {
		this.viewPager = viewPager;
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// Do nothing.
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// Do nothing.
	}
}
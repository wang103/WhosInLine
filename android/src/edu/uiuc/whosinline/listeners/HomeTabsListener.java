package edu.uiuc.whosinline.listeners;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class HomeTabsListener implements ActionBar.TabListener {

	public Fragment fragment;
	
	public HomeTabsListener(Fragment fragment){
		this.fragment = fragment;
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// nothing for now
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
}

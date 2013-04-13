package edu.uiuc.whosinline.listeners;

import edu.uiuc.whosinline.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

public class HomeTabsListener implements ActionBar.TabListener {

	final private FragmentManager fragmentManager;
	final private Fragment fragment;
	final private int fragmentContainerId;
	final private ViewPager viewPager;
	
	public HomeTabsListener(FragmentManager fragmentManager, Fragment fragment,
			int fragmentContainerId, ViewPager viewPager){
		
		this.fragmentManager = fragmentManager;
		this.fragment = fragment;
		this.fragmentContainerId = fragmentContainerId;
		this.viewPager = viewPager;
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// Do nothing.
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		/*// Create new transaction.
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		// Replace whatever is in the fragment_container view with this
		// fragment.
		transaction.replace(fragmentContainerId, fragment);

		// Commit the transaction.
		transaction.commit();*/
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// Do nothing.
	}
}
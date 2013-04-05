package edu.uiuc.whosinline.listeners;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class HomeTabsListener implements ActionBar.TabListener {

	final private FragmentManager fragmentManager;
	final private Fragment fragment;
	final private int fragmentContainerId;
	
	public HomeTabsListener(FragmentManager fragmentManager, Fragment fragment,
			int fragmentContainerId){
		
		this.fragmentManager = fragmentManager;
		this.fragment = fragment;
		this.fragmentContainerId = fragmentContainerId;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// Do nothing.
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Create new transaction.
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		// Replace whatever is in the fragment_container view with this
		// fragment.
		transaction.replace(fragmentContainerId, fragment);

		// Commit the transaction.
		transaction.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// Do nothing.
	}
}
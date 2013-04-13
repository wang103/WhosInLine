package edu.uiuc.whosinline;

import edu.uiuc.whosinline.fragments.BaseFragment;
import edu.uiuc.whosinline.fragments.FavoriteFragment;
import edu.uiuc.whosinline.fragments.NearbyFragment;
import edu.uiuc.whosinline.fragments.RecentFragment;
import edu.uiuc.whosinline.listeners.HomeTabsListener;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class HomeActivity extends Activity {

	private FragmentManager fragmentManager;

	final static public String INTENT_TABLE_NUM = "intent_table_num";
	final static public String INTENT_VENUE_ID = "intent_venue_id";
	
	final static private String STATE_TAB = "state_tab";
	final static private String STRING_TAB_NEARBY = "NEARBY";
	final static private String STRING_TAB_RECENT = "RECENT";
	final static private String STRING_TAB_FAVORITE = "FAVORITE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fragmentManager = getFragmentManager();
		
		// set up the action bar with tabs
		setUpActionBar(savedInstanceState);
		
		// set the view for this activity
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		// Save the current tab.
		ActionBar actionBar = getActionBar();
		Tab curTab = actionBar.getSelectedTab();
		int tabNum;
		if (curTab.getText().equals(STRING_TAB_NEARBY)) {
			tabNum = 0;
		} else if (curTab.getText().equals(STRING_TAB_RECENT)) {
			tabNum = 1;
		} else {
			tabNum = 2;
		}
		outState.putInt(STATE_TAB, tabNum);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/**
	 * Sets up the action bar for this activity by creating three tabs.
	 * 
	 * @param savedInstanceState a {@link Bundle} object.
	 */
	private void setUpActionBar(Bundle savedInstanceState){
		
		// get rid of the icon and app title in the action bar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		
		// set up tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// initiate the three tabs
		Tab nearbyTab = actionBar.newTab();
		nearbyTab.setText(STRING_TAB_NEARBY);
		nearbyTab.setIcon(R.drawable.ic_tab_nearby);
		
		Tab recentTab = actionBar.newTab();
		recentTab.setText(STRING_TAB_RECENT);
		recentTab.setIcon(R.drawable.ic_tab_recent);
		
		Tab favoriteTab = actionBar.newTab();
		favoriteTab.setText(STRING_TAB_FAVORITE);
		favoriteTab.setIcon(R.drawable.ic_tab_favorite);

		// create fragments to display each tab
		Fragment nearbyFragment = new NearbyFragment();
		Fragment recentFragment = new RecentFragment();
		Fragment favoriteFragment = new FavoriteFragment();
		
		// set the tab listeners to listen for clicks
		nearbyTab.setTabListener(new HomeTabsListener(fragmentManager,
				nearbyFragment, R.id.fragment_container));
		recentTab.setTabListener(new HomeTabsListener(fragmentManager,
				recentFragment, R.id.fragment_container));
		favoriteTab.setTabListener(new HomeTabsListener(fragmentManager,
				favoriteFragment, R.id.fragment_container));

		// Restore the tab that was being viewed.
		int selectedTabNum = 0;
		if (savedInstanceState != null) {
			selectedTabNum = savedInstanceState.getInt(STATE_TAB);
		}

		// add tabs to the action bar
		actionBar.addTab(nearbyTab, selectedTabNum == 0);
		actionBar.addTab(recentTab, selectedTabNum == 1);
		actionBar.addTab(favoriteTab, selectedTabNum == 2);
	}
	
	@Override
	public void onBackPressed() {
		// Confirm if user really want to exit the app.
		Builder alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setMessage("Do you want to exit the app?");
		alertBuilder.setCancelable(false);
		alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				HomeActivity.this.finish();
			}
		});
		alertBuilder.setNegativeButton("NO", null);
		alertBuilder.show();
	}
	
	public void onProfileButtonClick(View v) {
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentById(R.id.fragment_container);
		ListView lv = fragment.getListView();
		int tableNum;
		long venueID = lv.getPositionForView(v);
		if (fragment instanceof NearbyFragment) {
			tableNum = 0;
		} else if (fragment instanceof RecentFragment) {
			tableNum = 1;
		} else {
			tableNum = 2;
		}
		
		Intent intent = new Intent(this, VenueActivity.class);
		Bundle extras = new Bundle();
		extras.putInt(INTENT_TABLE_NUM, tableNum);
		extras.putLong(INTENT_VENUE_ID, venueID);
		intent.putExtras(extras);
		startActivity(intent);
	}
}
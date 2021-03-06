package edu.uiuc.whosinline;

import edu.uiuc.whosinline.adapters.SwipePagerAdapter;
import edu.uiuc.whosinline.database.DatabaseAccessObjVenue;
import edu.uiuc.whosinline.fragments.BaseFragment;
import edu.uiuc.whosinline.fragments.FavoriteFragment;
import edu.uiuc.whosinline.fragments.NearbyFragment;
import edu.uiuc.whosinline.fragments.RecentFragment;
import edu.uiuc.whosinline.listeners.HomeTabsListener;
import edu.uiuc.whosinline.listeners.MenuItemListener;
import edu.uiuc.whosinline.listeners.SearchQueryListener;
import edu.uiuc.whosinline.windows.ChatWindow;
import edu.uiuc.whosinline.windows.FavoriteWindow;
import edu.uiuc.whosinline.windows.SubmitWaitTimeWindow;
import edu.uiuc.whosinline.windows.WriteReviewWindow;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.SearchManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

public class HomeActivity extends FragmentActivity {

	private FragmentManager fragmentManager;
	private ViewPager viewPager;
	private boolean exitOnBack = true;
	
	final static public String INTENT_CHAT_TYPE = "intent_chat_type";
	final static public String INTENT_TABLE_NUM = "intent_table_num";
	final static public String INTENT_VENUE_ID = "intent_venue_id";
	final static public String INTENT_QUERY = "intent_query";

	final static private String STATE_TAB = "state_tab";
	final static private String STRING_TAB_NEARBY = "NEARBY";
	final static private String STRING_TAB_RECENT = "RECENT";
	final static private String STRING_TAB_FAVORITE = "FAVORITE";

	final static public String TAG_SUBMIT_WAIT_TIME_WINDOW = "submit_wait_time";
	final static public String TAG_CHAT_WINDOW = "chat";
	final static public String TAG_WRITE_REVIEW_WINDOW = "write_review";
	final static public String TAG_FAVORITE_WINDOW = "favorite";
	final static public String TAG_REQUEST_WINDOW = "request";
	
	final static public int RESULT_REFRESH = 0;
	final static public int RESULT_SETTINGS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the view for this activity.
		setContentView(R.layout.activity_main);

		fragmentManager = getSupportFragmentManager();

		// Set up the action bar with tabs.
		setUpActionBar(savedInstanceState);
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

		menu.findItem(R.id.menu_chat).setOnMenuItemClickListener(new MenuItemListener(this));

		// Associate searchable configuration with the SearchView.
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setOnQueryTextListener(new SearchQueryListener(this, viewPager, fragmentManager));
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent i = new Intent(this, SettingsActivity.class);
			startActivityForResult(i, RESULT_SETTINGS);
			break;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_REFRESH:
			// Get the table number that needs to be refreshed.
			int needToRefreshTable = data.getIntExtra("needToRefreshTables", 0);
			if (needToRefreshTable == 1 || needToRefreshTable == 3) {
				refreshList(1);
			}
			if (needToRefreshTable == 2 || needToRefreshTable == 3) {
				refreshList(2);
			}
			break;
		case RESULT_SETTINGS:
			// Get the boolean value from the checkbox.
			SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
			this.exitOnBack = sharedPrefs.getBoolean("prefexit", true);
			break;
		}
	}

	/**
	 * Sets up the action bar for this activity by creating three tabs.
	 * 
	 * @param savedInstanceState a {@link Bundle} object.
	 */
	private void setUpActionBar(Bundle savedInstanceState){

		// Get rid of the icon and app title in the action bar.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		// Set up tabs.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Initiate the three tabs.
		Tab nearbyTab = actionBar.newTab();
		nearbyTab.setText(STRING_TAB_NEARBY);
		nearbyTab.setIcon(R.drawable.ic_tab_nearby);

		Tab recentTab = actionBar.newTab();
		recentTab.setText(STRING_TAB_RECENT);
		recentTab.setIcon(R.drawable.ic_tab_recent);

		Tab favoriteTab = actionBar.newTab();
		favoriteTab.setText(STRING_TAB_FAVORITE);
		favoriteTab.setIcon(R.drawable.ic_tab_favorite);

		// Create fragments to display each tab.
		Fragment nearbyFragment = new NearbyFragment();
		Fragment recentFragment = new RecentFragment();
		Fragment favoriteFragment = new FavoriteFragment();

		// Add the fragments to the pager adapter.
		SwipePagerAdapter pagerAdapter = new SwipePagerAdapter(fragmentManager);
		pagerAdapter.addFragment(nearbyFragment);
		pagerAdapter.addFragment(recentFragment);
		pagerAdapter.addFragment(favoriteFragment);

		// Set the adapter to the ViewPager.
		viewPager = (ViewPager) findViewById(R.id.pager_view);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setCurrentItem(0);

		// Set the swipe listener.
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				getActionBar().setSelectedNavigationItem(position);
			}
		});

		// Set the tab listeners to listen for clicks.
		nearbyTab.setTabListener(new HomeTabsListener(viewPager));
		recentTab.setTabListener(new HomeTabsListener(viewPager));
		favoriteTab.setTabListener(new HomeTabsListener(viewPager));

		// Restore the tab that was being viewed.
		int selectedTabNum = 0;
		if (savedInstanceState != null) {
			selectedTabNum = savedInstanceState.getInt(STATE_TAB);
		}

		// Add tabs to the action bar.
		actionBar.addTab(nearbyTab, selectedTabNum == 0);
		actionBar.addTab(recentTab, selectedTabNum == 1);
		actionBar.addTab(favoriteTab, selectedTabNum == 2);
	}

	@Override
	public void onBackPressed() {
		if (exitOnBack) {
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
		else {
			HomeActivity.this.finish();
		}
	}

	public void refreshList(int tableNum) {
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(
				"android:switcher:" + R.id.pager_view + ":" + tableNum);
		fragment.fillData(tableNum);
	}

	public void onProfileButtonClick(View v) {
		int curItem = viewPager.getCurrentItem();
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(
				"android:switcher:" + R.id.pager_view + ":" + curItem);
		ListView lv = fragment.getListView();
		int tableNum;
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObjVenue.cursorToVenue(cursor).getId();
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

	public void onSubmitWaitButtonClick(View v) {
		int curItem = viewPager.getCurrentItem();
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(
				"android:switcher:" + R.id.pager_view + ":" + curItem);
		ListView lv = fragment.getListView();
		int tableNum;
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObjVenue.cursorToVenue(cursor).getId();
		if (fragment instanceof NearbyFragment) {
			tableNum = 0;
		} else if (fragment instanceof RecentFragment) {
			tableNum = 1;
		} else {
			tableNum = 2;
		}

		SubmitWaitTimeWindow windowFrag = new SubmitWaitTimeWindow();
		Bundle extras = new Bundle();
		extras.putInt(INTENT_TABLE_NUM, tableNum);
		extras.putLong(INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), TAG_SUBMIT_WAIT_TIME_WINDOW);
	}

	public void onChatButtonClick(View v) {
		int curItem = viewPager.getCurrentItem();
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(
				"android:switcher:" + R.id.pager_view + ":" + curItem);
		ListView lv = fragment.getListView();
		int tableNum;
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObjVenue.cursorToVenue(cursor).getId();
		if (fragment instanceof NearbyFragment) {
			tableNum = 0;
		} else if (fragment instanceof RecentFragment) {
			tableNum = 1;
		} else {
			tableNum = 2;
		}

		ChatWindow windowFrag = new ChatWindow();
		Bundle extras = new Bundle();
		extras.putBoolean(INTENT_CHAT_TYPE, true);
		extras.putInt(INTENT_TABLE_NUM, tableNum);
		extras.putLong(INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), TAG_CHAT_WINDOW);
	}

	public void onWriteReviewButtonClick(View v) {
		int curItem = viewPager.getCurrentItem();
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(
				"android:switcher:" + R.id.pager_view + ":" + curItem);
		ListView lv = fragment.getListView();
		int tableNum;
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObjVenue.cursorToVenue(cursor).getId();
		if (fragment instanceof NearbyFragment) {
			tableNum = 0;
		} else if (fragment instanceof RecentFragment) {
			tableNum = 1;
		} else {
			tableNum = 2;
		}

		WriteReviewWindow windowFrag = new WriteReviewWindow();
		Bundle extras = new Bundle();
		extras.putInt(INTENT_TABLE_NUM, tableNum);
		extras.putLong(INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), TAG_WRITE_REVIEW_WINDOW);
	}

	public void onFavoriteButtonClick(View v) {
		int curItem = viewPager.getCurrentItem();
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(
				"android:switcher:" + R.id.pager_view + ":" + curItem);
		ListView lv = fragment.getListView();
		int tableNum;
		int position = lv.getPositionForView(v);
		SQLiteCursor cursor = (SQLiteCursor) lv.getItemAtPosition(position);
		long venueID = DatabaseAccessObjVenue.cursorToVenue(cursor).getId();
		if (fragment instanceof NearbyFragment) {
			tableNum = 0;
		} else if (fragment instanceof RecentFragment) {
			tableNum = 1;
		} else {
			tableNum = 2;
		}

		FavoriteWindow windowFrag = new FavoriteWindow();
		Bundle extras = new Bundle();
		extras.putInt(INTENT_TABLE_NUM, tableNum);
		extras.putLong(INTENT_VENUE_ID, venueID);
		windowFrag.setArguments(extras);
		windowFrag.show(getFragmentManager(), TAG_FAVORITE_WINDOW);
	}
}
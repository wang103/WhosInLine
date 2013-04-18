package edu.uiuc.whosinline.listeners;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.SearchActivity;
import edu.uiuc.whosinline.fragments.BaseFragment;
import edu.uiuc.whosinline.fragments.NearbyFragment;
import edu.uiuc.whosinline.fragments.RecentFragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.SearchView.OnQueryTextListener;

public class SearchQueryListener implements OnQueryTextListener {

	private Activity activity;
	private ViewPager viewPager;
	private FragmentManager fragmentManager;
	
	public SearchQueryListener(Activity activity, ViewPager viewPager,
			FragmentManager fragmentManager) {
		this.activity = activity;
		this.viewPager = viewPager;
		this.fragmentManager = fragmentManager;
	}
	
	@Override
	public boolean onQueryTextChange(String newText) {
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		int curItem = viewPager.getCurrentItem();
		BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(
				"android:switcher:" + R.id.pager_view + ":" + curItem);
		int tableNum;
		if (fragment instanceof NearbyFragment) {
			tableNum = 0;
		} else if (fragment instanceof RecentFragment) {
			tableNum = 1;
		} else {
			tableNum = 2;
		}
		
		Intent intent = new Intent(activity, SearchActivity.class);
		Bundle appData = new Bundle();
		appData.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
		appData.putString(HomeActivity.INTENT_QUERY, query);
		intent.putExtras(appData);
		activity.startActivity(intent);
		
		return true;
	}
}
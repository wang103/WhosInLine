package edu.uiuc.whosinline.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SwipePagerAdapter extends FragmentPagerAdapter {

	private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
	
	public SwipePagerAdapter(FragmentManager manager) {
		super(manager);
	}

	public void addFragment(Fragment fragment) {
		mFragments.add(fragment);
		notifyDataSetChanged();
	}
	
	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}
}
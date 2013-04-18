package edu.uiuc.whosinline.listeners;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.windows.ChatWindow;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class MenuItemListener implements OnMenuItemClickListener {

	private HomeActivity activity;

	public MenuItemListener(HomeActivity activity) {
		this.activity = activity;
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		
		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		
		ChatWindow windowFrag = new ChatWindow();
		Bundle extras = new Bundle();
		extras.putBoolean(HomeActivity.INTENT_CHAT_TYPE, false);
		windowFrag.setArguments(extras);
		windowFrag.show(fragmentManager, HomeActivity.TAG_CHAT_WINDOW);
		
		return true;
	}
}
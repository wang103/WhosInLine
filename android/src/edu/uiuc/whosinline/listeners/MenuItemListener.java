package edu.uiuc.whosinline.listeners;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.windows.ChatWindow;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class MenuItemListener implements OnMenuItemClickListener {

	private HomeActivity activity;

	public MenuItemListener(HomeActivity activity) {
		this.activity = activity;
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
				
		ChatWindow windowFrag = new ChatWindow();
		Bundle extras = new Bundle();
		extras.putBoolean(HomeActivity.INTENT_CHAT_TYPE, false);
		windowFrag.setArguments(extras);
		windowFrag.show(activity.getFragmentManager(), HomeActivity.TAG_CHAT_WINDOW);
		
		return true;
	}
}
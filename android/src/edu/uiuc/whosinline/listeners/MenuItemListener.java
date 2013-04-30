package edu.uiuc.whosinline.listeners;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.windows.ChatWindow;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class MenuItemListener implements OnMenuItemClickListener {

	private Activity activity;

	public MenuItemListener(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_chat:
			ChatWindow windowFrag = new ChatWindow();
			Bundle extras = new Bundle();
			extras.putBoolean(HomeActivity.INTENT_CHAT_TYPE, false);
			windowFrag.setArguments(extras);
			windowFrag.show(activity.getFragmentManager(), HomeActivity.TAG_CHAT_WINDOW);
			break;
		case R.id.venue_submit_wait:
			
			break;
		case R.id.venue_chat:
			
			break;
		case R.id.venue_review:
			
			break;
		case R.id.venue_favorite:
			
			break;
		case R.id.venue_request:
			
			break;
		default:
			break;
		}
		
		return true;
	}
}
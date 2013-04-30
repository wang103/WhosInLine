package edu.uiuc.whosinline.listeners;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.Venue;
import edu.uiuc.whosinline.windows.BaseWindow;
import edu.uiuc.whosinline.windows.ChatWindow;
import edu.uiuc.whosinline.windows.FavoriteWindow;
import edu.uiuc.whosinline.windows.SubmitRequestWindow;
import edu.uiuc.whosinline.windows.SubmitWaitTimeWindow;
import edu.uiuc.whosinline.windows.WriteReviewWindow;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class MenuItemListener implements OnMenuItemClickListener {

	private Activity activity;
	private Venue venue;
	private int tableNum;

	public MenuItemListener(Activity activity) {
		this.activity = activity;
	}
	
	public MenuItemListener(Activity activity, Venue venue, int tableNum) {
		this.activity = activity;
		this.venue = venue;
		this.tableNum = tableNum;
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		BaseWindow windowFrag = null;
		Bundle extras = new Bundle();
		String tag = null;

		switch (item.getItemId()) {
		case R.id.menu_chat:
			windowFrag = new ChatWindow();
			extras.putBoolean(HomeActivity.INTENT_CHAT_TYPE, false);
			tag = HomeActivity.TAG_CHAT_WINDOW;
			break;
			
		case R.id.venue_submit_wait:
			windowFrag = new SubmitWaitTimeWindow();
			extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
			extras.putLong(HomeActivity.INTENT_VENUE_ID, venue.getId());
			tag = HomeActivity.TAG_SUBMIT_WAIT_TIME_WINDOW;
			break;
			
		case R.id.venue_chat:
			windowFrag = new ChatWindow();
			extras.putBoolean(HomeActivity.INTENT_CHAT_TYPE, true);
			extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
			extras.putLong(HomeActivity.INTENT_VENUE_ID, venue.getId());
			tag = HomeActivity.TAG_CHAT_WINDOW;
			break;
			
		case R.id.venue_review:
			windowFrag = new WriteReviewWindow();
			extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
			extras.putLong(HomeActivity.INTENT_VENUE_ID, venue.getId());
			tag = HomeActivity.TAG_WRITE_REVIEW_WINDOW;
			break;
			
		case R.id.venue_favorite:
			windowFrag = new FavoriteWindow();
			extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
			extras.putLong(HomeActivity.INTENT_VENUE_ID, venue.getId());
			tag = HomeActivity.TAG_FAVORITE_WINDOW;
			break;
			
		case R.id.venue_request:
			windowFrag = new SubmitRequestWindow();
			extras.putInt(HomeActivity.INTENT_TABLE_NUM, tableNum);
			extras.putLong(HomeActivity.INTENT_VENUE_ID, venue.getId());
			tag = HomeActivity.TAG_REQUEST_WINDOW;
			break;
			
		default:
			break;
		}
		
		windowFrag.setArguments(extras);
		windowFrag.show(activity.getFragmentManager(), tag);
		
		return true;
	}
}
package edu.uiuc.whosinline.windows;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.Venue;
import edu.uiuc.whosinline.database.DatabaseAccessObj;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitWaitTimeWindow extends BaseWindow {
	
	private TextView textViewVenueName;
	private EditText editTextWaitTime;
	
	private void submitWaitTime(int waitTimeMinutes, Venue venue) {
		
		DatabaseAccessObj dao = new DatabaseAccessObj(getActivity());
		dao.open();
		
		if (dao.getVenue(1, venue.getName()) == null) {
			dao.insertVenue(1, venue);
		}
		
		dao.close();
		
		Activity activity = this.getActivity();
		if (activity instanceof HomeActivity) {
			((HomeActivity) activity).refreshList(1);
		}
		
		Toast toast = Toast.makeText(getActivity(), "Wait time submitted", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	private boolean checkUserInput(String input) {
		if (input.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Submit Wait Time");
		
		// Get the layout inflater.
		LayoutInflater inflator = getActivity().getLayoutInflater();
		View view = inflator.inflate(R.layout.window_submit_wait_time, null);
		
		final Venue venue = getVenue(getArguments(), getActivity());
		
		textViewVenueName = (TextView) view.findViewById(R.id.venue_name_textview);
		editTextWaitTime = (EditText) view.findViewById(R.id.wait_time_edittext);
		positiveButton = (Button) view.findViewById(R.id.positive_button);
		negativeButton = (Button) view.findViewById(R.id.negative_button);
		
		textViewVenueName.setText(venue.getName());
		
		builder.setView(view);
		
		// Add action buttons.
		positiveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Give user confirmation the wait time is submitted.
				String input = editTextWaitTime.getText().toString();
				
				if (checkUserInput(input)) {
					submitWaitTime(Integer.parseInt(input), venue);
					SubmitWaitTimeWindow.this.getDialog().dismiss();
				} else {
					Toast toast = Toast.makeText(getActivity(), "Invalid input", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		negativeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SubmitWaitTimeWindow.this.getDialog().cancel();
			}
		});
		
		return builder.create();
	}
}
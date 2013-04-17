package edu.uiuc.whosinline.windows;

import edu.uiuc.whosinline.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class WriteReviewWindow extends BaseWindow {
	
	private TextView textViewVenueName;
	private EditText editTextTitle;
	private EditText editTextContent;
	private RatingBar ratingBar;
	
	private void submitReview(String title, String content, float rating) {
		Toast toast = Toast.makeText(getActivity(), "Review submitted", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	private boolean checkUserInput(String title, String content) {
		if (title.isEmpty()) {
			return false;
		}
		if (content.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Write Review");
		
		// Get the layout inflater.
		LayoutInflater inflator = getActivity().getLayoutInflater();
		View view = inflator.inflate(R.layout.window_write_review, null);
		
		textViewVenueName = (TextView) view.findViewById(R.id.venue_name_textview);
		editTextTitle = (EditText) view.findViewById(R.id.review_title_edittext);
		editTextContent = (EditText) view.findViewById(R.id.review_content_edittext);
		ratingBar = (RatingBar) view.findViewById(R.id.review_ratingbar);
		positiveButton = (Button) view.findViewById(R.id.positive_button);
		negativeButton = (Button) view.findViewById(R.id.negative_button);
		
		textViewVenueName.setText(getVenue(getArguments(), getActivity()).getName());

		builder.setView(view);
		
		// Add action buttons
		positiveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Give user confirmation the review is submitted.
				String title = editTextTitle.getText().toString();
				String content = editTextContent.getText().toString();
				float rating = ratingBar.getRating();
				
				if (checkUserInput(title, content)) {
					submitReview(title, content, rating);
					WriteReviewWindow.this.getDialog().dismiss();
				} else {
					Toast toast = Toast.makeText(getActivity(), "Invalid input", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		negativeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WriteReviewWindow.this.getDialog().cancel();
			}
		});
		
		return builder.create();
	}
}
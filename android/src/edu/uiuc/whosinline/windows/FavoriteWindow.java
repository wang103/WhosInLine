package edu.uiuc.whosinline.windows;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.Venue;
import edu.uiuc.whosinline.database.DatabaseAccessObj;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteWindow extends BaseWindow {

	private TextView textViewPrompt1;
	private TextView textViewVenueName;
	private TextView textViewPrompt2;
	
	private boolean addToFavorite;
	private Venue venue;
	
	private void AddRemoveFavorite() {
		
		DatabaseAccessObj dao = new DatabaseAccessObj(getActivity());
		dao.open();
		
		if (addToFavorite) {
			venue.setId(dao.getTableRowCount(2));
			dao.insertVenue(2, venue);
		} else {
			dao.deleteVenue(2, venue);
		}
		
		dao.close();
		
		HomeActivity ha = (HomeActivity) this.getActivity();
		ha.refreshList(2);
		
		String feedback;
		if (addToFavorite) {
			feedback = "Venue added to favorites";
		} else {
			feedback = "Venue removed from favorites";
		}
		
		Toast toast = Toast.makeText(getActivity(), feedback, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		venue = getVenue(getArguments(), getActivity());
		addToFavorite = true;
		DatabaseAccessObj dao = new DatabaseAccessObj(getActivity());
		dao.open();
		Venue temp = dao.getVenue(2, venue.getName());
		if (temp != null) {
			venue = temp;
			addToFavorite = false;
		}
		dao.close();

		String title = addToFavorite ? "Add to Favorites" : "Remove from Favorites";
		builder.setTitle(title);
		
		// Get the layout inflater.
		LayoutInflater inflator = getActivity().getLayoutInflater();
		View view = inflator.inflate(R.layout.window_add_favorite, null);

		textViewVenueName = (TextView) view.findViewById(R.id.venue_name_textview);
		textViewPrompt1 = (TextView) view.findViewById(R.id.favorite_prompt1);
		textViewPrompt2 = (TextView) view.findViewById(R.id.favorite_prompt2);
		positiveButton = (Button) view.findViewById(R.id.positive_button);
		negativeButton = (Button) view.findViewById(R.id.negative_button);
		
		textViewVenueName.setText(venue.getName());
		if (addToFavorite) {
			textViewPrompt1.setText("Do you want to add");
			textViewPrompt2.setText("to favorites?");
		} else {
			textViewPrompt1.setText("Do you want to remove");
			textViewPrompt2.setText("from favorites?");
		}
		
		builder.setView(view);
		
		// Add action buttons
		positiveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddRemoveFavorite();
				FavoriteWindow.this.getDialog().dismiss();
			}
		});
		
		negativeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FavoriteWindow.this.getDialog().cancel();
			}
		});
		
		return builder.create();
	}
}
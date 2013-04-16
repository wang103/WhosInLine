package edu.uiuc.whosinline.windows;

import edu.uiuc.whosinline.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class ChatWindow extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Chat");
		
		// Get the layout inflater.
		LayoutInflater inflator = getActivity().getLayoutInflater();
		
		// Inflate and set the layout for the dialog.
		// Pass null as the parent view because it's going in the dialog layout.
		builder.setView(inflator.inflate(R.layout.window_chat_venue, null))
		// Add action buttons
			.setPositiveButton(R.string.button_positive, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Confirm the wait time is submitted.
					
				}
			})
			.setNegativeButton(R.string.button_negative, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ChatWindow.this.getDialog().cancel();
				}
			});
		
		return builder.create();
	}
}
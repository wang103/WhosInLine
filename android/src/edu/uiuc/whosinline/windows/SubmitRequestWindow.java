package edu.uiuc.whosinline.windows;

import edu.uiuc.whosinline.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitRequestWindow extends BaseWindow {

	private TextView textViewVenueName;
	private Spinner typeSpinner;
	private EditText editTextInput;
	
	private void submitRequest(String type, String request) {
		Toast toast = Toast.makeText(getActivity(), "Request submitted", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	private boolean checkUserInput(String request) {
		if (request.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Submit Request");
		
		// Get the layout inflater.
		LayoutInflater inflator = getActivity().getLayoutInflater();
		View view = inflator.inflate(R.layout.window_submit_request, null);
		
		textViewVenueName = (TextView) view.findViewById(R.id.venue_name_textview);
		typeSpinner = (Spinner) view.findViewById(R.id.type_spinner);
		editTextInput = (EditText) view.findViewById(R.id.request_content_edittext);
		positiveButton = (Button) view.findViewById(R.id.positive_button);
		negativeButton = (Button) view.findViewById(R.id.negative_button);
		
		textViewVenueName.setText(getVenue(getArguments(), getActivity()).getName());

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.request_type_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeSpinner.setAdapter(adapter);
		
		builder.setView(view);
		
		// Add action buttons
		positiveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Give user confirmation the review is submitted.
				String request = editTextInput.getText().toString();
				
				if (checkUserInput(request)) {
					submitRequest("", request);
					SubmitRequestWindow.this.getDialog().dismiss();
				} else {
					Toast toast = Toast.makeText(getActivity(), "Invalid input", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		negativeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SubmitRequestWindow.this.getDialog().cancel();
			}
		});
		
		return builder.create();
	}
}
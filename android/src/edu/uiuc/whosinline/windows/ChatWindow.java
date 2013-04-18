package edu.uiuc.whosinline.windows;

import edu.uiuc.whosinline.HomeActivity;
import edu.uiuc.whosinline.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatWindow extends BaseWindow {

	private TextView textViewPrompt;
	private TextView textViewVenueName;
	private TextView textViewChat;
	private EditText editTextInput;
	private Button sendButton;
	
	private void submitChat(String chat) {
		textViewChat.setText(textViewChat.getText() + "\n" + "You: " + chat);
		textViewChat.scrollTo(0, textViewChat.getLineHeight() * (textViewChat.getLineCount() - 8));
		editTextInput.setText("");
	}
	
	private boolean checkUserInput(String chat) {
		if (chat.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Chat");
		
		// Get the layout inflater.
		LayoutInflater inflator = getActivity().getLayoutInflater();
		View view = inflator.inflate(R.layout.window_chat_venue, null);
		
		textViewPrompt = (TextView) view.findViewById(R.id.chat_prompt_textview);
		textViewVenueName = (TextView) view.findViewById(R.id.venue_name_textview);
		textViewChat = (TextView) view.findViewById(R.id.chat_textview);
		editTextInput = (EditText) view.findViewById(R.id.chat_edittext);
		sendButton = (Button) view.findViewById(R.id.chat_send_button);
		
		Bundle extras = getArguments();
		boolean chatType = extras.getBoolean(HomeActivity.INTENT_CHAT_TYPE);
		if (chatType) {
			textViewVenueName.setText(getVenue(extras, getActivity()).getName());
		}
		else {
			textViewPrompt.setText("You are currently chatting with\npeople around you.");
		}
		
		textViewChat.setMovementMethod(new ScrollingMovementMethod());
		
		builder.setView(view);
		
		// Add action buttons
		sendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String chat = editTextInput.getText().toString();
				if (checkUserInput(chat)) {
					submitChat(chat);
				}
			}
		});
		
		return builder.create();
	}
}
package edu.uiuc.whosinline.fragments;

import edu.uiuc.whosinline.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NearbyFragment extends Fragment {
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 // inflate the layout for this fragment
		 return inflater.inflate(R.layout.fragment_main, container, false);
	 }
}

package edu.uiuc.whosinline.fragments;

import java.util.List;

import edu.uiuc.whosinline.R;
import edu.uiuc.whosinline.data.Venue;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class NearbyFragment extends BaseFragment {
	
	final private int dbNum = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<Venue> venues = dbAccessObj.getAllVenues(dbNum);
		
		ArrayAdapter<Venue> adapter = new ArrayAdapter<Venue>(getActivity(),
				R.layout.list_cell_venue, venues);
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment.
		return inflater.inflate(R.layout.fragment_nearby, container, false);
	}
}
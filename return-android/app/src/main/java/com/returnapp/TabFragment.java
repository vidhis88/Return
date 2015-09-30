package com.returnapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vidhi on 8/27/15.
 */
public class TabFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.tab_fragment, container, false);

		RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list_items);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setHasFixedSize(true);

		List<LocationItemDto> list = new ArrayList<>();

		LocationItemDto item1 = new LocationItemDto();
		item1.setLocationName("location 1");
		item1.setLocationTime(DateFormat.getDateTimeInstance().format(new Date()));
		item1.setLatLong("-32, 45.00");
		list.add(item1);

		LocationItemDto item2 = new LocationItemDto();
		item2.setLocationName("location 2");
		item2.setLocationTime(DateFormat.getDateTimeInstance().format(new Date()));
		item2.setLatLong("0.35, 20.22");
		list.add(item2);

		LocationItemDto item3 = new LocationItemDto();
		item3.setLocationName("location 3");
		item3.setLocationTime(DateFormat.getDateTimeInstance().format(new Date()));
		item3.setLatLong("34, -200");
		list.add(item3);

		LocationItemDto item4 = new LocationItemDto();
		item4.setLocationName("location 4");
		item4.setLocationTime(DateFormat.getDateTimeInstance().format(new Date()));
		item4.setLatLong("-37.23, 44.12");
		list.add(item4);

		ItemListAdapter adapter = new ItemListAdapter(list);
		recyclerView.setAdapter(adapter);

		return rootView;
	}
}

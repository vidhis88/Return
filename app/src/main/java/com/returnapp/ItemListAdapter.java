package com.returnapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vidhi on 8/29/15.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
	private List<LocationItemDto> items;

	public ItemListAdapter(List<LocationItemDto> items) {
		this.items = items;
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_detail_item, null);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
		LocationItemDto item = items.get(position);

		itemViewHolder.setLocName(item.getLocationName());
		itemViewHolder.setLocTime(item.getLocationTime());
		itemViewHolder.setLatLong(item.getLatLong());
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public class ItemViewHolder extends RecyclerView.ViewHolder {

		private TextView locNameTv;
		private TextView locTimeTv;
		private TextView latLongTv;

		public ItemViewHolder(View itemView) {
			super(itemView);

			locNameTv = (TextView) itemView.findViewById(R.id.locNameTv);
			locTimeTv = (TextView) itemView.findViewById(R.id.locTimeTv);
			latLongTv = (TextView) itemView.findViewById(R.id.latLongTv);
		}

		public void setLocName(String locName) {
			locNameTv.setText(locName);
		}

		public void setLocTime(String locTime) {
			locTimeTv.setText(locTime);
		}

		public void setLatLong(String latLong) {
			latLongTv.setText(latLong);
		}
	}
}

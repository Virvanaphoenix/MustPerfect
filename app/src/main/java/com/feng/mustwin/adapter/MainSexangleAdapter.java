package com.feng.mustwin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.custom.vg.list.CustomAdapter;
import com.feng.mustwin.R;

import java.util.List;

public class MainSexangleAdapter extends CustomAdapter {

	private List<String> list;
	private Context con;
	private LayoutInflater inflater;

	public MainSexangleAdapter(Context context, List<String> list) {
		this.con = context;
		this.list = list;
		inflater = LayoutInflater.from(con);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if(convertView == null){
			vh = new ViewHolder();
			convertView = inflater.inflate(R.layout.adapter_sexangle_item_style, null);
			vh.tv = (TextView) convertView.findViewById(R.id.adapter_text);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}

		String str = list.get(position);
		vh.tv.setText(str);

		return convertView;
	}

	public class ViewHolder{
		public TextView tv;
	}

}

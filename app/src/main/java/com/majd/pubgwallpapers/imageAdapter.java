package com.majd.pubgwallpapers;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class imageAdapter extends BaseAdapter {

	private Context mContext;
	public String[] imageArray={
	};

	public imageAdapter(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public int getCount() { return imageArray.length; }

	@Override
	public Object getItem(int position) {
		return imageArray[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		int ws= Resources.getSystem().getDisplayMetrics().widthPixels;
		int width = ws/2;
		int hs= Resources.getSystem().getDisplayMetrics().heightPixels;
		int h=hs/10;
		int height= (hs/2)-h;


		ImageView imageView=new ImageView(mContext);
		String url = imageArray[position];
		Picasso.get().load(url).placeholder(R.drawable.load_pro).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(imageView);

		//imageView.setImageResource(Picasso.get().load(url).placeholder(R.drawable.load_pro).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(width, height));

		return imageView;
	}
}

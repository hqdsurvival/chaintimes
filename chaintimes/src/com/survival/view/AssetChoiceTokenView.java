package com.survival.view;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.cry.library.base.BaseView;
import com.cry.library.util.ImageLoaderUtil;
import com.survival.chaintimes.R;
import com.survival.model.Coin;

/**
 * 货币展示view
 * @author Survival
 *
 */
public class AssetChoiceTokenView extends BaseView<Coin> implements OnClickListener {

	public AssetChoiceTokenView(Activity context, Resources resources) {
		super(context, resources);
		// TODO Auto-generated constructor stub
	}

	private ImageView img_token;
	private TextView tv_token_name;
	private TextView tv_token_desc;
	private ImageView img_choice;
	
	@Override
	public View createView(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.list_asset_token, null);
		
		img_token = (ImageView) convertView.findViewById(R.id.img_token);
		tv_token_desc = (TextView) convertView.findViewById(R.id.tv_token_desc);
		tv_token_name = (TextView) convertView.findViewById(R.id.tv_token_name);
		img_choice = (ImageView) convertView.findViewById(R.id.img_choice);
		
		return convertView;
	}

	@Override
	public void bindView(Coin data) {
		// TODO Auto-generated method stub
		if(data ==null){
			data = new Coin();
		}
		this.data = data;
		ImageLoaderUtil.loadImage(img_token, data.getCoin_img());
		tv_token_name.setText(data.getCoin_name());
		tv_token_desc.setText(data.getCoin_detail());
		
		if(data.isCoin_choice()){
			img_choice.setImageResource(R.drawable.check_yes);
		}else{
			img_choice.setImageResource(R.drawable.check_no);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}

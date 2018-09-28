package com.eagle.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.eagle.sdwan.R;



public class PopMore extends PopupWindow implements OnClickListener {

	private OnMoreItemClick itemClick;
	private float density = 0;

	

	public PopMore(Context context){
		super(context);
		
	}

	/**
	 * @param context
	 */
	public PopMore(Context context, OnMoreItemClick itemClick) {
		this(context);
		this.itemClick = itemClick;
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.activity_main_more, null);
		view.findViewById(R.id.txtReport).setOnClickListener(this);
		view.findViewById(R.id.txtInfo).setOnClickListener(this);
		view.findViewById(R.id.txtTraffic).setOnClickListener(this);
		view.findViewById(R.id.txtConfig).setOnClickListener(this);

		setContentView(view);
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setBackgroundDrawable(context.getResources().getDrawable(
				R.color.transparent));
		setOutsideTouchable(false);
		setFocusable(true);

	}
	


	@Override
	public void onClick(View v) {
		if (itemClick == null) {
			return;
		}
		switch (v.getId()) {
		case R.id.txtReport:
			itemClick.onItemReport();
			break;
		case R.id.txtInfo:
			itemClick.onItemLogs();
			break;
		case R.id.txtTraffic:
			itemClick.onItemTraffic();
			break;
		case R.id.txtConfig:
			itemClick.onItemConfig();
			break;
		}
		dismiss();
	}


	private float getDensity(Context context) {
		if (density == 0) {
			density = context.getResources().getDisplayMetrics().density;
		}
		return density;
	}

	public void showWindow(View anchor) {
//		if (isCasting) {
//			imgStatus.setImageResource(R.drawable.icon_pop_pause);
//			txtStatus.setText(R.string.pop_pause_cast);
//		} else {
//			imgStatus.setImageResource(R.drawable.icon_pop_start);
//			txtStatus.setText(R.string.pop_resume_cast);
//		}
		showAsDropDown(anchor, anchor.getWidth() - getWidth(), (int)(-11.5*getDensity(anchor.getContext().getApplicationContext())));
	}
	
	@Override
	public void update() {
		super.update();
	}

	public interface OnMoreItemClick {
		void onItemReport();

		void onItemLogs();

		void onItemTraffic();
		
		void onItemConfig();
		
		/*void onSelectIdc();
		
		void onItemLiveSwitch();

		void onItemHongbao();
		
		void onItemBeauty();
		
		void onItemAsStart();

        void onItemSkinSwitch();*/
    }

}

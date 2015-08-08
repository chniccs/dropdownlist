package com.yufenit.dropdownlist.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yufenit.dropdownlist.R;

public class MyEditText extends RelativeLayout {

	/**
	 * 下拉框
	 */
	private PopupWindow pop;
	/**
	 * 展示的数据的集合
	 */
	private List<String> list;
	/**
	 * 点击条目的监听器
	 */
	protected onItemClickecdListener listener;
	/**
	 * 条目被点击后是否显示条目中的信息到输入框中
	 */
	private boolean showClickedItemInfoToEditText=true;

	public MyEditText(Context context) {
		super(context);
		initView(context);
	}

	public MyEditText(Context context, AttributeSet attrs) {

		super(context, attrs);
		initView(context);
	}

	/**
	 * 初始化view的方法 
	 * @param context
	 */
	public void initView(final Context context) {

		RelativeLayout rl = (RelativeLayout) View.inflate(context,
				R.layout.my_edittext, null);
		this.addView(rl);

		final EditText et = (EditText) rl.findViewById(R.id.et);

		ImageView iv = (ImageView) rl.findViewById(R.id.iv);

		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (list == null) {
					return;
				}

				ListView lv = new ListView(context);
				lv.setBackgroundResource(R.drawable.listview_background);
				if (pop != null) {
					pop.dismiss();
					pop = null;
				}

				pop = new PopupWindow(lv, et.getWidth(), 100);
				pop.setFocusable(true);
				pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				pop.setOutsideTouchable(true);
				lv.setAdapter(new MyAdapter());
				pop.showAsDropDown(et);

				if (listener != null) {
					lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {

							listener.itemClicked(position);
							if(showClickedItemInfoToEditText){
								et.setText(list.get(position).toString());
							}
							pop.dismiss();
						}
					});
				}

			}
		});

	}
	/**
	 * 设置下拉列表激活的方法，如果要实现点击出现下拉列表必须要设置此方法
	 * @param list 要展示的数据的集合，必须要设置
	 * @param listener 条目被点击的监听器，传null则不会响应点击。
	 * @param showClickedItemInfoToEditText 条目被点击后是否显示条目中的信息到输入框中,默认为true
	 */
	public void setDropDownList(List<String> list,
			onItemClickecdListener listener,boolean showClickedItemInfoToEditText) {
		this.list = list;
		this.listener = listener;
		this.showClickedItemInfoToEditText=showClickedItemInfoToEditText;
	}

	public interface onItemClickecdListener {
		
		public void itemClicked(int position);
		
		
	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			TextView tv;
			if (convertView == null) {
				tv = new TextView(getContext());
			} else {
				tv = (TextView) convertView;
			}
			tv.setPadding(5, 5, 5, 5);
			tv.setTextColor(Color.BLACK);
			tv.setTextSize(18);
			tv.setText(list.get(position));

			return tv;
		}

	}

}

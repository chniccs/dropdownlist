package com.yufenit.dropdownlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.yufenit.dropdownlist.ui.MyEditText;
import com.yufenit.dropdownlist.ui.MyEditText.onItemClickecdListener;

public class MainActivity extends Activity {

	private List<String> list;
	
	private MyEditText et;

	private PopupWindow pop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list=new ArrayList<String>();
		
		et=(MyEditText) findViewById(R.id.et);
		
		for (int i = 0; i < 10; i++) {
			list.add("153153"+i);
		}
		
		onItemClickecdListener listener=new onItemClickecdListener() {
			
			@Override
			public void itemClicked(int position) {
				Toast.makeText(getApplicationContext(), "heyhey", 0).show();
			}
		};
		
		et.setDropDownList(list,listener , false);
		
	}

}

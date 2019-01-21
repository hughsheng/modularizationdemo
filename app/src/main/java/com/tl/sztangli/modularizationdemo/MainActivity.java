package com.tl.sztangli.modularizationdemo;

import android.os.Bundle;
import android.widget.TextView;
import com.tl.commonsdk.activity.BaseToolBarActivity;
import butterknife.BindView;


public class MainActivity extends BaseToolBarActivity {

  @BindView(R.id.main_tv)
  TextView main_tv;

  @Override
  public int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override
  public void initalFragment(Bundle savedInstanceState) {
    main_tv.setText("测试butterKnife");
  }

}

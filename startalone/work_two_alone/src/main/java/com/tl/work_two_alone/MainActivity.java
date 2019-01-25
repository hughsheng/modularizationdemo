package com.tl.work_two_alone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tl.commonsdk.activity.BaseToolBarActivity;
import com.tl.commonsdk.util.ActivityUtils;
import com.tl.work_two.WorkTwoFragment;

public class MainActivity extends BaseToolBarActivity {


  @Override
  public void initalFragment(Bundle savedInstanceState) {
    WorkTwoFragment workTwoFragment = WorkTwoFragment.newInstance();
    ActivityUtils.addFragmentToActivity(mFragmentManager, workTwoFragment, R.id.container,
        WorkTwoFragment.TAG);
  }

  @Override
  public int getLayoutResId() {
    return R.layout.activity_base;
  }
}

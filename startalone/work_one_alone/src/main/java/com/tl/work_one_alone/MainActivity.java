package com.tl.work_one_alone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tl.commonsdk.activity.BaseToolBarActivity;
import com.tl.commonsdk.util.ActivityUtils;
import com.tl.work_one.WorkOneFragment;

public class MainActivity extends BaseToolBarActivity {


  @Override
  public void initalFragment(Bundle savedInstanceState) {
    WorkOneFragment workOneFragment = WorkOneFragment.newInstance();
    ActivityUtils.addFragmentToActivity(mFragmentManager, workOneFragment, R.id.container,
        WorkOneFragment.TAG);
  }

  @Override
  public int getLayoutResId() {
    return R.layout.activity_base;
  }
}

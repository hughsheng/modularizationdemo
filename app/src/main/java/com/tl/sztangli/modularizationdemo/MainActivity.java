package com.tl.sztangli.modularizationdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.tl.commonsdk.activity.BaseToolBarActivity;
import com.tl.work_one.WorkOneFragment;
import com.tl.work_two.WorkTwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseToolBarActivity {

  @BindView(R.id.vp)
  ViewPager vp;

  @Override
  public int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override
  public void initalFragment(Bundle savedInstanceState) {
    WorkOneFragment workOneFragment = WorkOneFragment.newInstance();
    WorkTwoFragment workTwoFragment = WorkTwoFragment.newInstance();
    List<Fragment> fragmentList = new ArrayList<>();
    fragmentList.add(workOneFragment);
    fragmentList.add(workTwoFragment);
    VpAdapter vpAdapter = new VpAdapter(mFragmentManager, fragmentList);
    vp.setAdapter(vpAdapter);

  }

}

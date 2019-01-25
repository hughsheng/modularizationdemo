package com.tl.work_one;

import android.os.Bundle;

import com.tl.commonsdk.fragment.BaseFragment;

/**
 * created by tl on 2019-1-25
 * 业务组件1
 */
public class WorkOneFragment extends BaseFragment {

  public static final String TAG = "WorkOneFragment";

  public static WorkOneFragment newInstance() {

    Bundle args = new Bundle();

    WorkOneFragment fragment = new WorkOneFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getLayoutResId() {
    return R.layout.fragment_work_one;
  }

  @Override
  public void initialization() {

  }


  @Override
  public void onUnBind() {

  }
}

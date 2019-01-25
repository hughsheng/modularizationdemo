package com.tl.work_two;

import android.os.Bundle;

import com.tl.commonsdk.fragment.BaseFragment;

/**
 * created by tl on 2019-1-25
 */
public class WorkTwoFragment extends BaseFragment {

  public static final String TAG = "WorkTwoFragment";

  public static WorkTwoFragment newInstance() {

    Bundle args = new Bundle();

    WorkTwoFragment fragment = new WorkTwoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getLayoutResId() {
    return R.layout.fragment_work_two;
  }

  @Override
  public void initialization() {

  }

  @Override
  public void onUnBind() {

  }
}

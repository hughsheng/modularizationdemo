package com.tl.commonsdk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tl.commonsdk.R;

import butterknife.ButterKnife;

/**
 * created by tl on 2019-1-28
 * 网络请求fragment基类
 */
public abstract class BaseHttpFragment extends BaseFragment {
  protected LoadingDialogFragment loadingDialogFragment;

  protected LinearLayout netless_layout;
  protected TextView retry_tv;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    if (null != rootView) {
      ViewGroup parent = (ViewGroup) rootView.getParent();
      if (null != parent) {
        parent.removeView(rootView);
      }
    } else {
      rootView = inflater.inflate(getLayoutResId(), container, false);
      FrameLayout fragment_container = rootView.findViewById(R.id.fragment_container);
      inflater.inflate(getFragmentResId(), fragment_container);
    }

    netless_layout = rootView.findViewById(R.id.netless_layout);
    retry_tv = rootView.findViewById(R.id.retry_tv);

    mUnbinder = ButterKnife.bind(this, rootView);
    childManager = getChildFragmentManager();
    loadingDialogFragment = LoadingDialogFragment.newInstance();
    return rootView;
  }

  public void showLoading() {
    if (!loadingDialogFragment.isAdded()) {
      loadingDialogFragment.show(childManager, LoadingDialogFragment.TAG);
    }
  }

  public void showLoadingWithStatus(String status) {
    loadingDialogFragment.showWithStatus(childManager, status);
  }

  public void hideLoading() {
    loadingDialogFragment.dismiss();
  }

  @Override
  public int getLayoutResId() {
    return R.layout.fragment_http_base;
  }


  protected abstract int getFragmentResId();

}

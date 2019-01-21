package com.tl.commonsdk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sztangli on 2017/7/13.
 */

public abstract class BaseFragment extends Fragment {
  protected View rootView;
  protected Unbinder mUnbinder;
  protected FragmentManager childManager;
  protected LoadingDialogFragment loadingDialogFragment;

  public BaseFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

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
    }
    mUnbinder = ButterKnife.bind(this, rootView);
    childManager = getChildFragmentManager();
    loadingDialogFragment = LoadingDialogFragment.newInstance();
    return rootView;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initialization();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    if (mUnbinder != null) {
      mUnbinder.unbind();
      mUnbinder = null;
    }
  }

  public void showToastTip(int resId) {
    Toast.makeText(getContext(), getString(resId), Toast.LENGTH_SHORT).show();
  }

  public void showToastTip(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  public void showSnackBarTip(String message) {
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
  }

  public void showSnackBarTip(int resId) {
    Snackbar.make(rootView, resId, Snackbar.LENGTH_SHORT).show();
  }

  public void showLoading(FragmentManager manager) {
    loadingDialogFragment.show(manager, LoadingDialogFragment.TAG);
  }

  public void showLoadingWithStatus(FragmentManager manager, String status) {
    loadingDialogFragment.showWithStatus(manager, status);
  }

  public void hideLoading() {
    loadingDialogFragment.dismiss();
  }


  public abstract int getLayoutResId();

  public abstract void onUnBind();//fragment处于后台，进行一些对象的销毁操作

  //做一些初始化操作
  public abstract void initialization();

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    onUnBind();
  }
}

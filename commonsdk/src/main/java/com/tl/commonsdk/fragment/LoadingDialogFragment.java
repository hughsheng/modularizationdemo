package com.tl.commonsdk.fragment;

import android.graphics.PorterDuff;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.TextView;

import com.tl.commonsdk.R;

/**
 * created by tl on 2018-10-23
 * 加载框
 */
public class LoadingDialogFragment extends BaseDialogFragment {

  public static final String TAG = "LoadingDialogFragment";

  private String status;
  private int color;

  public static LoadingDialogFragment newInstance() {
    return new LoadingDialogFragment();
  }

  @Override
  public void onStart() {
    super.onStart();
    //  setGravity(Gravity.BOTTOM);
  }

  @Override
  protected void setContentView(View view) {
    TextView status_tv = view.findViewById(R.id.status_tv);
    ContentLoadingProgressBar load_progress = view.findViewById(R.id.load_progress);
    if (status != null) {
      status_tv.setVisibility(View.VISIBLE);
      status_tv.setText(status);
    }

    if (color != 0) {
      load_progress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(),
          color), PorterDuff.Mode.MULTIPLY);
    }
    cancelable(false);
    cancelableOnTouchOutside(false);
  }


  @Override
  protected int getLayoutId() {
    return R.layout.loading;
  }


  public void showWithStatus(FragmentManager manager, String status) {
    this.status = status;
    if (!isAdded()) {
      show(manager, TAG);
    }
  }

  public void setColor(int color) {
    this.color = color;
  }

}

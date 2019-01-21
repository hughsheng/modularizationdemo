package com.tl.commonsdk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tl.commonsdk.R;
import com.tl.commonsdk.util.ScreenUtil;

/**
 * Created by sztangli on 2017/7/13.
 * 包含toolbar的activity基类
 */

public abstract class BaseToolBarActivity extends BaseActivity {
  protected TextView titleCenter;
  protected Toolbar toolbar;
  protected ImageView back_iv;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initalFragment(savedInstanceState);
    initToolBar();
  }

  protected void initToolBar() {
    titleCenter = findViewById(R.id.title_center);
    toolbar = findViewById(R.id.toolbar);
    back_iv = findViewById(R.id.back_iv);
    back_iv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override
  public void hideStatusBar() {
    super.hideStatusBar();
    toolbar.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
      @Override
      public void onSystemUiVisibilityChange(int visibility) {
        toolbar.setPadding(toolbar.getPaddingLeft(), 0, toolbar.getPaddingRight(), toolbar
            .getPaddingBottom() - ScreenUtil.getStatusHeight(BaseToolBarActivity.this));
      }
    });
  }

  @Override
  public void showStatusBar() {
    super.showStatusBar();
    toolbar.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
      @Override
      public void onSystemUiVisibilityChange(int visibility) {

        toolbar.setPadding(toolbar.getPaddingLeft(), ScreenUtil.getStatusHeight
            (BaseToolBarActivity.this), toolbar.getPaddingRight(), toolbar.getPaddingBottom() +
            ScreenUtil.getStatusHeight(BaseToolBarActivity.this));
      }
    });
  }

  public void setTitleCenter(String title) {
    titleCenter.setText(title);
  }

  public abstract void initalFragment(Bundle savedInstanceState);

}

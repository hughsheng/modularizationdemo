package com.tl.work_two;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tl.commonsdk.fragment.BaseHttpFragment;
import com.tl.router.WorkOneRouter;

import java.util.zip.CheckedOutputStream;

import butterknife.BindView;
import io.github.prototypez.appjoint.AppJoint;

/**
 * created by tl on 2019-1-25
 */
public class WorkTwoFragment extends BaseHttpFragment {

  public static final String TAG = "WorkTwoFragment";

  @BindView(R2.id.work_two_tv)
  TextView work_two_tv;

  public static WorkTwoFragment newInstance() {

    Bundle args = new Bundle();

    WorkTwoFragment fragment = new WorkTwoFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  protected int getFragmentResId() {
    return R.layout.fragment_work_two;
  }

  @Override
  public void initialization() {
    final WorkOneRouter workOneRouter = AppJoint.service(WorkOneRouter.class);
    work_two_tv.setText("222222222");
    showLoading();
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        hideLoading();
        netless_layout.setVisibility(View.VISIBLE);
      }
    }, 3000);

    work_two_tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        workOneRouter.WorkTwoTest(getContext());
      }
    });

    retry_tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        netless_layout.setVisibility(View.GONE);
      }
    });
  }


  public void workTwoTest(Context context, String from) {
    Toast.makeText(context, "from：" + from, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onUnBind() {

  }
}

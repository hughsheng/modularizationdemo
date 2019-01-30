package com.tl.work_one;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tl.commonsdk.app.AppApplication;
import com.tl.commonsdk.fragment.BaseFragment;
import com.tl.router.AppRouter;
import com.tl.router.WorkTwoRouter;
import com.tl.work_one.app.WorkOneApiServiceComponent;
import com.tl.work_one.app.WorkOneApplication;

import butterknife.BindView;
import io.github.prototypez.appjoint.AppJoint;
import retrofit2.Retrofit;

/**
 * created by tl on 2019-1-25
 * 业务组件1
 */
public class WorkOneFragment extends BaseFragment {

  public static final String TAG = "WorkOneFragment";

  @BindView(R2.id.work_one_tv)
  TextView work_one_tv;


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
    WorkOneApiServiceComponent workOneApiServiceComponent =
        WorkOneApplication.getInstance().getApiServiceComponent();
    Retrofit retrofit = workOneApiServiceComponent.getRetrofit();
    final WorkTwoRouter workTwoRouter = AppJoint.service(WorkTwoRouter.class);
    work_one_tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        workTwoRouter.WorkTwoTest(getContext());
      }
    });
  }

  public void workOneTest(Context context, String from) {
    Toast.makeText(context, "from：" + from, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onUnBind() {

  }
}

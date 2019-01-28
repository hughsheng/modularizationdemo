package com.tl.work_two.app;

import android.content.Context;
import android.widget.Toast;

import com.tl.router.WorkTwoRouter;
import com.tl.work_two.WorkTwoFragment;

import io.github.prototypez.appjoint.core.ServiceProvider;

/**
 * created by tl on 2019-1-28
 */
@ServiceProvider
public class WorkTwoRouterImpl implements WorkTwoRouter {
  @Override
  public void WorkTwoTest(Context context) {
    WorkTwoFragment workTwoFragment = WorkTwoFragment.newInstance();
    workTwoFragment.workTwoTest(context, "workTow");
  }
}

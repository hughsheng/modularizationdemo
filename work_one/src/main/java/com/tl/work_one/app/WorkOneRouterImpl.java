package com.tl.work_one.app;

import android.content.Context;
import android.widget.Toast;

import com.tl.router.WorkOneRouter;
import com.tl.work_one.WorkOneFragment;

import io.github.prototypez.appjoint.core.ServiceProvider;

/**
 * created by tl on 2019-1-28
 */
@ServiceProvider
public class WorkOneRouterImpl implements WorkOneRouter {

  @Override
  public void WorkTwoTest(Context context) {
    WorkOneFragment workOneFragment = WorkOneFragment.newInstance();
    workOneFragment.workOneTest(context,"workOne");
  }

}

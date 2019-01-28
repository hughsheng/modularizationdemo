package com.tl.work_two.app;

import com.tl.commonsdk.app.AppApplication;

import io.github.prototypez.appjoint.core.ModuleSpec;

/**
 * created by tl on 2019-1-25
 * 在这里进行业务组件的初始化
 */
@ModuleSpec
public class WorkTwoApplication extends AppApplication<WorkTwoApiServiceComponent> {
  @Override
  protected WorkTwoApiServiceComponent getComponent() {
    return null;
  }

  @Override
  protected String setSharedpreferenceFileName() {
    return null;
  }
}

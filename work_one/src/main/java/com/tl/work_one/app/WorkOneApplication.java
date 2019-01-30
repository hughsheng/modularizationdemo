package com.tl.work_one.app;

import com.tl.commonsdk.api.ApiServiceModule;
import com.tl.commonsdk.app.AppApplication;
import com.tl.work_one.BuildConfig;

import io.github.prototypez.appjoint.core.ModuleSpec;

/**
 * created by tl on 2019-1-25
 */
@ModuleSpec
public class WorkOneApplication extends AppApplication<WorkOneApiServiceComponent> {
  private static WorkOneApplication workOneApplication;
  private final String SPNAME = "work_one_sp";

  @Override
  public void onCreate() {
    super.onCreate();
    workOneApplication = this;
  }

  public static WorkOneApplication getInstance() {
    return workOneApplication;
  }


  @Override
  protected WorkOneApiServiceComponent getComponent() {
    return DaggerWorkOneApiServiceComponent.builder()
        .apiServiceModule(new ApiServiceModule(this, BuildConfig.SERVER_URL))
        .build();
  }

  @Override
  protected String setSharedpreferenceFileName() {
    return SPNAME;
  }


}

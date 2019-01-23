package com.tl.sztangli.modularizationdemo.base.app;

import com.tl.commonsdk.api.ApiServiceComponent;
import com.tl.commonsdk.api.ApiServiceModule;
import com.tl.commonsdk.app.AppApplication;
import com.tl.sztangli.modularizationdemo.BuildConfig;
import com.tl.sztangli.modularizationdemo.base.dagger.DaggerModularizationApiServiceComponent;
import com.tl.sztangli.modularizationdemo.base.dagger.ModularizationApiServiceComponent;

/**
 * created by tl on 2019-1-10
 */
public class ModularizationApplication extends AppApplication<ModularizationApiServiceComponent> {

  private final String SP_NAME = "modularization_sp";

  @Override
  protected ModularizationApiServiceComponent getComponent() {
    return DaggerModularizationApiServiceComponent.builder()
        .apiServiceModule(new ApiServiceModule(this, BuildConfig.SERVER_URL))
        .build();
  }


  @Override
  protected String setSharedpreferenceFileName() {
    return SP_NAME;
  }

}

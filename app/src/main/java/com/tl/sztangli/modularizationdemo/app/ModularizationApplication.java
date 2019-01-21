package com.tl.sztangli.modularizationdemo.app;

import com.tl.commonsdk.api.ApiServiceComponent;
import com.tl.commonsdk.app.AppApplication;
import com.tl.sztangli.modularizationdemo.BuildConfig;

/**
 * created by tl on 2019-1-10
 */
public class ModularizationApplication extends AppApplication {

  @Override
  protected ApiServiceComponent getComponent() {
    return null;
  }

  @Override
  protected String setSharedpreferenceFileName() {
    return null;
  }

}

package com.tl.work_one.app;

import com.tl.commonsdk.app.AppApplication;
import io.github.prototypez.appjoint.core.ModuleSpec;

/**
 * created by tl on 2019-1-25
 */
@ModuleSpec
public class WorkOneApplication extends AppApplication<WorkOneApiServiceComponent> {
  @Override
  protected WorkOneApiServiceComponent getComponent() {
    return null;
  }

  @Override
  protected String setSharedpreferenceFileName() {
    return null;
  }
}

package com.tl.sztangli.modularizationdemo.base.app;

import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.tl.commonsdk.api.ApiServiceComponent;
import com.tl.commonsdk.api.ApiServiceModule;
import com.tl.commonsdk.app.AppApplication;
import com.tl.sztangli.modularizationdemo.BuildConfig;
import com.tl.sztangli.modularizationdemo.base.dagger.DaggerModularizationApiServiceComponent;
import com.tl.sztangli.modularizationdemo.base.dagger.ModularizationApiServiceComponent;

import butterknife.ButterKnife;
import io.github.prototypez.appjoint.core.AppSpec;

/**
 * created by tl on 2019-1-10
 */
@AppSpec
public class ModularizationApplication extends AppApplication<ModularizationApiServiceComponent> {

  private final String SP_NAME = "modularization_sp";


  @Override
  public void onCreate() {
    super.onCreate();
    initDebug();
  }

  @Override
  protected ModularizationApiServiceComponent getComponent() {
    return DaggerModularizationApiServiceComponent.builder()
        .apiServiceModule(new ApiServiceModule(this, BuildConfig.SERVER_URL))
        .build();
  }

  private void initDebug() {
    ButterKnife.setDebug(com.tl.commonsdk.BuildConfig.DEBUG);
    if (com.tl.commonsdk.BuildConfig.DEBUG) {         //调试模式下,开启严格模式检测
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()         //将对当前线程应用该策略
          .detectDiskReads()         //监控磁盘读
          .detectDiskWrites()        //监控磁盘写
          .detectNetwork()           //监控网络访问
          .detectAll()               //检测当前线程所有函数
          .penaltyLog()              //表示将警告输出到LogCat，你也可以使用其他或增加新的惩罚（penalty）函数
//                    .penaltyDeath()            //检测到不合法,杀死进程,自动退出app
          .build());
      LeakCanary.install(this);
    }
  }


  @Override
  protected String setSharedpreferenceFileName() {
    return SP_NAME;
  }

}

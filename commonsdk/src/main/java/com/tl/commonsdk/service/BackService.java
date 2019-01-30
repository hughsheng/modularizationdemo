package com.tl.commonsdk.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.tl.commonsdk.app.AppApplication;

/**
 * .
 * 开启后台下载服务
 */
public class BackService extends IntentService {

  public static final String ACTION_CACHE = "service.action.cache";

  public BackService() {
    super("BackService");
  }

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      final String action = intent.getAction();
      final Bundle bundle = intent.getExtras();
      if (ACTION_CACHE.equals(action)) {

      }

    }

  }


}

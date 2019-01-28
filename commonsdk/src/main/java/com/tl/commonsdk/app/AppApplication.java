package com.tl.commonsdk.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.tl.commonsdk.BuildConfig;
import com.tl.commonsdk.api.ApiServiceComponent;
import com.tl.commonsdk.api.ApiServiceModule;
import com.tl.commonsdk.api.DaggerApiServiceComponent;
import com.tl.commonsdk.service.BackService;
import com.tl.commonsdk.util.NetworkUtils;
import com.tl.commonsdk.util.SharedPreferencesUtils;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import io.github.prototypez.appjoint.core.AppSpec;
import io.github.prototypez.appjoint.core.ModuleSpec;


/**
 * Created by sztangli on 2017/7/13.
 * 全局基础类，管理全局单例
 */
public abstract class AppApplication<T extends ApiServiceComponent> extends Application {
  private static AppApplication mApplication;//把application设置为静态对象
  private T mApiServiceComponent;
  private SharedPreferencesUtils mSharedPreferencesUtils;
  private String baseUrl;
  private int width = 0, height = 0;
  private String versionName;
  private int versionCode;
  //管理所有的Activity
  private HashMap<String, AppCompatActivity> mActivityHashMap = new HashMap<>();

  public static AppApplication getInstance() {
    return mApplication;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    mApplication = this;
    mSharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
    mApiServiceComponent = getComponent();
//    DaggerApiServiceComponent.builder()
//        .apiServiceModule(new ApiServiceModule(this, getBaseUrl()))
//        .build();
  }


  /**
   * 开启后台服务器 (采用IntentService服务形式加载)
   *
   * @param action (动作)
   * @param bundle (绑定数据)
   */
  public void startBackService(String action, Bundle bundle) {
    Intent backService = new Intent(this, BackService.class);
    backService.setAction(action);
    if (bundle != null) {
      backService.putExtras(bundle);
    }
    startService(backService);
  }


  public T getApiServiceComponent() {
    return mApiServiceComponent;
  }


  /**
   * 采用andriod本身数据格式缓存数据
   * 后期可以更改成其他缓存
   */
  public void saveCacheData(String key, Object data) {
    saveCacheData(setSharedpreferenceFileName(), key, data);
  }

  private void saveCacheData(final String fileName, final String key, final Object defaultObject) {
    mSharedPreferencesUtils.saveData(fileName, key, defaultObject);
  }

  public Object getCacheData(String key, Object defaultObject) {
    return getCacheData(SharedPreferencesUtils.SP_NAME, key, defaultObject);
  }

  private Object getCacheData(final String fileName, final String key, final Object defaultObject) {
    return mSharedPreferencesUtils.getData(fileName, key, defaultObject);
  }

  public void saveCacheListData(String key, final List<Map<String, String>> dataList) {
    saveCacheListData(SharedPreferencesUtils.SP_NAME, key, dataList);
  }

  private void saveCacheListData(final String fileName, final String key, final List<Map<String,
      String>> dataList) {
    mSharedPreferencesUtils.saveListData(fileName, key, dataList);
  }

  public List<Map<String, String>> getCacheListData(final String key) {
    return getCacheListData(SharedPreferencesUtils.SP_NAME, key);
  }

  private List<Map<String, String>> getCacheListData(final String fileName, final String key) {
    return mSharedPreferencesUtils.getListData(fileName, key);
  }

  public void removeListData(final String key) {
    mSharedPreferencesUtils.removeListData(SharedPreferencesUtils.SP_NAME, key);
  }

  public void saveCacheStringListData(String key, final List<String> dataList) {
    mSharedPreferencesUtils.saveStringListData(SharedPreferencesUtils.SP_NAME, key, dataList);
  }

  public List<String> getCacheStringListData(final String key) {
    return mSharedPreferencesUtils.getStringListData(SharedPreferencesUtils.SP_NAME, key);
  }

  public void saveMapData(String key, Map<String, String> mapData) {
    mSharedPreferencesUtils.saveMapData(SharedPreferencesUtils.SP_NAME, key, mapData);
  }

  public Map<String, String> getMapData(String key) {
    return mSharedPreferencesUtils.getMapData(SharedPreferencesUtils.SP_NAME, key);
  }

  public void saveTreeMapData(String key, Map<String, String> mapData) {
    mSharedPreferencesUtils.saveTreeMapData(SharedPreferencesUtils.SP_NAME, key, mapData);
  }

  public Map<String, String> getTreeMapData(String key) {
    return mSharedPreferencesUtils.getTreeMapData(SharedPreferencesUtils.SP_NAME, key);
  }


  /**
   * 网络相关
   *
   * @return true表示已连接, false表示未连接
   */
  public boolean isContented() {
    return NetworkUtils.isNetworkAvailable(getApplicationContext());
  }

  public String getNetWorkIp() {
    String ip;
    if (NetworkUtils.isWifi(getApplicationContext())) {
      ip = NetworkUtils.getWifiIp(getApplicationContext());
    } else {
      ip = NetworkUtils.getGPRSIpAddress();
    }
    return ip;
  }

  public AppCompatActivity getActivity(String acString) {
    AppCompatActivity activity = null;
    if (!mActivityHashMap.isEmpty()) {
      activity = mActivityHashMap.get(acString);
    }
    return activity;
  }


  private void initProperty() {
    DisplayMetrics metric = new DisplayMetrics();
    WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    wm.getDefaultDisplay().getMetrics(metric);
    width = metric.widthPixels; // 屏幕宽度（像素）
    height = metric.heightPixels; // 屏幕高度（像素
  }

  public int getWindowWidth() {
    if (width == 0) {
      initProperty();
    }
    return this.width;
  }

  public int getWindowHeight() {
    if (height == 0) {
      initProperty();
    }
    return this.height;
  }

  protected abstract T getComponent();

  protected abstract String setSharedpreferenceFileName();
}

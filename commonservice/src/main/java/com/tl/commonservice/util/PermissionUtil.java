package com.tl.commonservice.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * created by tl on 2019-1-14
 * 动态申请权限
 */
public class PermissionUtil {

  public static final int PERMISSIONS_REQUEST_READ_WRITE = 0x9001;


  //申请读写权限
  public static void requestReadAndWritePermission(AppCompatActivity activity) {
    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
        != PackageManager.PERMISSION_GRANTED
        || ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

      ActivityCompat.requestPermissions(activity,
          new String[]{
              Manifest.permission.READ_PHONE_STATE,
              Manifest.permission.WRITE_EXTERNAL_STORAGE
          }, PERMISSIONS_REQUEST_READ_WRITE);

    }
//    else {
//      Toast.makeText(activity, "已有所需权限", Toast.LENGTH_SHORT).show();
//    }
  }

}

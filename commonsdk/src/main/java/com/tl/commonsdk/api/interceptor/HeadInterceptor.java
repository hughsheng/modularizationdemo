package com.tl.commonsdk.api.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tl on 2018-8-9
 * 请求头拦截器
 */
public class HeadInterceptor implements Interceptor {

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request request = chain.request()
        .newBuilder()
        .header("Content-Type", "application/json") //若是已存在的头必须用.header来重写不能用addHeader
        .addHeader("Authorization", "Bearer ")
        .build();
    return chain.proceed(request);
  }

}

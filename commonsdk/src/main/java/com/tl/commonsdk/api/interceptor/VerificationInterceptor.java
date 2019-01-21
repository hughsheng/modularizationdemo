package com.tl.commonsdk.api.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/**
 * Created by fangpenglin on 16/6/18.
 * 认证拦截器
 */
public class VerificationInterceptor implements Interceptor {

  private static final Charset UTF8 = Charset.forName("UTF-8");
  private static final String ENCODE = "ftly-securitycode";

  public VerificationInterceptor() {

  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    Response response = chain.proceed(request);
    String head = response.header(ENCODE, "0");
    ResponseBody responseBody = response.body();
    BufferedSource source = responseBody.source();
    source.request(Long.MAX_VALUE);
    String bodyContent = source.buffer().clone().readString(UTF8);

    if (!"0".equals(head)) {
      String encryption = "";
      //  String encryption = Utils.encoderByMd5("leyou" + bodyContent.trim() + "fangte");
      if (!head.equals(encryption)) {//数据被串改

      } else {//数据正常

      }
    }
    return response;
  }
}

package com.tl.commonservice.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tl.commonservice.BuildConfig;
import com.tl.commonservice.R;

/**
 * 微信支付回调activity
 */
public class WXPayEntryActivity extends FragmentActivity implements
    IWXAPIEventHandler {

  private static final String TAG = "WXPayEntryActivity";
  private IWXAPI api;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.wxpay_result);
    api = WXAPIFactory.createWXAPI(this, BuildConfig.WEIXIN_APPID);
    api.handleIntent(getIntent(), this);
    Log.i(TAG, "WXPayEntryActivity");
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    setIntent(intent);
    api.handleIntent(intent, this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    api.unregisterApp();
    api.detach();
    api = null;
  }

  @Override
  public void onReq(BaseReq req) {
  }


  /**
   * 是一个activity，去哪儿是用了一个dialog，然后把activity的风格变成了透明的窗口话!
   * <p/>
   * 微信支付没有同步返回相应的结果，只返回给了服务器
   */
  @Override
  public void onResp(BaseResp resp) {
    Log.i(TAG, "WXPayEntryActivity====onResp" + resp.toString());
    Log.i(TAG, "WXPayEntryActivity====onResp" + resp.errStr);

    if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {// 点击完成的时候执行
      int resultCode = resp.errCode;
      if (resultCode == 0) {// 成功

      }
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }

}
package com.tl.commonservice.alipay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class AliPayUtil {


  private Activity mActivity;
  private AliPayResponseListener aliPayResponseListener;

  public AliPayUtil(Activity activity) {
    mActivity = activity;
  }

  public void setPaySuccessListener(AliPayResponseListener aliPayResponseListener) {
    this.aliPayResponseListener = aliPayResponseListener;
  }

  public void destroy() {
    aliPayResponseListener = null;
    mActivity = null;
  }


  /**
   * 支付宝支付业务：入参app_id
   */
  public static final String APPID = "2016091800537073";

  /**
   * 支付宝账户登录授权业务：入参pid值
   */
  public static final String PID = "2088602119455092";
  /**
   * 支付宝账户登录授权业务：入参target_id值
   */
  public static final String TARGET_ID = "";

  /** 商户私钥，pkcs8格式 */
  /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
  /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
  /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
  /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
  /**
   * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
   */
  public static final String RSA2_PRIVATE =
      "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCy4y4u82CVFezKe2ECFk+7eHl2438Fl68ERk" +
          "/lYeYWxciEZZBPl24wzeBoU5Iq1XuKDGxgGn+Pffrog0h0XjtdAak7" +
          "/qMTN4xuAZ6beMuI7ppAtC8YZCzvrtDInn8GLojv/q5Elyj5UvBFsGbnI6PAnDRmhhQRc5eqpAeMK3RwZ0Tj" +
          "+BmWWdmbmpMEJOWhjeonejN76fj3tLarJdwcDPbXS3CHT/1Yj4C7i6niVRlNtb1J/W3PbhUMJMFMmKhme" +
          "/GqtUx2OIJAhIeA43zw2OBjRbXLwv1EREayuTJteDiICkAE/xMK" +
          "/h0GWXciAcaLqPcexVuciWcls1rJAc2PrMQfAgMBAAECggEBALE3iMAwkYUAlHW/psnnipOhdUuHcoX60AK" +
          "/GVWYF9aFl/E5oK6IyTwGKQORjyNqHJoVVqQafDaOSqxfqr7Fv6+S/rIVwD5AcJlmd1d0YsbEAgKJvoI" +
          "+c5n7KJEnG5XnMff36XLAAXKGbMvNRxRC5W3iEbNjrqvfROhi2atOPWs/5YTcgxNKJszk" +
          "+YWHnMC0spO2jIq1DbL2B/cd90pyHWQrRrwRVkhGbhQuGaSh5xg" +
          "+au1blFMuratdb8X9w8CWHHkyOdIXfCIIpiqL6S4HJY9W+rlEJfSWczG+ZPZfX5tP3ojZFG8Z5VJTNA0c/s4P" +
          "/DX1J9VxxRxENP/OL7pDxNECgYEA+972XI3aQHREYcXxkogQi5hNiv/mkjRNinxE" +
          "/2u9FLLgNkpdS5PBtoHVEzsVYuulbl6CmOY9RfakXSGGooGXpMjpiWBnYzv4O8qn1" +
          "/vhpxHQW1WTpB5Uz27Zx8MJiTd4XCkpfy9l764TbWUQMg8syq9utqB5CAtoCK6fzmqRyBkCgYEAtdHsyhjgdj" +
          "/8Pybkj9Ufv9TtAlEpiF2WcBC" +
          "/A36EojSJBnQCCQh3khcf2xLQ5iz8HIt5L0woAjqGpvkWMBdAojIbvver3eCOnQsnP4z" +
          "/whluFxATxh1FYYnSx8zzvOVMYyDfvdWZiiycTT" +
          "/Bv2fdKqgm4C7Av4JaXLzAVsfQ1PcCgYEA5Fy3ShtjMuU3yt5JtO2g8rBYPhre1tiNJW6esGbi" +
          "vI2iNBcgl5GUJQF6S0FalqHJEFSoXDEEhRam17m59EVDVKjE9UVu9847txp7K5y4b137Z5QjlL3b" +
          "b2e2FSSE1OByRD0d3q7+4V0XSCxFjt2rYunEm9jLY7vTy2togSKmoqECgYEAgGf9Xs0q2NhfgQoq" +
          "qRnPDl+F8vj26AOQVxy8ie80JIoNq60KMu7z4wzJxqIHPkfO1Sn7L9gM+WXz6sBHVzdUwje5OKk" +
          "Zi90nWumhklQ13BKxmUAVdvj9IuF/JvQrvuABjpCM8ziC8v6GdFfusQrdO1799Z2PKrEYuB3liGQ" +
          "drAkCgYAOZPtJO/jJcocOzMzywFKRDQQwKhV2mh3LlAF+VI95qtESGncJnpFANrsEj6AEksqqIq" +
          "3X+1//t5l+cFZl5a8hpiNoYxyeZ7uBf2XvkH9qBsbBy8MbFW6SBv2ZfQB6g85pD2SKu+sSecUQgE9" +
          "VV45wboyZ1f7e23euM+xIdIecJg==";
  public static final String RSA_PRIVATE = "";

  private static final int SDK_PAY_FLAG = 1;
  private static final int SDK_AUTH_FLAG = 2;


  /**
   * 支付宝测试支付业务
   *
   * @param v
   */
  public void payV2(View v) {
    if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty
        (RSA_PRIVATE))) {
      new AlertDialog.Builder(mActivity).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
          .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
              //
              mActivity.finish();
            }
          }).show();
      return;
    }

    /**
     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     *
     * orderInfo的获取必须来自服务端；
     */
    boolean rsa2 = (RSA2_PRIVATE.length() > 0);
    Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
    String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

    String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
    String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
    final String orderInfo = orderParam + "&" + sign;

    AliPayTask aliPayTask = new AliPayTask();
    aliPayTask.execute(orderInfo);
  }

  /**
   * 支付宝正式支付
   *
   * @param orderInfo 服务器返回订单加签数据
   */
  public void pay2V(String orderInfo) {
    AliPayTask aliPayTask = new AliPayTask();
    aliPayTask.execute(orderInfo);
  }


  /**
   * 支付宝账户授权业务
   *
   * @param v
   */
  public void authV2(View v) {
    if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
        || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
        || TextUtils.isEmpty(TARGET_ID)) {
      new AlertDialog.Builder(mActivity).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| " +
          "RSA_PRIVATE| " +
          "TARGET_ID")
          .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
            }
          }).show();
      return;
    }

    /**
     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     *
     * authInfo的获取必须来自服务端；
     */
    boolean rsa2 = (RSA2_PRIVATE.length() > 0);
    Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID,
        rsa2);
    String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

    String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
    String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
    final String authInfo = info + "&" + sign;
    Runnable authRunnable = new Runnable() {

      @Override
      public void run() {
        // 构造AuthTask 对象
        AuthTask authTask = new AuthTask(mActivity);
        // 调用授权接口，获取授权结果
        Map<String, String> result = authTask.authV2(authInfo, true);

        Message msg = new Message();
        msg.what = SDK_AUTH_FLAG;
        msg.obj = result;
        //    mHandler.sendMessage(msg);
      }
    };

    // 必须异步调用
    Thread authThread = new Thread(authRunnable);
    authThread.start();
  }

  /**
   * get the sdk version. 获取SDK版本号
   */
  public void getSDKVersion() {
    PayTask payTask = new PayTask(mActivity);
    String version = payTask.getVersion();
    Toast.makeText(mActivity, version, Toast.LENGTH_SHORT).show();
  }

  /**
   * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
   *
   * @param v
   */
  public void h5Pay(View v) {
    Intent intent = new Intent(mActivity, H5PayActivity.class);
    Bundle extras = new Bundle();
    /**
     * url 是要测试的网站，在 Demo App 中会使用 H5PayActivity 内的 WebView 打开。
     *
     * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
     * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
     * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
     * 进行测试。
     *
     * H5PayActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
     * 可以参考它实现自定义的 URL 拦截逻辑。
     */
    String url = "https://www.baidu.com";
    extras.putString("url", url);
    intent.putExtras(extras);
    mActivity.startActivity(intent);
  }


  public interface AliPayResponseListener {
    /**
     * 支付结果
     *
     * @param result (支付宝支付结果)
     */
    void onAliPayResult(Map<String, String> result);
  }


  private class AliPayTask extends AsyncTask<String, Void, Map<String, String>> {

    @Override
    protected Map<String, String> doInBackground(String... params) {
      PayTask alipay = new PayTask(mActivity);
      // 调用支付接口，获取支付结果
      return alipay.payV2(params[0], true);
    }

    @Override
    protected void onPostExecute(Map<String, String> result) {
      super.onPostExecute(result);
      if (aliPayResponseListener != null && !isCancelled()) {
        aliPayResponseListener.onAliPayResult(result);
      }
    }
  }


}

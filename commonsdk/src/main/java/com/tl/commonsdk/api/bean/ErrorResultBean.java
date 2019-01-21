package com.tl.commonsdk.api.bean;
import com.tl.commonsdk.api.exception.ServerApiException;
import com.tl.commonsdk.util.ConstanceValue;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * 请求失败结果的bean
 */
public abstract class ErrorResultBean extends BaseResultBean implements Consumer<Throwable> {


  private boolean isSuccess;   //成功或失败

  private String codeDesc;     //状态码描述


  public boolean isSuccess() {
    return isSuccess;
  }

  public void setSuccess(boolean success) {
    isSuccess = success;
  }

  public String getCodeDesc() {
    return codeDesc;
  }

  public void setCodeDesc(String codeDesc) {
    this.codeDesc = codeDesc;
  }

  @Override
  public void accept(Throwable e) throws Exception {
    e.printStackTrace();  //打印异常信息
    ErrorBean errorBean = new ErrorBean();
    if (e instanceof HttpException) {
      int code = ((HttpException) e).code();
      errorBean.setErrorCode(code);
      errorBean.setErrorResult("网络异常");
    } else if (e instanceof SocketTimeoutException) {
      errorBean.setErrorCode(ConstanceValue.TIMEOUT_ERROR);
      errorBean.setErrorResult("链接超时");
    } else if (e instanceof JsonSyntaxException) {
      errorBean.setErrorCode(ConstanceValue.JSON_ERROR);
      errorBean.setErrorResult("数据解析异常");
    } else if (e instanceof ServerApiException) {   //服务器异常
      errorBean.setErrorCode(((ServerApiException) e).getCode());
      errorBean.setErrorResult(e.getMessage());
    } else if (e instanceof ConnectException) {
      errorBean.setErrorCode(ConstanceValue.CONNECT_ERROR);
      errorBean.setErrorResult("当前网络不可用，请检查网络");
    } else if (e instanceof UnknownHostException) {
      errorBean.setErrorCode(ConstanceValue.CONNECT_ERROR);
      errorBean.setErrorResult("当前网络不可用，请检查网络");
    } else {
      errorBean.setErrorCode(ConstanceValue.UNKNOW_ERROR);
      errorBean.setErrorResult("当前网络不可用，请检查网络");
    }
    onError(errorBean);
  }

  protected abstract void onError(ErrorBean errorBean);


  public class ErrorBean {
    private int errorCode;
    private String errorResult;

    public int getErrorCode() {
      return errorCode;
    }

    public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
    }

    public String getErrorResult() {
      return errorResult;
    }

    public void setErrorResult(String errorResult) {
      this.errorResult = errorResult;
    }
  }

}

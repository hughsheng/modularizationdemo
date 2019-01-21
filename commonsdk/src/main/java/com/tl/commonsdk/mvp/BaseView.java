package com.tl.commonsdk.mvp;

import android.support.annotation.NonNull;
import com.tl.commonsdk.api.bean.ErrorResultBean;

/**
 * Created by fangpenglin on 16/6/10.
 * 基础视图层管理
 */
public interface BaseView<T> {
  /**
   * 设置路由层
   *
   * @param presenter (根据泛型选定路由层)
   */
  void setPresenter(@NonNull T presenter);

  /**
   * 错误信息(包含错误编码和错误描述)
   *
   * @param errorBean (记录错误信息编码和错误信息描述)
   */
  void onLoadFail(ErrorResultBean.ErrorBean errorBean);

  //显示加载框
  void show();

  //隐藏加载框
  void hide();
}

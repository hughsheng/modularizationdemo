package com.tl.work_one.app;

import com.tl.commonsdk.api.ApiServiceComponent;
import com.tl.commonsdk.api.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * created by tl on 2019-1-25
 */
@Singleton
@Component(modules = {ApiServiceModule.class})
public interface WorkOneApiServiceComponent extends ApiServiceComponent {

}

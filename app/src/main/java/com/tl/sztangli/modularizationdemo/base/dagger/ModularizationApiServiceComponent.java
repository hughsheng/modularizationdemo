package com.tl.sztangli.modularizationdemo.base.dagger;

import com.tl.commonsdk.api.ApiServiceComponent;
import com.tl.commonsdk.api.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * created by tl on 2019-1-23
 *
 */
@Singleton
@Component(modules = {ApiServiceModule.class})
public interface ModularizationApiServiceComponent extends ApiServiceComponent {

}

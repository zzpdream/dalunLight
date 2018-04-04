package com.zz.led.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zz.led.mvp.contract.LogoContract;
import com.zz.led.mvp.model.LogoModel;

import dagger.Module;
import dagger.Provides;


@Module
public class LogoModule {
    private LogoContract.View view;

    /**
     * 构建LogoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LogoModule(LogoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LogoContract.View provideLogoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LogoContract.Model provideLogoModel(LogoModel model) {
        return model;
    }
}
package com.zz.led.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.zz.led.di.module.LogoModule;
import com.zz.led.mvp.ui.activity.ChooseLanguageActivity;
import com.zz.led.mvp.ui.activity.LogoActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LogoModule.class, dependencies = AppComponent.class)
public interface LogoComponent {
    void inject(LogoActivity activity);
    void inject(ChooseLanguageActivity activity);
}
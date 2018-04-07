package com.zz.led.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.jess.arms.utils.DataHelper;
import com.zz.led.di.component.DaggerLogoComponent;
import com.zz.led.di.module.LogoModule;
import com.zz.led.mvp.contract.LogoContract;
import com.zz.led.mvp.presenter.UserPresenter;

import com.zz.led.R;
import com.zz.led.utils.WeakHandler;


import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 *  主要是为了获取sokcet连接IP以及自动登录
 */
public class LogoActivity extends BaseActivity<UserPresenter> implements LogoContract.View {

    WeakHandler handler=new WeakHandler();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLogoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .logoModule(new LogoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_logo; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        int isFirst= DataHelper.getIntergerSF(this,"isFirst");
        if(isFirst<0){//默认值-1 为首次进入
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LogoActivity.this,ChooseLanguageActivity.class));
                }
            },1000);
        }else{//登录并进入首页

        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

}

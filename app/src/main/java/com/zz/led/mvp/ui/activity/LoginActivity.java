package com.zz.led.mvp.ui.activity;

import android.os.Bundle;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zz.led.R;
import com.zz.led.mvp.presenter.UserPresenter;

/**
 * Created by Administrator on 2018/4/8.
 */

public class LoginActivity extends BaseActivity<UserPresenter>  {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}

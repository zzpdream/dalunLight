package com.zz.led.mvp.ui.activity;

import android.os.Bundle;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zz.led.R;
import com.zz.led.mvp.model.entity.CountryBean;
import com.zz.led.utils.LanguageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzpdream on 2018/4/4.
 */

public class ChooseLanguageActivity extends BaseActivity {

    private List<CountryBean> languages = new ArrayList<>();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_choose_language;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        languages.add(new CountryBean(getResources().getString(R.string.china_simple), LanguageUtils.CHOOSE_CHINA));
        languages.add(new CountryBean(getResources().getString(R.string.england), LanguageUtils.CHOOSE_ENGLAND));
        languages.add(new CountryBean(getResources().getString(R.string.japan), LanguageUtils.CHOOSE_JAPAN));
        languages.add(new CountryBean(getResources().getString(R.string.germany), LanguageUtils.CHOOSE_GERMANY));
        languages.add(new CountryBean(getResources().getString(R.string.dutch), LanguageUtils.CHOOSE_HOLLAND));
        languages.add(new CountryBean(getResources().getString(R.string.china_hk), LanguageUtils.CHOOSE_CHINA_TAIWAN));
        languages.add(new CountryBean(getResources().getString(R.string.czech), LanguageUtils.CHOOSE_CZECH));
    }
}

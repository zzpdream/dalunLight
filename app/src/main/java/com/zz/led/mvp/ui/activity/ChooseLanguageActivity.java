package com.zz.led.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.zz.led.R;
import com.zz.led.mvp.model.entity.CountryBean;
import com.zz.led.mvp.ui.adapter.ChooseLanguageAdapter;
import com.zz.led.utils.LanguageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzpdream on 2018/4/4.
 */

public class ChooseLanguageActivity extends BaseActivity {

    @BindView(R.id.language_list)
    RecyclerView languageList;

    private List<CountryBean> languages = new ArrayList<>();

    private ChooseLanguageAdapter adapter;

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

        adapter = new ChooseLanguageAdapter(languages);
        languageList.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ArmsUtils.configRecyclerView(languageList, linearLayoutManager);
        languageList.setNestedScrollingEnabled(false);
    }
}

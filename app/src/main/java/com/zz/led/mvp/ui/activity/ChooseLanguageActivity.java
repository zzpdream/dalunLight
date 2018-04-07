package com.zz.led.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.zz.led.R;
import com.zz.led.di.component.DaggerLogoComponent;
import com.zz.led.di.module.LogoModule;
import com.zz.led.mvp.contract.LogoContract;
import com.zz.led.mvp.model.entity.CountryBean;
import com.zz.led.mvp.presenter.UserPresenter;
import com.zz.led.mvp.ui.adapter.ChooseLanguageAdapter;
import com.zz.led.socket.NettyService;
import com.zz.led.utils.LanguageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzpdream on 2018/4/4.
 */

public class ChooseLanguageActivity extends BaseActivity<UserPresenter> implements LogoContract.View {

    @BindView(R.id.language_list)
    RecyclerView languageList;

    private List<CountryBean> languages = new ArrayList<>();

    private ChooseLanguageAdapter adapter;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLogoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .logoModule(new LogoModule(this))
                .build()
                .inject(this);
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

        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener<CountryBean>() {
            @Override
            public void onItemClick(View view, int viewType, CountryBean data, int position) {
                mPresenter.getHosts(data);
            }
        });
        startService(new Intent(this, NettyService.class));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, NettyService.class));
    }
}

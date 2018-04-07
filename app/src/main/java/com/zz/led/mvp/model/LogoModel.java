package com.zz.led.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zz.led.mvp.contract.LogoContract;
import com.zz.led.mvp.model.Api.services.CommonService;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


@ActivityScope
public class LogoModel extends BaseModel implements LogoContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LogoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<String> getServerIps(Map<String,String> params) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getServerIp(params);
    }
}
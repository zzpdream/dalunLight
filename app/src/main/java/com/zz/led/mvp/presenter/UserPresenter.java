package com.zz.led.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.zz.led.mvp.contract.LogoContract;
import com.zz.led.mvp.model.entity.CountryBean;
import com.zz.led.utils.LanguageUtils;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;


@ActivityScope
public class UserPresenter extends BasePresenter<LogoContract.Model, LogoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public UserPresenter(LogoContract.Model model, LogoContract.View rootView) {
        super(model, rootView);
    }

    public void getHosts(CountryBean bean){
        HashMap<String, String> data = new HashMap<>();
        data.put("sign", "getip");

        String code=bean.getCode();
        if(LanguageUtils.CHOOSE_CHINA.equals(code)){
            data.put("serverId", LanguageUtils.CHOOSE_CHINA);
        }else{
            data.put("serverId", LanguageUtils.CHOOSE_ENGLAND);
        }

        mModel.getServerIps(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                })
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏上拉加载更多的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull String result) {
                        Timber.e(result);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}

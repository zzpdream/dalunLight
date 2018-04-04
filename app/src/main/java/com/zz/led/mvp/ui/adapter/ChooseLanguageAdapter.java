package com.zz.led.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.zz.led.R;
import com.zz.led.mvp.model.entity.CountryBean;
import com.zz.led.mvp.ui.holder.LanguageHolder;

import java.util.List;

/**
 * Created by zzpdream on 2018/4/4.
 */

public class ChooseLanguageAdapter extends DefaultAdapter<CountryBean>{

    public ChooseLanguageAdapter(List<CountryBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<CountryBean> getHolder(View v, int viewType) {
        return new LanguageHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_choose_language;
    }
}

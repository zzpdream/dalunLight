package com.zz.led.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by Administrator on 2017/11/1.
 * 语言设置类
 */

public class LanguageUtils {


    public static final String CHOOSE_CHINA = "1";
    public static final String CHOOSE_ENGLAND = "2";
    public static final String CHOOSE_JAPAN = "3";
    public static final String CHOOSE_GERMANY = "4";
    public static final String CHOOSE_HOLLAND = "5";
    public static final String CHOOSE_RUSSIA = "6";
    public static final String CHOOSE_CHINA_TAIWAN = "7";
    public static final String CHOOSE_CZECH = "8";

    public final static Locale CHINA = Locale.CHINA;
    public final static Locale US = Locale.US;
    public final static Locale JAPAN = Locale.JAPAN;
    public final static Locale GERMANY = Locale.GERMANY;
    public final static Locale TAIWAN = Locale.TAIWAN;
    public final static Locale Holland = new Locale("nl", "NL", "");//定义荷兰
    public final static Locale Russia = new Locale("ru", "RU", "");//定义俄罗斯
    public final static Locale CZECH=new Locale("cs","CZ","");


    //    设置app的语言
    public static void setLanguage(Context context, Locale location) {
        //获取res对象
        Resources resources = context.getResources();
        //获得设置对象
        Configuration config = resources.getConfiguration();
        //获取屏幕参数 主要是分辨率,像素等
        DisplayMetrics dm = resources.getDisplayMetrics();
        //语言
        config.locale = location;
        resources.updateConfiguration(config, dm);
    }


}

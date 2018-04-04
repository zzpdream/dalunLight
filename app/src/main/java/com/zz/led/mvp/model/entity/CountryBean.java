package com.zz.led.mvp.model.entity;

/**
 * Created by zzpdream on 2018/4/4.
 */

public class CountryBean {

    private String country;
    private String code;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CountryBean(String country, String code) {
        this.country = country;
        this.code = code;
    }
}

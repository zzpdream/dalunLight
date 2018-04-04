package com.zz.led.mvp.model.Api.services;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zzpdream on 2018/4/4.
 */

public interface CommonService {

    @POST("api/dalen")
    @FormUrlEncoded
    Observable<ResponseBody> getServerIp(@FieldMap(encoded = true) Map<String, String> params);
}

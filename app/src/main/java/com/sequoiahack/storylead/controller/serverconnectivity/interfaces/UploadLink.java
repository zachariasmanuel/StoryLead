package com.sequoiahack.storylead.controller.serverconnectivity.interfaces;

import com.sequoiahack.storylead.controller.serverconnectivity.models.UploadLinkResult;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by zac on 10/09/16.
 */
public interface UploadLink {
    @FormUrlEncoded
    @POST("api/media/get-upload-url")
    void getUploadLink(@Field("name") String name, Callback<UploadLinkResult> callback);
}

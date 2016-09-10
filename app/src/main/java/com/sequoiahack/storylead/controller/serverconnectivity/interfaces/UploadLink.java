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
    @POST("/media/get_upload_url")
        //@POST("/v2/57d464970f0000eb082d86f8")
    void getUploadLink(@Field("name") String name, Callback<UploadLinkResult> callback);
}

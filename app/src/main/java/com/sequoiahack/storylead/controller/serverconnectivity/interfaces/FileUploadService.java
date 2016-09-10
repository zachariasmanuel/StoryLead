package com.sequoiahack.storylead.controller.serverconnectivity.interfaces;

import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Interface for fileupload
 * Created by zac on 11/09/16.
 */
public interface FileUploadService {

    @Multipart
    @PUT("")
    void upload(@Part("myfile") TypedFile file, @Part("description") String description, Callback<String> cb);
}

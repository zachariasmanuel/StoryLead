package com.sequoiahack.storylead.controller.serverconnectivity.interfaces;

import retrofit.RetrofitError;

/**
 * Created by zac on 10/09/16.
 */
public interface ServerInternalListener {
    void onUploadLinkReceived(String url);

    void onUploadLinkGettingFailed();

    void onRetrofitError(RetrofitError error);

    void onUploadSuccess();

    void onUploadFailed();
}

package com.sequoiahack.storylead.controller.serverconnectivity;

import android.util.Log;

import com.sequoiahack.storylead.app.AppConstant;
import com.sequoiahack.storylead.controller.serverconnectivity.interfaces.FileUploadService;
import com.sequoiahack.storylead.controller.serverconnectivity.interfaces.ServerInternalListener;
import com.sequoiahack.storylead.controller.serverconnectivity.interfaces.UploadLink;
import com.sequoiahack.storylead.controller.serverconnectivity.models.UploadLinkResult;

import java.io.File;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by zac on 10/09/16.
 */
public class ServerConnection {

    private static String baseURL;

    static {
        baseURL = AppConstant.CURRENT_SERVER;
    }

    private RestAdapter restAdapter;
    private ServerInternalListener mServerInternalListener;

    public ServerConnection(ConnectionManager connectionManager) {
        mServerInternalListener = connectionManager;
    }


    public void getUploadLink(String name, String accessToken) {
        final String header = accessToken;
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", header);
            }
        };
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .setRequestInterceptor(requestInterceptor).setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        final UploadLink uploadLink = restAdapter.create(UploadLink.class);
        uploadLink.getUploadLink(name, new Callback<UploadLinkResult>() {

            @Override
            public void success(UploadLinkResult uploadLinkResult, Response response) {
                if (response.getStatus() == 200) {
                    mServerInternalListener.onUploadLinkReceived(uploadLinkResult.getUrl());
                } else {
                    mServerInternalListener.onUploadLinkGettingFailed();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mServerInternalListener.onRetrofitError(error);
            }
        });

    }

    public void uploadFile(String url, String filepath) {

        ServiceGenerator.API_BASE_URL = url;

        FileUploadService service = ServiceGenerator.createService(FileUploadService.class);
        TypedFile typedFile = new TypedFile("multipart/form-data", new File(filepath));
        String description = "hello, this is description speaking";

        service.upload(typedFile, description, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                if (response.getStatus() == 200) {
                    showLog("ServerConnection - File Upload success");
                    mServerInternalListener.onUploadSuccess();
                } else {
                    showLog("ServerConnection - File Upload failed");
                    mServerInternalListener.onUploadFailed();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mServerInternalListener.onRetrofitError(error);
            }
        });
    }

    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }


}

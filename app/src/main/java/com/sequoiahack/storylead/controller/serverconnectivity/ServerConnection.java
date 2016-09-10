package com.sequoiahack.storylead.controller.serverconnectivity;

import android.os.AsyncTask;
import android.util.Log;

import com.sequoiahack.storylead.app.AppConstant;
import com.sequoiahack.storylead.controller.serverconnectivity.interfaces.ServerInternalListener;
import com.sequoiahack.storylead.controller.serverconnectivity.interfaces.UploadLink;
import com.sequoiahack.storylead.controller.serverconnectivity.models.UploadLinkResult;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    private String filePath;
    private String fileName;

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


    private class UploadAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            final MediaType MEDIA_TYPE_MP3 = MediaType.parse("audio/mpeg3");
            final OkHttpClient client = new OkHttpClient();

            // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("audio", fileName,
                            RequestBody.create(MEDIA_TYPE_MP3, new File(filePath)))
                    .build();

            // fileName = URLEncoder.encode(fileName, "UTF-8");
            Request request = new Request.Builder().addHeader("X-AMZ-ACL", "public-read-write")
                    .url("https://s3.ap-south-1.amazonaws.com/chakka/" + fileName)
                    .put(requestBody)
                    .build();

            okhttp3.Response response = null;
            try {
                response = client.newCall(request).execute();
                showLog(response.message());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.message().equals("OK"))
                return "OK";
            else
                return "Failed";
        }

        @Override
        protected void onPostExecute(String result) {
            showLog("Upload - " + result);
            if (result.equals("OK"))
                mServerInternalListener.onUploadSuccess();
            else
                mServerInternalListener.onUploadFailed();
        }

        @Override
        protected void onPreExecute() {
        }
    }


    public void uploadFile(String url, String filepath) {
        this.filePath = filepath;

        String[] fileComponents = filepath.split("/");
        this.fileName = fileComponents[fileComponents.length - 1];
        UploadAsync uploadAsync = new UploadAsync();
        uploadAsync.execute();


       /* ServiceGenerator serviceGenerator = new ServiceGenerator(url);

        FileUploadService service = serviceGenerator.createService(FileUploadService.class);
        TypedFile typedFile = new TypedFile("multipart/form-data", new File(filepath));
        String description = "hello, this is description speaking";

        service.upload(typedFile, description, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                if (response.getStatus() == 200) {
                    showLog("ServerConnection - File Upload success");

                } else {
                    showLog("ServerConnection - File Upload failed");
                    mServerInternalListener.onUploadFailed();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mServerInternalListener.onRetrofitError(error);
            }
        });*/
    }

    protected void showLog(String message) {
        if (AppConstant.ENABLE_LOG)
            Log.d(AppConstant.APP_TAG, message);
    }


}

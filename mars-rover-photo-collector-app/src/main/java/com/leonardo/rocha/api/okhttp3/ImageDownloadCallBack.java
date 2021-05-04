package com.leonardo.rocha.api.okhttp3;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageDownloadCallBack implements Callback {

    private String dest;

    public ImageDownloadCallBack(String dest) {
        this.dest = dest;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        System.out.println("request failed: " + e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.body() != null) {
            InputStream byteStream = response.body().byteStream();
            File destFile = new File(dest);
            FileUtils.copyInputStreamToFile(byteStream, destFile);
        }
    }
}

package com.leonardo.rocha.api;

import com.leonardo.rocha.api.okhttp3.ImageDownloadCallBack;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class UrlImageDownloader {

    @Autowired
    private OkHttpClient client;

    @Value("#{photoDir}")
    private String photoDir;

    public File download(String url){
        String parseFileName = getParsedFileName(url);
        String filePath = getFilePath(parseFileName);
        Request request = buildRequest(url);
        client.newCall(request).enqueue(new ImageDownloadCallBack(filePath));
        return new File(filePath);
    }

    private String getParsedFileName(String fileName){
        String[] tokens = fileName.split("/");
        return tokens[tokens.length - 1];
    }

    private String getFilePath(String fileName){
        return photoDir + "/" + fileName;
    }

    private Request buildRequest(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }
}

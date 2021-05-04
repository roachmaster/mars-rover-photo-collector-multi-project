package com.leonardo.rocha.api;

import com.leonardo.rocha.AutoValue.PhotoList;
import com.leonardo.rocha.AutoValue.RoverList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DefaultMarsRoverApiClient implements MarsRoverApiClient {

    @Value("#{apiKey}")
    private String apiKey;

    @Autowired
    private MarsRoverRestApi restApi;

    @Autowired
    private UrlImageDownloader imageDownloader;

    @Override
    public RoverList getRoverList() throws IOException {
        return restApi.getRoverList(apiKey).execute().body();
    }

    @Override
    public PhotoList getPhotoList(String name, String earthDate) throws IOException {
        return restApi.getPhotoList(name, earthDate, apiKey).execute().body();
    }

    @Override
    public File download(String roverName, String photoId, String imgSrc) {
        return imageDownloader.download(imgSrc);
    }

}

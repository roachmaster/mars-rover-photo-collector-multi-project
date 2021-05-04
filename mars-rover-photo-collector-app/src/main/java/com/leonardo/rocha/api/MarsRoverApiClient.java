package com.leonardo.rocha.api;

import com.leonardo.rocha.AutoValue.PhotoList;
import com.leonardo.rocha.AutoValue.RoverList;

import java.io.File;
import java.io.IOException;

public interface MarsRoverApiClient {
    RoverList getRoverList() throws IOException;
    PhotoList getPhotoList(String name, String earthDate) throws IOException;
    File download(String roverName, String photoId, String imgSrc);
}

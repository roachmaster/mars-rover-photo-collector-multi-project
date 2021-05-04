package com.leonardo.rocha.controller;

import com.leonardo.rocha.AutoValue.PhotoList;
import com.leonardo.rocha.AutoValue.RoverList;
import com.leonardo.rocha.api.MarsRoverApiClient;
import com.leonardo.rocha.endpoint.DatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class MarsRoverImageController {

    @Autowired
    private MarsRoverApiClient marsRoverApiClient;

    @Autowired
    private DatesService datesService;

    @RequestMapping(value = "dates", method = RequestMethod.GET)
    public List<String> getDates() {
        return datesService.getDates();
    }

    @RequestMapping(value = "rovers/{name}/photos", method = RequestMethod.GET)
    public PhotoList getRoverPhotos(@PathVariable String name, @RequestParam("earth_date") String date) throws IOException {
        return marsRoverApiClient.getPhotoList(name, date);
    }

    @RequestMapping(value = "rovers/{name}/photos/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImgUrl(@PathVariable String name, @PathVariable String id, @RequestParam("img_src") String imgSrc) throws IOException {
        File filePath = marsRoverApiClient.download(name, id, imgSrc);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(Files.readAllBytes(filePath.toPath()));
    }

    @RequestMapping(value = "rovers", method = RequestMethod.GET)
    public RoverList getRovers() throws IOException {
        return marsRoverApiClient.getRoverList();
    }
}
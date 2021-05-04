package com.leonardo.rocha.api;

import com.leonardo.rocha.AutoValue.PhotoList;
import com.leonardo.rocha.AutoValue.RoverList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MarsRoverRestApi {
    @GET("/mars-photos/api/v1/rovers")
    Call<RoverList> getRoverList(@Query("api_key") String apiKey);

    @GET("/mars-photos/api/v1/rovers/{name}/photos")
    Call<PhotoList> getPhotoList(@Path("name") String name,
                                 @Query("earth_date") String earthDate,
                                 @Query("api_key") String apiKey);
}

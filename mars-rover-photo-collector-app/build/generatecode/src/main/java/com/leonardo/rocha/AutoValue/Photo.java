package com.leonardo.rocha.AutoValue;

import com.google.auto.value.AutoValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Photo.Builder.class)
public abstract class Photo {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AutoValue.Builder
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public interface Builder {
        @JsonProperty("id")
        Builder id(String id);
        @JsonProperty("earth_date")
        Builder earthDate(String earthDate);
        @JsonProperty("img_src")
        Builder imgSrc(String imgSrc);
        @JsonProperty("camera")
        Builder camera(Camera camera);
        @JsonProperty("rover")
        Builder rover(Rover rover);
        Photo build();
    }

    @JsonProperty("id")
    public abstract String id();
    @JsonProperty("earth_date")
    public abstract String earthDate();
    @JsonProperty("img_src")
    public abstract String imgSrc();
    @JsonProperty("camera")
    public abstract Camera camera();
    @JsonProperty("rover")
    public abstract Rover rover();

    public static Builder builder() {
        return new AutoValue_Photo.Builder();
    }

    @JsonCreator
    static Photo create(
            @JsonProperty("id") String id,
            @JsonProperty("earth_date") String earthDate,
            @JsonProperty("img_src") String imgSrc,
            @JsonProperty("camera") Camera camera,
            @JsonProperty("rover") Rover rover){ 

        return builder()
              .id(id)
              .earthDate(earthDate)
              .imgSrc(imgSrc)
              .camera(camera)
              .rover(rover)
              .build();
    }
}

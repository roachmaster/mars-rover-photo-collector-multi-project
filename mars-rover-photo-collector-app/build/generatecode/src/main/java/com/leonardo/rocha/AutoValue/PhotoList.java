package com.leonardo.rocha.AutoValue;

import com.google.auto.value.AutoValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_PhotoList.Builder.class)
public abstract class PhotoList {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AutoValue.Builder
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public interface Builder {
        @JsonProperty("photos")
        Builder photos(List<Photo> photos);
        PhotoList build();
    }

    @JsonProperty("photos")
    public abstract List<Photo> photos();

    public static Builder builder() {
        return new AutoValue_PhotoList.Builder();
    }

    @JsonCreator
    static PhotoList create(
            @JsonProperty("photos") List<Photo> photos){ 

        return builder()
              .photos(photos)
              .build();
    }
}

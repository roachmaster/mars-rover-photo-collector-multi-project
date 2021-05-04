package com.leonardo.rocha.AutoValue;

import com.google.auto.value.AutoValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Camera.Builder.class)
public abstract class Camera {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AutoValue.Builder
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public interface Builder {
        @JsonProperty("id")
        Builder id(String id);
        @JsonProperty("name")
        Builder name(String name);
        @JsonProperty("full_name")
        Builder fullName(String fullName);
        Camera build();
    }

    @JsonProperty("id")
    public abstract String id();
    @JsonProperty("name")
    public abstract String name();
    @JsonProperty("full_name")
    public abstract String fullName();

    public static Builder builder() {
        return new AutoValue_Camera.Builder();
    }

    @JsonCreator
    static Camera create(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("full_name") String fullName){ 

        return builder()
              .id(id)
              .name(name)
              .fullName(fullName)
              .build();
    }
}

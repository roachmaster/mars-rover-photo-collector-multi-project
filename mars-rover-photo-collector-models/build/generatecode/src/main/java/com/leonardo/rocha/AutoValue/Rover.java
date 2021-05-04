package com.leonardo.rocha.AutoValue;

import com.google.auto.value.AutoValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_Rover.Builder.class)
public abstract class Rover {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AutoValue.Builder
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public interface Builder {
        @JsonProperty("id")
        Builder id(String id);
        @JsonProperty("name")
        Builder name(String name);
        Rover build();
    }

    @JsonProperty("id")
    public abstract String id();
    @JsonProperty("name")
    public abstract String name();

    public static Builder builder() {
        return new AutoValue_Rover.Builder();
    }

    @JsonCreator
    static Rover create(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name){ 

        return builder()
              .id(id)
              .name(name)
              .build();
    }
}

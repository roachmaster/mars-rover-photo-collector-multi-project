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
@JsonDeserialize(builder = AutoValue_RoverList.Builder.class)
public abstract class RoverList {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AutoValue.Builder
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public interface Builder {
        @JsonProperty("rovers")
        Builder rovers(List<Rover> rovers);
        RoverList build();
    }

    @JsonProperty("rovers")
    public abstract List<Rover> rovers();

    public static Builder builder() {
        return new AutoValue_RoverList.Builder();
    }

    @JsonCreator
    static RoverList create(
            @JsonProperty("rovers") List<Rover> rovers){ 

        return builder()
              .rovers(rovers)
              .build();
    }
}

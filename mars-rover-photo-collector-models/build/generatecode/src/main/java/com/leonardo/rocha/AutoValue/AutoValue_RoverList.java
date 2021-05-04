package com.leonardo.rocha.AutoValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_RoverList extends RoverList {

  private final List<Rover> rovers;

  private AutoValue_RoverList(
      List<Rover> rovers) {
    this.rovers = rovers;
  }

  @JsonProperty("rovers")
  @Override
  public List<Rover> rovers() {
    return rovers;
  }

  @Override
  public String toString() {
    return "RoverList{"
         + "rovers=" + rovers
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof RoverList) {
      RoverList that = (RoverList) o;
      return this.rovers.equals(that.rovers());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= rovers.hashCode();
    return h$;
  }

  static final class Builder implements RoverList.Builder {
    private List<Rover> rovers;
    Builder() {
    }
    @Override
    public RoverList.Builder rovers(List<Rover> rovers) {
      if (rovers == null) {
        throw new NullPointerException("Null rovers");
      }
      this.rovers = rovers;
      return this;
    }
    @Override
    public RoverList build() {
      String missing = "";
      if (this.rovers == null) {
        missing += " rovers";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_RoverList(
          this.rovers);
    }
  }

}

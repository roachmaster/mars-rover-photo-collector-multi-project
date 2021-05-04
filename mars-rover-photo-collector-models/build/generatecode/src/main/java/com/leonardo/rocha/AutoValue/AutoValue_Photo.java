package com.leonardo.rocha.AutoValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Photo extends Photo {

  private final String id;

  private final String earthDate;

  private final String imgSrc;

  private final Camera camera;

  private final Rover rover;

  private AutoValue_Photo(
      String id,
      String earthDate,
      String imgSrc,
      Camera camera,
      Rover rover) {
    this.id = id;
    this.earthDate = earthDate;
    this.imgSrc = imgSrc;
    this.camera = camera;
    this.rover = rover;
  }

  @JsonProperty("id")
  @Override
  public String id() {
    return id;
  }

  @JsonProperty("earth_date")
  @Override
  public String earthDate() {
    return earthDate;
  }

  @JsonProperty("img_src")
  @Override
  public String imgSrc() {
    return imgSrc;
  }

  @JsonProperty("camera")
  @Override
  public Camera camera() {
    return camera;
  }

  @JsonProperty("rover")
  @Override
  public Rover rover() {
    return rover;
  }

  @Override
  public String toString() {
    return "Photo{"
         + "id=" + id + ", "
         + "earthDate=" + earthDate + ", "
         + "imgSrc=" + imgSrc + ", "
         + "camera=" + camera + ", "
         + "rover=" + rover
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Photo) {
      Photo that = (Photo) o;
      return this.id.equals(that.id())
          && this.earthDate.equals(that.earthDate())
          && this.imgSrc.equals(that.imgSrc())
          && this.camera.equals(that.camera())
          && this.rover.equals(that.rover());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= id.hashCode();
    h$ *= 1000003;
    h$ ^= earthDate.hashCode();
    h$ *= 1000003;
    h$ ^= imgSrc.hashCode();
    h$ *= 1000003;
    h$ ^= camera.hashCode();
    h$ *= 1000003;
    h$ ^= rover.hashCode();
    return h$;
  }

  static final class Builder implements Photo.Builder {
    private String id;
    private String earthDate;
    private String imgSrc;
    private Camera camera;
    private Rover rover;
    Builder() {
    }
    @Override
    public Photo.Builder id(String id) {
      if (id == null) {
        throw new NullPointerException("Null id");
      }
      this.id = id;
      return this;
    }
    @Override
    public Photo.Builder earthDate(String earthDate) {
      if (earthDate == null) {
        throw new NullPointerException("Null earthDate");
      }
      this.earthDate = earthDate;
      return this;
    }
    @Override
    public Photo.Builder imgSrc(String imgSrc) {
      if (imgSrc == null) {
        throw new NullPointerException("Null imgSrc");
      }
      this.imgSrc = imgSrc;
      return this;
    }
    @Override
    public Photo.Builder camera(Camera camera) {
      if (camera == null) {
        throw new NullPointerException("Null camera");
      }
      this.camera = camera;
      return this;
    }
    @Override
    public Photo.Builder rover(Rover rover) {
      if (rover == null) {
        throw new NullPointerException("Null rover");
      }
      this.rover = rover;
      return this;
    }
    @Override
    public Photo build() {
      String missing = "";
      if (this.id == null) {
        missing += " id";
      }
      if (this.earthDate == null) {
        missing += " earthDate";
      }
      if (this.imgSrc == null) {
        missing += " imgSrc";
      }
      if (this.camera == null) {
        missing += " camera";
      }
      if (this.rover == null) {
        missing += " rover";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_Photo(
          this.id,
          this.earthDate,
          this.imgSrc,
          this.camera,
          this.rover);
    }
  }

}

package com.leonardo.rocha.AutoValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_PhotoList extends PhotoList {

  private final List<Photo> photos;

  private AutoValue_PhotoList(
      List<Photo> photos) {
    this.photos = photos;
  }

  @JsonProperty("photos")
  @Override
  public List<Photo> photos() {
    return photos;
  }

  @Override
  public String toString() {
    return "PhotoList{"
         + "photos=" + photos
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PhotoList) {
      PhotoList that = (PhotoList) o;
      return this.photos.equals(that.photos());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= photos.hashCode();
    return h$;
  }

  static final class Builder implements PhotoList.Builder {
    private List<Photo> photos;
    Builder() {
    }
    @Override
    public PhotoList.Builder photos(List<Photo> photos) {
      if (photos == null) {
        throw new NullPointerException("Null photos");
      }
      this.photos = photos;
      return this;
    }
    @Override
    public PhotoList build() {
      String missing = "";
      if (this.photos == null) {
        missing += " photos";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_PhotoList(
          this.photos);
    }
  }

}

package com.leonardo.rocha.AutoValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Camera extends Camera {

  private final String id;

  private final String name;

  private final String fullName;

  private AutoValue_Camera(
      String id,
      String name,
      String fullName) {
    this.id = id;
    this.name = name;
    this.fullName = fullName;
  }

  @JsonProperty("id")
  @Override
  public String id() {
    return id;
  }

  @JsonProperty("name")
  @Override
  public String name() {
    return name;
  }

  @JsonProperty("full_name")
  @Override
  public String fullName() {
    return fullName;
  }

  @Override
  public String toString() {
    return "Camera{"
         + "id=" + id + ", "
         + "name=" + name + ", "
         + "fullName=" + fullName
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Camera) {
      Camera that = (Camera) o;
      return this.id.equals(that.id())
          && this.name.equals(that.name())
          && this.fullName.equals(that.fullName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= id.hashCode();
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= fullName.hashCode();
    return h$;
  }

  static final class Builder implements Camera.Builder {
    private String id;
    private String name;
    private String fullName;
    Builder() {
    }
    @Override
    public Camera.Builder id(String id) {
      if (id == null) {
        throw new NullPointerException("Null id");
      }
      this.id = id;
      return this;
    }
    @Override
    public Camera.Builder name(String name) {
      if (name == null) {
        throw new NullPointerException("Null name");
      }
      this.name = name;
      return this;
    }
    @Override
    public Camera.Builder fullName(String fullName) {
      if (fullName == null) {
        throw new NullPointerException("Null fullName");
      }
      this.fullName = fullName;
      return this;
    }
    @Override
    public Camera build() {
      String missing = "";
      if (this.id == null) {
        missing += " id";
      }
      if (this.name == null) {
        missing += " name";
      }
      if (this.fullName == null) {
        missing += " fullName";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_Camera(
          this.id,
          this.name,
          this.fullName);
    }
  }

}

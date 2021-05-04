package com.leonardo.rocha.AutoValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Rover extends Rover {

  private final String id;

  private final String name;

  private AutoValue_Rover(
      String id,
      String name) {
    this.id = id;
    this.name = name;
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

  @Override
  public String toString() {
    return "Rover{"
         + "id=" + id + ", "
         + "name=" + name
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Rover) {
      Rover that = (Rover) o;
      return this.id.equals(that.id())
          && this.name.equals(that.name());
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
    return h$;
  }

  static final class Builder implements Rover.Builder {
    private String id;
    private String name;
    Builder() {
    }
    @Override
    public Rover.Builder id(String id) {
      if (id == null) {
        throw new NullPointerException("Null id");
      }
      this.id = id;
      return this;
    }
    @Override
    public Rover.Builder name(String name) {
      if (name == null) {
        throw new NullPointerException("Null name");
      }
      this.name = name;
      return this;
    }
    @Override
    public Rover build() {
      String missing = "";
      if (this.id == null) {
        missing += " id";
      }
      if (this.name == null) {
        missing += " name";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_Rover(
          this.id,
          this.name);
    }
  }

}

package com.example.jokerepo.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Joke {

  // final instance fields
  private String id;
  private String content;

  public Joke(Builder builder)
  {
    this.id = builder.id;
    this.content = builder.content;
  }

  // Static class Builder
  public static class Builder {

    /// instance fields
    private String id;
    private String content;

    public static Builder newJoke()
    {
      return new Builder();
    }

    private Builder() {}

    // Setter methods
    public Builder setId(String id)
    {
      this.id = id;
      return this;
    }
    public Builder setContent(String content)
    {
      this.content = content;
      return this;
    }

    // build method to deal with outer class
    // to return outer instance
    public Joke build()
    {
      return new Joke(this);
    }
  }
}
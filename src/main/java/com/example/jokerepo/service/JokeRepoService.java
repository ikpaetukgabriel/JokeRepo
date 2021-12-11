package com.example.jokerepo.service;

import com.example.jokerepo.model.Joke;
import com.example.jokerepo.model.Jokes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class JokeRepoService {

  private final Jokes jokes;

  @Autowired
  public JokeRepoService(Jokes jokes) {
    this.jokes = jokes;
  }

  public Jokes getJokes() {
    manufactureJokes();
    return jokes;
  }

  private void manufactureJokes() {
    IntStream.range(0, 10)
        .forEach(index -> jokes.addJoke(Joke.Builder
            .newJoke()
            .setId(UUID.randomUUID().toString())
            .setContent("Joke ".concat(String.valueOf(index)))
            .build()));
  }

}
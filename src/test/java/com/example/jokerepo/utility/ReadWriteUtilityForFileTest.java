package com.example.jokerepo.utility;

import com.example.jokerepo.model.Joke;
import com.example.jokerepo.model.Jokes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

class ReadWriteUtilityForFileTest {

  @Test
  void test_ReadAndWriteJokeToFile() throws JsonProcessingException {
    Jokes jokes = ReadWriteUtilityForFile.readJokesFromFile("src/main/resources/static/jokes.txt");

    if (isNull(jokes)) {
      Jokes newJokes = new Jokes();
      IntStream.range(0, 5)
          .forEach(index -> newJokes.addJoke(Joke.Builder
              .newJoke()
              .setId(UUID.randomUUID().toString())
              .setContent("Joke ".concat(String.valueOf(index)))
              .build()));

      ObjectMapper objectMapper = new ObjectMapper();
      String jokeAsString = objectMapper.writeValueAsString(newJokes);

      ReadWriteUtilityForFile.writeJokesToFile(jokeAsString,
          "src/main/resources/static/jokes.txt");
    }
  }
}
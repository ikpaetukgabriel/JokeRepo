package com.example.jokerepo.service;

import com.example.jokerepo.model.Joke;
import com.example.jokerepo.model.Jokes;
import com.example.jokerepo.utility.ReadWriteUtilityForFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JokeRepoServiceImpl implements JokeRepoService {

  private Jokes jokes;
  private final ObjectMapper objectMapper;
  private final List<String> jokeContents;
  private final String JOKE_FILE_LOCATION = "src/main/resources/static/jokes.txt";

  @Autowired
  public JokeRepoServiceImpl(Jokes jokes, ObjectMapper objectMapper, List<String> jokeContents) {
    this.jokes = jokes;
    this.objectMapper = objectMapper;
    this.jokeContents = jokeContents;
  }

  public Jokes getJokes() {
    jokes = readJokes();

    if (jokeContents.isEmpty()) {
      manufactureJokeStrings();
    }

    if (jokes.getJokes().isEmpty()) {
      writeJokesToFile(jokeContents);
      jokes = readJokes();
    }

    return jokes;
  }

  @SneakyThrows
  private void writeJokesToFile(List<String> jokeContent) {
    Jokes jokesToBeWritten = new Jokes();
    jokeContent
        .forEach(joke -> jokesToBeWritten.addJoke(Joke.Builder
            .newJoke()
            .setId(UUID.randomUUID().toString())
            .setContent(joke)
            .build()));

    String jokesToBeWrittenAsString = objectMapper.writeValueAsString(jokesToBeWritten);
    ReadWriteUtilityForFile.writeJokesToFile(jokesToBeWrittenAsString, JOKE_FILE_LOCATION);
  }

  private Jokes readJokes() {
    return ReadWriteUtilityForFile.readJokesFromFile(JOKE_FILE_LOCATION);
  }

  private void manufactureJokeStrings() {
    jokeContents.add("The generation of random numbers is too important to be left to chance.");
    jokeContents.add("The three most dangerous things in the world are a programmer with a soldering iron, " +
                    "a hardware engineer with a software patch, and a user with an idea.  " +
                    "– The Wizardry Compiled by Rick Cook");
    jokeContents.add("Two bytes meet.  The first byte asks, “Are you ill?”\n" +
                    "The second byte replies, “No, just feeling a bit off.”");
    jokeContents.add("There are only 10 kinds of people in this world: those who know binary and those who don’t.");
    jokeContents.add("Why do programmers always mix up Halloween and Christmas?\n" +
                    "Because Oct 31 equals Dec 25.");
    jokeContents.add("Have you heard about the new Cray super computer?  It’s so fast, it executes an infinite loop in 6 seconds.");
    jokeContents.add("Why did the programmer quit his job? Because he did not get arrays.");
    jokeContents.add("Algorithm- word used by programmers when they don’t want to explain what they did.");
    jokeContents.add("Q: Why do Java programmers have to wear glasses?\n" +
                    "A: Because, they don’t C#");
    jokeContents.add("Without google, most of us will not survive for six months in the profession of software development.\n" +
                    "Hence, when we write ten lines of code without having to copy-paste from anywhere, we feel like we moved a mountain with our bare hands.");
    jokeContents.add("Me: Will you be my Valentine?\n" +
                    "Girl: No way\n" +
                    "Me: sudo will you be my Valentine?\n" +
                    "Girl: Yes..yes..yes! Let’s go!" +
                    "The second byte replies, “No, just feeling a bit off.”");
    jokeContents.add("Two programmers are talking about their social life, and one says:\n" +
                    "–      The only date I get is the Java Update.");
    jokeContents.add("What is an object-oriented way to become wealthy?\n" +
                    "–      Inheritance.");
    jokeContents.add("An optimist says: “The glass is half-full”\n" +
                    "A pessimist says: “The glass is half empty”.\n" +
                    "A programmer says: “The glass is twice as large as necessary!”");
    jokeContents.add("C programmers never die. They are just <cast> into VOID.");
  }
}
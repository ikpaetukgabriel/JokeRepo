package com.example.jokerepo.controller;

import com.example.jokerepo.model.Joke;
import com.example.jokerepo.model.Jokes;
import com.example.jokerepo.service.JokeRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeRepoController {

  private final JokeRepoService jokeRepoService;

  @Autowired
  public JokeRepoController(JokeRepoService jokeRepoService) {
    this.jokeRepoService = jokeRepoService;
  }

  @GetMapping(value = "/get-jokes", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Jokes> getJokesFromController() {
    Jokes jokes = jokeRepoService.getJokes();
    return new ResponseEntity<>(jokes, HttpStatus.OK);
  }
}

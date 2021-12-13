package com.example.jokerepo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JokeRepoServiceImplTest {

  @Autowired
  private JokeRepoServiceImpl jokeRepoServiceImpl;

  @Test
  void getJokes() {
    jokeRepoServiceImpl.getJokes();
  }
}
package com.example.jokerepo.utility;

import com.example.jokerepo.model.Jokes;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.isNull;

public class ReadWriteUtilityForFile {
  private static final Gson gson = new Gson();

  // Save to file Utility
  public static void writeJokesToFile(String JokesClassAsString, String fileLocation) {

    File file = new File(fileLocation);

    // exists(): Tests whether the file or directory denoted by this abstract pathname exists.
    if (!file.exists()) {
      createFileAndDirectory(file);
    }

    try {
      // Convenience class for writing character files
      FileWriter fileWriter;
      fileWriter = new FileWriter(file.getAbsoluteFile(), true);

      // Writes text to a character-output stream
      BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
      bufferWriter.write(JokesClassAsString);
      bufferWriter.close();

      System.out.println("Joke data saved at file location: " + fileLocation + " Data: " + JokesClassAsString + "\n");
    }
    catch (IOException e) {

      System.out.println("Hmm.. Got an error while saving Joke data to file " + e);
    }
  }

  // Read From File Utility
  public static Jokes readJokesFromFile(String fileLocation) {

    // File: An abstract representation of file and directory pathname.
    // User interfaces and operating systems use system-dependent pathname strings to name files and directories.
    File file = new File(fileLocation);
    Jokes jokes = null;

    // exists(): Tests whether the file or directory denoted by this abstract pathname exists.
    if (!file.exists()) {
      createFileAndDirectory(file);
    }

    InputStreamReader isReader;
    try {
      isReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

      JsonReader myReader = new JsonReader(isReader);
      jokes = gson.fromJson(myReader, Jokes.class);
    }

    catch (Exception e) {
      System.out.println("error loading Jokes from file " + e);
    }

    System.out.println("\nJokes loaded successfully from file " + fileLocation);

    return !isNull(jokes) ? jokes : new Jokes();
  }

  private static void createFileAndDirectory(File file) {
    try {
      File directory = new File(file.getParent());
      if (!directory.exists()) {

        // mkdirs(): Creates the directory named by this abstract pathname, including any necessary but nonexistent parent directories.
        // Note that if this operation fails it may have succeeded in creating some necessary parent directories.
        if (directory.mkdirs()) {
          System.out.println(directory.toString().concat(" CREATED"));
        }
        else {
          System.out.println(directory.toString().concat(" NOT CREATED"));
        }
      }

      // createNewFile(): Atomically creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist.
      // The check for the existence of the file and the creation of the file if it does not exist are a single operation
      // that is atomic with respect to all other filesystem activities that might affect the file.
      if (file.createNewFile()) {
        System.out.println(file.toString().concat(" CREATED"));
      }
      else {
        System.out.println(file.toString().concat(" NOT CREATED"));
      }
    }
    catch (IOException e) {
      System.out.println("Exception Occurred: " + e);
    }
  }
}

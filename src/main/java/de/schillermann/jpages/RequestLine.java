package de.schillermann.jpages;

public interface RequestLine {
  Text method();

  Text path();

  Text query();

  Text protocol();
}

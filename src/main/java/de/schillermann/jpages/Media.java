package de.schillermann.jpages;

public interface Media {
  void attach(String name, String value);

  void write(byte[] content);
}

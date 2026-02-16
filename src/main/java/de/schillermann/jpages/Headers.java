package de.schillermann.jpages;

import java.io.OutputStream;

public interface Headers {
  void print(OutputStream out);

  Headers extension(String name, String value);
}

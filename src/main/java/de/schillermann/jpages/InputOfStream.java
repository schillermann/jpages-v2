package de.schillermann.jpages;

import java.io.InputStream;
import java.io.IOException;

public final class InputOfStream implements Input {
  private final InputStream source;

  public InputOfStream(final InputStream stream) {
    this.source = stream;
  }

  @Override
  public InputStream stream() throws IOException {
    return this.source;
  }
}

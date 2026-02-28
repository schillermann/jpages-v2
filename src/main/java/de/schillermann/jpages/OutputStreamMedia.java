package de.schillermann.jpages;

import java.io.OutputStream;
import java.io.IOException;

public final class OutputStreamMedia implements Media {
  private final OutputStream out;

  public OutputStreamMedia(OutputStream stream) {
    this.out = stream;
  }

  @Override
  public void write(byte[] content) {
    try {
      this.out.write(content);
      this.out.flush();
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }
}

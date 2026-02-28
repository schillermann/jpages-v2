package de.schillermann.jpages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class StickyInput implements Input {
  private final Input origin;
  private byte[] cache;

  public StickyInput(final Input input) {
    this.origin = input;
  }

  @Override
  public InputStream stream() throws IOException {
    if (this.cache == null) {
      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      this.origin.stream().transferTo(baos);
      this.cache = baos.toByteArray();
    }
    return new ByteArrayInputStream(this.cache);
  }
}

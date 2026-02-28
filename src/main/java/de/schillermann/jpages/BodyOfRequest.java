package de.schillermann.jpages;

import java.io.IOException;
import java.io.InputStream;

public final class BodyOfRequest implements Input {
  private final Input origin;

  public BodyOfRequest(final Input input) {
    this.origin = input;
  }

  @Override
  public InputStream stream() throws IOException {
    int cursor = 0;
    // Skip headers to the body
    while (cursor < 4) {
      final int b = this.origin.stream().read();
      if (b == -1) {
        break;
      }
      if ((cursor == 0 || cursor == 2) && b == 13) {
        cursor++;
      } else if ((cursor == 1 || cursor == 3) && b == 10) {
        cursor++;
      } else {
        cursor = (b == 13) ? 1 : 0;
      }
    }
    return this.origin.stream();
  }
}

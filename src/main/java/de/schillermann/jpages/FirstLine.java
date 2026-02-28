package de.schillermann.jpages;

import java.io.IOException;
import java.util.Scanner;

public final class FirstLine implements Text {
  private final Input origin;

  public FirstLine(final Input input) {
    this.origin = input;
  }

  @Override
  public String string() {
    try (var scanner = new Scanner(this.origin.stream(), "UTF-8")) {
      return scanner.nextLine(); // Just the first line
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }
}

package de.schillermann.jpages;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class ScannedLine implements Text {
  private final InputStream source;
  private final Pattern pattern;

  public ScannedLine(final InputStream input, final Pattern regex) {
    this.source = input;
    this.pattern = regex;
  }

  @Override
  public String string() {
    final Scanner scanner = new Scanner(this.source, "UTF-8");
    String found = "";
    while (scanner.hasNextLine()) {
      final String line = scanner.nextLine();
      if (line.isEmpty())
        break; // End of HTTP head
      if (this.pattern.matcher(line).matches()) {
        found = line;
        break;
      }
    }
    return found;
  }
}

package de.schillermann.jpages;

import java.io.InputStream;
import java.io.IOException;
import java.util.regex.Pattern;

public final class HeaderOfRequest implements Header {

  private final Input source;
  private final String name;

  public HeaderOfRequest(final Input input, final String key) {
    this.source = input;
    this.name = key;
  }

  @Override
  public String string() {
    try (InputStream stream = this.source.stream()) {
      return new Trimmed(
          new ParsedText(
              new ScannedLine(
                  stream,
                  Pattern.compile(
                      String.format("(?i)^%s:\\s*(.*)$", Pattern.quote(this.name)))),
              Pattern.compile(".*:\\s*(.*)$"),
              1))
          .string();
    } catch (IOException ex) {
      throw new IllegalStateException(
          String.format("Can't extract header '%s'", this.name),
          ex);
    }
  }
}

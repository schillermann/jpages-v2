package de.schillermann.jpages;

import java.util.regex.Pattern;

public final class RequestProtocol implements Text {
  private final Text line;

  public RequestProtocol(final Text text) {
    this.line = text;
  }

  @Override
  public String string() {
    return new ParsedText(
        this.line,
        Pattern.compile(".*\\s+(HTTP/\\d\\.\\d)$"),
        1).string();
  }
}

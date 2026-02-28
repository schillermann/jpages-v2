package de.schillermann.jpages;

import java.util.regex.Pattern;

public final class RequestTarget implements Text {
  private final Text line;

  public RequestTarget(final Text text) {
    this.line = text;
  }

  @Override
  public String string() {
    return new ParsedText(
        this.line,
        Pattern.compile("^\\S+\\s+(\\S+).*"),
        1).string();
  }
}

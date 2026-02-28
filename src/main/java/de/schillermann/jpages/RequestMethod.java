package de.schillermann.jpages;

import java.util.regex.Pattern;

/**
 * The HTTP Method (GET, POST, etc.)
 */
public final class RequestMethod implements Text {
  private final Text line;

  public RequestMethod(final Text text) {
    this.line = text;
  }

  @Override
  public String string() {
    return new ParsedText(
        this.line,
        Pattern.compile("^(\\S+).*"),
        1).string();
  }
}

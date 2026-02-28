package de.schillermann.jpages;

import java.util.regex.Pattern;

/**
 * The Query part of a URI.
 */
public final class RequestQuery implements Text {
  private final Text target;

  public RequestQuery(final Text uri) {
    this.target = uri;
  }

  @Override
  public String string() {
    return new ParsedText(
        this.target,
        Pattern.compile(".*\\?([^#]+).*"),
        1).string();
  }
}

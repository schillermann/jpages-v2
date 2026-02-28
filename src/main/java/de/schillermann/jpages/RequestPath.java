package de.schillermann.jpages;

import java.util.regex.Pattern;

public final class RequestPath implements Text {
  private final Text uri;

  public RequestPath(Text text) {
    this.uri = text;
  }

  @Override
  public String string() {
    return new ParsedText(
        this.uri,
        Pattern.compile("^([^?#]+).*"),
        1).string();
  }
}

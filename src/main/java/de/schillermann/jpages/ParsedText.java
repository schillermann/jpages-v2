package de.schillermann.jpages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParsedText implements Text {
  private final Text origin;
  private final Pattern pattern;
  private final int group;

  public ParsedText(Text text, Pattern regex, int index) {
    this.origin = text;
    this.pattern = regex;
    this.group = index;
  }

  @Override
  public String string() {
    final Matcher matcher = this.pattern.matcher(this.origin.string());
    return matcher.matches() ? matcher.group(this.group) : "";
  }
}

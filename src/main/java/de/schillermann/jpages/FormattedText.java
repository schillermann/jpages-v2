package de.schillermann.jpages;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class FormattedText implements Text {
  private final String pattern;
  private final Iterable<Object> args;

  // Secondary constructor for convenience
  public FormattedText(String ptn, Object... arguments) {
    this(ptn, new ListOf<>(arguments));
  }

  // Primary constructor
  public FormattedText(String pattern, Iterable<Object> arguments) {
    this.pattern = pattern;
    this.args = arguments;
  }

  @Override
  public String string() {
    final List<Object> list = new ArrayList<>();
    for (Object arg : this.args) {
      list.add(arg);
    }
    return String.format(Locale.ENGLISH, this.pattern, list.toArray());
  }
}

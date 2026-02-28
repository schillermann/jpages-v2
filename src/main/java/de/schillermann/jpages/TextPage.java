package de.schillermann.jpages;

public final class TextPage implements Page {
  private final Page origin;

  public TextPage(final Text text) {
    this.origin = new PageWithStatus(
        new PageWithType(
            new PageWithLength(text),
            "text/plain"),
        200,
        "OK");
  }

  public TextPage(final String text) {
    this(new FormattedText("%s", text));
  }

  @Override
  public void print(final Connection c) {
    this.origin.print(c);
  }
}

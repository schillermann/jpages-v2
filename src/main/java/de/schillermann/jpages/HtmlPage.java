package de.schillermann.jpages;

public final class HtmlPage implements Page {
  private final Page origin;

  public HtmlPage(final Text html) {
    this.origin = new PageWithStatus(
        new PageWithType(
            new PageWithLength(html),
            "text/html"),
        200,
        "OK");
  }

  public HtmlPage(final String html) {
    this(new FormattedText("%s", html));
  }

  @Override
  public void print(final Connection c) {
    this.origin.print(c);
  }
}

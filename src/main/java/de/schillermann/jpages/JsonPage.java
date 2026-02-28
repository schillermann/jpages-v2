package de.schillermann.jpages;

public final class JsonPage implements Page {
  private final Page origin;

  public JsonPage(final Text json) {
    this.origin = new PageWithStatus(
        new PageWithType(
            new PageWithLength(json),
            "application/json"),
        200,
        "OK");
  }

  public JsonPage(final String json) {
    this(new FormattedText("%s", json));
  }

  @Override
  public void print(final Connection c) {
    this.origin.print(c);
  }
}

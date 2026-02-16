package de.schillermann.jpages;

public final class SimplePage implements Page {
  private final String body;

  public SimplePage(final String text) {
    this.body = text;
  }

  @Override
  public void print(Media media) {
    media.attach("Content-Length", Integer.toString(this.body.length()));
    media.write(this.body.getBytes());
  }
}

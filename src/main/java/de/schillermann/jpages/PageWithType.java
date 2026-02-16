package de.schillermann.jpages;

public final class PageWithType implements Page {
  private final Page origin;
  private final String mime;

  public PageWithType(final Page page, final String type) {
    this.origin = page;
    this.mime = type;
  }

  @Override
  public void print(Media media) {
    media.attach("Content-Type", this.mime);
    this.origin.print(media);
  }
}

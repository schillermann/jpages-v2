package de.schillermann.jpages;

public final class WithBody implements Media {
  private final Media origin;

  public WithBody(Media media) {
    this.origin = media;
  }

  @Override
  public void write(byte[] content) {
    this.origin.write(
        new JoinedBytes("\r\n".getBytes(), content).array());
  }
}

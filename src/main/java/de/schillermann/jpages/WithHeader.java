package de.schillermann.jpages;

import java.nio.charset.StandardCharsets;

public final class WithHeader implements Media {
  private final Media origin;
  private final String name;
  private final String value;

  public WithHeader(Media media, String name, String value) {
    this.origin = media;
    this.name = name;
    this.value = value;
  }

  @Override
  public void write(byte[] content) {
    String formatted = String.format("%s: %s\r\n", this.name, this.value);
    this.origin.write(
        new JoinedBytes(formatted.getBytes(StandardCharsets.UTF_8), content).array());
  }
}

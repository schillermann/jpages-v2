package de.schillermann.jpages;

import java.nio.charset.StandardCharsets;

public final class PageWithLength implements Page {
  private final Text origin;

  public PageWithLength(final Text text) {
    this.origin = text;
  }

  @Override
  public void print(final Connection c) {
    final byte[] bytes = this.origin.string().getBytes(StandardCharsets.UTF_8);
    new WithBody(
        new WithHeader(
            c.media(),
            "Content-Length",
            String.valueOf(bytes.length)))
        .write(bytes);
  }
}

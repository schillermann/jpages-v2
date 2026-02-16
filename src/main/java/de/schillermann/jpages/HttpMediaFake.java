package de.schillermann.jpages;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class HttpMediaFake implements Media {
  private final Map<String, String> headers;
  private byte[] content;

  public HttpMediaFake() {
    this.headers = new HashMap<>();
    this.content = new byte[0];
  }

  @Override
  public void attach(final String name, final String value) {
    this.headers.put(name, value);
  }

  @Override
  public void write(final byte[] bytes) {
    this.content = bytes;
  }

  String header(final String name) {
    return this.headers.get(name);
  }

  String body() {
    return new String(this.content, StandardCharsets.UTF_8);
  }
}

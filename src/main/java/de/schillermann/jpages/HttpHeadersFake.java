package de.schillermann.jpages;

import java.io.OutputStream;
import java.util.Map;

public final class HttpHeadersFake implements Headers {
  private final Map<String, String> map;

  public HttpHeadersFake(final Map<String, String> map) {
    this.map = map;
  }

  @Override
  public Headers extension(final String name, final String value) {
    this.map.put(name, value);
    return this;
  }

  @Override
  public void print(final OutputStream out) {
    // Intentionally empty fake implementation.
  }
}

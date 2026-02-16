package de.schillermann.jpages;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class HttpHeaders implements Headers {
  private final Map<String, String> map;

  // Primary constructor
  public HttpHeaders(Map<String, String> list) {
    this.map = Collections.unmodifiableMap(list);
  }

  // Secondary constructor for "Empty" start
  public HttpHeaders() {
    this(new HashMap<>());
  }

  @Override
  public Headers extension(String name, String value) {
    final Map<String, String> next = new HashMap<>(this.map);
    next.put(name, value);
    return new HttpHeaders(next);
  }

  @Override
  public void print(OutputStream out) {
    try {
      out.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));

      for (final Map.Entry<String, String> entry : this.map.entrySet()) {
        out.write(
            new FormattedText(
                "%s: %s\r\n",
                entry.getKey(),
                entry.getValue()).string().getBytes(StandardCharsets.UTF_8));
      }

      out.write("\r\n".getBytes(StandardCharsets.UTF_8));
    } catch (IOException ex) {
      throw new IllegalStateException(
          "Failed to print headers to the stream", ex);
    }
  }
}

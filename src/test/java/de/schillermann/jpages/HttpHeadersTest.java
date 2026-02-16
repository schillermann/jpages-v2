package de.schillermann.jpages;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class HttpHeadersTest {
  @Test
  void printsStatusLineHeadersAndEmptyLine() {
    final Map<String, String> map = new LinkedHashMap<>();
    map.put("Content-Type", "application/json");
    map.put("Content-Length", "19");
    final HttpHeaders headers = new HttpHeaders(map);
    final ByteArrayOutputStream out = new ByteArrayOutputStream();

    headers.print(out);

    assertEquals(
        "HTTP/1.1 200 OK\r\n"
            + "Content-Type: application/json\r\n"
            + "Content-Length: 19\r\n"
            + "\r\n",
        out.toString(StandardCharsets.UTF_8)
    );
  }

  @Test
  void extensionReturnsNewHeadersWithAddedEntry() {
    final HttpHeaders original = new HttpHeaders();
    final Headers extended = original.extension("Content-Type", "text/plain");
    final ByteArrayOutputStream baseOut = new ByteArrayOutputStream();
    final ByteArrayOutputStream extendedOut = new ByteArrayOutputStream();

    original.print(baseOut);
    extended.print(extendedOut);

    assertNotSame(original, extended);
    assertEquals("HTTP/1.1 200 OK\r\n\r\n", baseOut.toString(StandardCharsets.UTF_8));
    assertEquals(
        "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/plain\r\n"
            + "\r\n",
        extendedOut.toString(StandardCharsets.UTF_8)
    );
  }
}

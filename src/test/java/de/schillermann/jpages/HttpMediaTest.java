package de.schillermann.jpages;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpMediaTest {
  @Test
  void writesBodyAndExtendsInjectedFakeHeaders() {
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final Map<String, String> headers = new HashMap<>();
    final HttpMedia media = new HttpMedia(out, new HttpHeadersFake(headers));

    media.attach("Content-Type", "text/plain");
    media.attach("Content-Length", "5");
    media.write("hello".getBytes(StandardCharsets.UTF_8));

    assertEquals("text/plain", headers.get("Content-Type"));
    assertEquals("5", headers.get("Content-Length"));
    assertEquals("hello", out.toString(StandardCharsets.UTF_8));
  }

  @Test
  void rejectsHeaderAttachmentAfterBodyWasWritten() {
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final HttpMedia media = new HttpMedia(out, new HttpHeadersFake(new HashMap<>()));

    media.write("body".getBytes(StandardCharsets.UTF_8));

    final IllegalStateException exception = assertThrows(
        IllegalStateException.class,
        () -> media.attach("X-Test", "value")
    );
    assertTrue(exception.getMessage().contains("X-Test"));
  }
}

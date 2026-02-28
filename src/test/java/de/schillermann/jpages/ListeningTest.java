package de.schillermann.jpages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListeningTest {
  @Test
  void startsServerAndServesOneRequest() throws Exception {
    final CountDownLatch responseWritten = new CountDownLatch(1);
    final CountDownLatch finishRequest = new CountDownLatch(1);
    final Page page = connection -> {
      new TextPage("hello from server").print(connection);
      responseWritten.countDown();
      try {
        finishRequest.await(2, TimeUnit.SECONDS);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    };

    try (ServerSocket server = new ServerSocket(0)) {
      final Thread worker = new Thread(
          () -> new Listening(server, page).value(),
          "listening-test-worker");
      worker.start();

      try (Socket client = new Socket("127.0.0.1", server.getLocalPort())) {
        client.setSoTimeout(2_000);
        final OutputStream out = client.getOutputStream();
        out.write("GET / HTTP/1.1\r\nHost: localhost\r\n\r\n".getBytes(StandardCharsets.UTF_8));
        out.flush();

        assertTrue(responseWritten.await(2, TimeUnit.SECONDS));

        final String response = new String(read(client.getInputStream()), StandardCharsets.UTF_8);
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("hello from server"));
      }

      worker.interrupt();
      finishRequest.countDown();
      worker.join(2_000);
      assertFalse(worker.isAlive());
    }
  }

  private static byte[] read(InputStream input) throws IOException {
    final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    final byte[] chunk = new byte[1024];
    final int amount = input.read(chunk);
    if (amount > 0) {
      bytes.write(chunk, 0, amount);
    }
    return bytes.toByteArray();
  }
}

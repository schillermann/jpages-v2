package de.schillermann.jpages;

import java.io.OutputStream;
import java.io.IOException;

final class HttpMedia implements Media {
  private final OutputStream out;
  private final Headers headers;
  private boolean bodyWritten;

  public HttpMedia(OutputStream stream) {
    this(stream, new HttpHeaders());
  }

  public HttpMedia(OutputStream stream, Headers head) {
    this.out = stream;
    this.headers = head;
    this.bodyWritten = false;
  }

  @Override
  public void attach(String name, String value) {
    if (this.bodyWritten) {
      throw new IllegalStateException(
          new FormattedText(
              "Cannot attach header '%s', because the body has already been written",
              name).string());
    }
    this.headers.extension(name, value);
  }

  @Override
  public void write(byte[] content) {
    try {
      if (!this.bodyWritten) {
        this.headers.print(this.out);
        this.bodyWritten = true;
      }
      this.out.write(content);
      this.out.flush();
    } catch (IOException ex) {
      throw new IllegalStateException(
          "Failed to transmit substance to the output stream",
          ex);
    }
  }
}

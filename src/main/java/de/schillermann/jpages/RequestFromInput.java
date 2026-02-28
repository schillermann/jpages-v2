package de.schillermann.jpages;

import java.io.InputStream;

public final class RequestFromInput implements Request {
  private final Input source;

  public RequestFromInput(InputStream stream) {
    this.source = new StickyInput(new InputOfStream(stream));
  }

  @Override
  public RequestLine line() {
    return new RequestLineFromInput(this.source);
  }

  @Override
  public Header header(String name) {
    return new HeaderOfRequest(this.source, name);
  }

  @Override
  public Input body() {
    return new BodyOfRequest(this.source);
  }
}

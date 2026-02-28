package de.schillermann.jpages;

public final class RequestLineFromInput implements RequestLine {
  private final Input source;

  public RequestLineFromInput(final Input input) {
    this.source = input;
  }

  @Override
  public Text method() {
    return new RequestMethod(new FirstLine(this.source));
  }

  @Override
  public Text path() {
    return new RequestPath(new RequestTarget(new FirstLine(this.source)));
  }

  @Override
  public Text query() {
    return new RequestQuery(new RequestTarget(new FirstLine(this.source)));
  }

  @Override
  public Text protocol() {
    return new RequestProtocol(new FirstLine(this.source));
  }
}

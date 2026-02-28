package de.schillermann.jpages;

public final class ConnectionWithStatus implements Connection {
  private final Connection origin;
  private final int code;
  private final String reason;

  public ConnectionWithStatus(final Connection cnct, final int status, final String msg) {
    this.origin = cnct;
    this.code = status;
    this.reason = msg;
  }

  @Override
  public Request request() {
    return this.origin.request();
  }

  @Override
  public Media media() {
    return new WithStatus(this.origin.media(), this.code, this.reason);
  }
}
